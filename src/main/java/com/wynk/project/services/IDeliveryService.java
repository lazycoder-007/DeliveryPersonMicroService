package com.wynk.project.services;

import com.wynk.project.models.DeliveryPerson;

import java.util.List;

public interface IDeliveryService {
    public List<DeliveryPerson> getStatus();

    public DeliveryPerson getStatus(Integer id);

    public String acceptOrder(Integer orderId, Integer deliveryPersonId);
}
