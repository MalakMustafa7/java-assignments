package org.example.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    @Autowired
    private B b;

    public A() {
        System.out.println(b);
    }

    @PostConstruct
    public void init() {
        System.out.println(b);
    }
}