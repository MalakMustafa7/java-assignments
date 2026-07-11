package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Grade {
    private String studentId;
    private String subjectCode;
    private double  scores;
    private String  semesters;


}
