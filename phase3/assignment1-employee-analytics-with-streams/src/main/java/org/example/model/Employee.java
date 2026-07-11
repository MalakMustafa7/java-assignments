package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class Employee {
    String id, name, department, role;
    double salary;
    boolean isRemote;
    int yearsOfExperience;
    List<String> skills;
    LocalDate hireDate;

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
