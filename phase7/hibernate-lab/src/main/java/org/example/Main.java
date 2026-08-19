package org.example;

import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context=
                new AnnotationConfigApplicationContext(Main.class);
        



    }
}