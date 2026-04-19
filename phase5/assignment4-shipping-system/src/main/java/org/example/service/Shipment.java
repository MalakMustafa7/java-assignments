package org.example.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.PackageItem;
import org.example.state.State;
import org.example.state.States;
import org.example.strategy.delivery.Delivery;
import org.example.strategy.packaging.PackagingStrategy;


@Getter
@Setter
@Slf4j
public class Shipment {
    private PackageItem packageItem;
    private PackagingStrategy packagingStrategy;
    private   Delivery delivery;
    private State state;

    public Shipment(PackageItem packageItem,PackagingStrategy packagingStrategy,Delivery delivery){

        state = States.READY_FOR_DELIVERY;
        this.packageItem = packageItem;
        this.packagingStrategy = packagingStrategy;
        this.delivery = delivery;
    }

    public void next(){
        state.next(this);
    }

    public void process(){
        packagingStrategy.pack(packageItem);
        delivery.deliver(packageItem);
    }

    public String getCurrentState(){
        return state.name();
    }
}
