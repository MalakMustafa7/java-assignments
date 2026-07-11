package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class EmployeeRepository {
    List<Employee> employees = new ArrayList<>();

    public Map<String,Employee>findHighestPaidEmployeeByDepartment(){
      return employees.stream()
               .collect(Collectors.groupingBy(Employee::getDepartment,
                       Collectors.collectingAndThen(
                               Collectors.maxBy(Comparator.comparing(Employee::getSalary))
                               , Optional::get
                       )));
    }

    public Map<String,Long> countEmployeesByRole(){
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole,
                                               Collectors.counting()));
    }

    public List<String>getUppercaseSortedNamesOfRemoteEmployees(){
        return employees.stream()
                .filter(Employee::isRemote)
                .map(employee -> employee.getName().toUpperCase())
                .sorted()
                .toList();
    }

    public OptionalDouble getAverageSalaryByExperience(){
        return employees.stream()
                .filter(employee -> employee.getYearsOfExperience()>3)
                .mapToDouble(Employee::getSalary)
                .average();
    }

    public List<String> getAllDistinctSortedSkills(){
        return employees.stream()
                .map(Employee::getSkills)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .toList();
    }

    public List<Employee>getTop5HighestPaidNonManagers(){
        return employees.stream()
                .filter(employee -> !employee.getRole().equalsIgnoreCase("manager"))
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(5)
                .toList();
    }

    public Map<Integer, List<Employee>> groupByHireYear(){
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getHireDate().getYear()));
    }

    public List<String> findJavaSpringDevelopers() {
        return employees.stream()
                .filter(e -> e.getSkills().stream().anyMatch(s -> s.equalsIgnoreCase("java")) &&
                        e.getSkills().stream().anyMatch(s -> s.equalsIgnoreCase("spring")))
                .map(Employee::getName)
                .toList();
    }

    public Map<String, Double> getDepartmentSalaryCostSorted() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Optional<String> getTopRemoteDepartment(){
        return employees.stream()
                .filter(Employee::isRemote)
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }



}
