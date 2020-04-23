package com.wynk.project.controllers;

import com.wynk.project.models.DeliveryPerson;
import com.wynk.project.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryPersonController {

    @Autowired
    private DeliveryService deliveryService;

    /**
     * API to get the status of all the delivery person
     *
     * @return the status of the delivery person
     */
    @RequestMapping("/getStatus")
    public List<DeliveryPerson> getStatus() {
        return deliveryService.getStatus();
    }

    /**
     * API to get the status of a delivery person with a deliveryId
     *
     * @param id the delivery id of the person
     * @return the status of the delivery person
     */
    @RequestMapping("/getStatus/{id}")
    public DeliveryPerson getStatus(@PathVariable Integer id) {
        return deliveryService.getStatus(id);
    }

    /**
     * API to get accept an order with an orderId and and by a delivery person with delivery Id
     *
     * @param orderId the orderId of the order
     * @param deliveryPersonId the delivery person's id
     * @return the status of the accepted order
     */
    @RequestMapping(method = RequestMethod.GET, value = "/acceptOrder")
    public String acceptOrder(@RequestParam Integer orderId, @RequestParam Integer deliveryPersonId) {
        return deliveryService.acceptOrder(orderId, deliveryPersonId);
    }
}
