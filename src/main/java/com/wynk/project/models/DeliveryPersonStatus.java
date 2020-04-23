package com.wynk.project.models;

public enum DeliveryPersonStatus {

    ACTIVE("Active"), INACTIVE("Inactive");

    String status;

    DeliveryPersonStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
