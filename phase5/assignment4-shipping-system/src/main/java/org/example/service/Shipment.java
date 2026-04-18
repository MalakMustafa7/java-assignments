package org.example.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.PackageItem;
import org.example.state.Pending;
import org.example.state.State;
import org.example.state.States;
import org.example.strategy.delivery.Delivery;
import org.example.strategy.packaging.PackagingStrategy;


@Getter
@Slf4j
public class Shipment {
    private PackageItem packageItem;
    private PackagingStrategy packagingStrategy;
    private   Delivery delivery;
    private State state;

    public Shipment(PackageItem packageItem,PackagingStrategy packagingStrategy,Delivery delivery){

        state = States.PENDING;
        this.packageItem = packageItem;
        this.packagingStrategy = packagingStrategy;
        this.delivery = delivery;
    }

    public void pack(){
        state.pack(this);
    }
    public void ship(){
        state.ship(this);
    }
    public void deliver(){
        state.deliver(this);
    }
    public void applyPackaging() {
        packagingStrategy.pack(packageItem);
    }

    public void applyDelivery() {
        delivery.deliver(packageItem);
    }

    public void setState(State newState) {
        log.info("Transition: {} -> {}",
                state.getClass().getSimpleName(),
                newState.getClass().getSimpleName()
        );
        this.state = newState;
    }
}
