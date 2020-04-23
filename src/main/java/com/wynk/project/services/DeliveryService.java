package com.wynk.project.services;

import com.wynk.project.exceptions.NoDeliveryPersonExistException;
import com.wynk.project.models.DeliveryPerson;
import com.wynk.project.models.DeliveryPersonStatus;
import com.wynk.project.models.OrderDetails;
import com.wynk.project.models.OrderStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.wynk.project.models.Constants.*;

@Service
public class DeliveryService implements IDeliveryService {

    /**
     * The list containing all the delivery person's info in the memory
     */
    List<DeliveryPerson> deliveryPersonList = new ArrayList<>();

    @PostConstruct
    public void initialise() {
        if (deliveryPersonList.isEmpty()) {
            for (int i = 0; i < numberOfDeliveryPersons; i++) {
                deliveryPersonList.add(new DeliveryPerson());
            }
        }
    }

    @Override
    public List<DeliveryPerson> getStatus() {
        return deliveryPersonList.stream().map(deliveryPerson ->
        {
            if (deliveryPerson.getTimeAtWhichHeStarted() != null) {
                deliveryPerson.setTimeRemaining();
            }
            return deliveryPerson;
        }).collect(Collectors.toList());
    }

    @Override
    public DeliveryPerson getStatus(Integer deliveryPersonId) {
        Optional<DeliveryPerson> deliveryPersonOptional = deliveryPersonList.stream().filter(delPer -> delPer.getId()
                .equals(deliveryPersonId)).findAny();
        if (deliveryPersonOptional.isPresent()) {
            DeliveryPerson deliveryPerson = deliveryPersonOptional.get();
            if (deliveryPerson.getTimeAtWhichHeStarted() != null) {
                deliveryPerson.setTimeRemaining();
            }
            return deliveryPerson;
        }
        throw new NoDeliveryPersonExistException(DELIVERY_PERSON_ID_NOT_PRESENT);
    }

    @Override
    public String acceptOrder(Integer orderId, Integer deliveryPersonId) {
        Optional<DeliveryPerson> deliveryPersonOptional = deliveryPersonList.stream().filter(deliveryPerson ->
                deliveryPerson.getId().equals(deliveryPersonId)).findAny();
        if (deliveryPersonOptional.isPresent()) {
            DeliveryPerson deliveryPerson = deliveryPersonOptional.get();
            if (deliveryPerson.getStatus().equals(DeliveryPersonStatus.INACTIVE)) {
                deliveryPerson.setOrderId(orderId);
                deliveryPerson.setStatus(DeliveryPersonStatus.ACTIVE);
                deliveryPerson.setTimeAtWhichHeStarted(new Date());
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderId(orderId);
                orderDetails.setStatus(OrderStatus.COMPLETED);
                OrderScheduler orderScheduler = new OrderScheduler(orderDetails, deliveryPerson);
                Timer timer = new Timer();
                timer.schedule(orderScheduler, Date.from(Instant.now().plusSeconds(delay)));
                return OrderStatus.ACCEPTED.getStatus();
            }
            return DELIVERY_PERSON_IS_STILL_ACTIVE;
        }
        return DELIVERY_PERSON_ID_NOT_PRESENT;
    }
}
