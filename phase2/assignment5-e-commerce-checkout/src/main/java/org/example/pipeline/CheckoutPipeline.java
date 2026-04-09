package org.example.pipeline;

import org.example.utility.ErrorMessages;

import java.util.List;

public class CheckoutPipeline {
    private final CheckoutHandler firstHandler;
    public CheckoutPipeline(List<CheckoutHandler> handlers){
        if(handlers.isEmpty()){
            throw new IllegalArgumentException(ErrorMessages.EMPTY_PIPELINE);
        }
        firstHandler = handlers.getFirst();
        for(int i=0;i<handlers.size()-1;i++){
            handlers.get(i).setNext(handlers.get(i+1));
        }
    }

    public void execute(CheckoutContext context){
        firstHandler.handle(context);
    }
}
