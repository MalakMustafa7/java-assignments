package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;

@AllArgsConstructor
@Getter
public class Task implements Comparable<Task> {
    private String taskId;
    private String name;
    private Integer priority;
    private LocalDateTime deadline;
    private Long estimatedMinutes;
    private Status status;

    @Override
    public int compareTo(Task o) {
       return Comparator.comparing(Task::getPriority)
               .thenComparing(Task::getDeadline)
               .compare(this,o);
    }
}
