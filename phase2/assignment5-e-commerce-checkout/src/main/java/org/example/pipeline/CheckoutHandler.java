package org.example.pipeline;

public abstract class CheckoutHandler {
    private CheckoutHandler next;

    public CheckoutHandler setNext(CheckoutHandler next){
        this.next = next;
        return next;
    }
    public final void handle(CheckoutContext context){
        process(context);
        if(next!=null){
            next.handle(context);
        }
    }
    protected abstract void process(CheckoutContext context);
}
