package com.wynk.project.models;

import java.util.Date;

import static com.wynk.project.models.Constants.delay;

public class DeliveryPerson {

    static Integer personId = 0;
    {
        personId++;
    }

    Integer id;
    DeliveryPersonStatus deliveryPersonStatus;
    Integer orderId;
    Date timeAtWhichHeStarted;
    Long timeRemainingInSeconds;

    public DeliveryPerson() {
        this.id = personId;
        this.deliveryPersonStatus = DeliveryPersonStatus.INACTIVE;
        this.orderId = null;
        this.timeAtWhichHeStarted = null;
        this.timeRemainingInSeconds = null;
    }

    public Integer getId() {
        return id;
    }

    public DeliveryPersonStatus getStatus() {
        return deliveryPersonStatus;
    }

    public void setStatus(DeliveryPersonStatus deliveryPersonStatus) {
        this.deliveryPersonStatus = deliveryPersonStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getTimeRemainingInSeconds() {
        return timeRemainingInSeconds;
    }

    public void setTimeRemaining() {
        this.timeRemainingInSeconds = delay -Long.valueOf((new Date().getTime() - timeAtWhichHeStarted.getTime())/1000);
    }

    public Date getTimeAtWhichHeStarted() {
        return timeAtWhichHeStarted;
    }

    public void setTimeAtWhichHeStarted(Date timeAtWhichHeStarted) {
        this.timeAtWhichHeStarted = timeAtWhichHeStarted;
    }

    public void reset() {
        this.deliveryPersonStatus = DeliveryPersonStatus.INACTIVE;
        this.orderId = null;
        timeAtWhichHeStarted = null;
        this.timeRemainingInSeconds = 0L;
    }
}
