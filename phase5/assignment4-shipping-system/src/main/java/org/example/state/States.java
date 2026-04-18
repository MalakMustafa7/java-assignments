package org.example.state;

public class States {
    public static final State PENDING = new Pending();
    public static final State READY_FOR_DELIVERY = new ReadyForDelivery();
    public static final State OUT_FOR_DELIVERY = new OutForDelivery();
    public static final State DELIVERED = new Delivered();
}
