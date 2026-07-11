package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Subject {
    private String code;
    private String name;
    private int credits;
}
