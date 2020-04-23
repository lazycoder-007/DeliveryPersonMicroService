package com.wynk.project.services;

import com.wynk.project.models.DeliveryPerson;
import com.wynk.project.models.OrderDetails;
import org.springframework.web.client.RestTemplate;

import java.util.TimerTask;

public class OrderScheduler extends TimerTask {
    OrderDetails orderDetails;
    DeliveryPerson deliveryPerson;

    public OrderScheduler(OrderDetails orderDetails, DeliveryPerson deliveryPerson)
    {
        this.orderDetails = orderDetails;
        this.deliveryPerson = deliveryPerson;
    }

    @Override
    public void run() {
        String url = "http://localhost:8089/updateOrder";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, orderDetails, String.class);
        deliveryPerson.reset();
    }
}
