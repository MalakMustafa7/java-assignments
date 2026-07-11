package org.example;

import java.util.*;

public class BrowserHistory {
    private  int maxSize ;
    private Deque<String> forwardStack= new ArrayDeque<>();
    private Deque<String> backStack= new ArrayDeque<>();
    private String current = null;

    public BrowserHistory(int maxSize) {
        this.maxSize = maxSize;
    }
    public BrowserHistory(){
        this(10);
    }
    public void visit(String url){
        if(current != null){
            backStack.push(current);
        }
        current = url;
        forwardStack.clear();
        trimIfNeeded();
    }
    public String back(int steps){
        while (steps>0 && !backStack.isEmpty()){
            forwardStack.push(current);
            current= backStack.pop();
            steps--;
        }
        return current;
    }

    public String forward(int steps){
        while (steps > 0 && !forwardStack.isEmpty()){
            backStack.push(current);
            current = forwardStack.pop();
            steps--;
        }
        return current;
    }

    public String getCurrentPage(){
        return current;
    }

    public List<String> getBackHistory(){
       return new ArrayList<>(backStack);
    }

    public List<String> getForwardHistory(){
        return new ArrayList<>(forwardStack);
    }

    private void trimIfNeeded() {
        while (backStack.size() + (current != null ? 1 : 0) > maxSize && !backStack.isEmpty()) {
                backStack.removeLast();
        }
    }
}


