package org.example;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Employee;
import org.example.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
public class Main {
    public static void main(String[] args) {

        // ── Seed data ──────────────────────────────────────────────────────────
        EmployeeRepository repo = new EmployeeRepository();

            repo.getEmployees().addAll(List.of(
                new Employee("E01", "Alice Johnson",   "Engineering", "Developer", 95_000, true,  6,
                        List.of("Java", "Spring", "Docker"),          LocalDate.of(2018, 3, 15)),
                new Employee("E02", "Bob Smith",       "Engineering", "Manager",   120_000, false, 9,
                        List.of("Java", "Kubernetes", "Spring"),      LocalDate.of(2015, 7, 1)),
                new Employee("E03", "Carol White",     "Marketing",   "Analyst",   70_000, true,  3,
                        List.of("Excel", "SQL", "PowerBI"),           LocalDate.of(2021, 1, 20)),
                new Employee("E04", "David Brown",     "Engineering", "Developer", 88_000, false, 5,
                        List.of("Python", "Django", "SQL"),           LocalDate.of(2019, 11, 5)),
                new Employee("E05", "Eve Davis",       "HR",          "Recruiter", 60_000, true,  2,
                        List.of("Communication", "Excel"),            LocalDate.of(2022, 6, 10)),
                new Employee("E06", "Frank Miller",    "Marketing",   "Manager",   110_000, false, 8,
                        List.of("Marketing", "SQL", "PowerBI"),       LocalDate.of(2016, 4, 22)),
                new Employee("E07", "Grace Wilson",    "Engineering", "Developer", 102_000, true,  7,
                        List.of("Java", "Spring", "React", "SQL"),   LocalDate.of(2017, 9, 13)),
                new Employee("E08", "Henry Taylor",    "HR",          "Manager",   98_000, true,  10,
                        List.of("Leadership", "Excel", "Communication"), LocalDate.of(2014, 2, 28)),
                new Employee("E09", "Isla Anderson",   "Engineering", "DevOps",    91_000, true,  4,
                        List.of("Docker", "Kubernetes", "Linux"),    LocalDate.of(2020, 8, 3)),
                new Employee("E10", "Jack Thomas",     "Marketing",   "Developer", 78_000, false, 1,
                        List.of("JavaScript", "React", "CSS"),       LocalDate.of(2023, 5, 17))
        ));

        log.info("=== Highest Paid Employee By Department ===");
        log.info("{}", repo.findHighestPaidEmployeeByDepartment());

        log.info("=== Employees Count By Role ===");
        log.info("{}", repo.countEmployeesByRole());

        log.info("=== Uppercase Sorted Remote Employees ===");
        log.info("{}", repo.getUppercaseSortedNamesOfRemoteEmployees());

        log.info("=== Average Salary By Experience > 3 Years ===");
        log.info("{}", repo.getAverageSalaryByExperience());

        log.info("=== Distinct Sorted Skills ===");
        log.info("{}", repo.getAllDistinctSortedSkills());

        log.info("=== Top 5 Highest Paid Non-Managers ===");
        log.info("{}", repo.getTop5HighestPaidNonManagers());

        log.info("=== Group By Hire Year ===");
        log.info("{}", repo.groupByHireYear());

        log.info("=== Java & Spring Developers ===");
        log.info("{}", repo.findJavaSpringDevelopers());

        log.info("=== Department Salary Cost Sorted ===");
        log.info("{}", repo.getDepartmentSalaryCostSorted());

        log.info("=== Top Remote Department ===");
        log.info("{}", repo.getTopRemoteDepartment());
    }
}