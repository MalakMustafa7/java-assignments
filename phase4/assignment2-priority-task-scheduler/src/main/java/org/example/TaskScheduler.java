package org.example;

import java.util.*;

public class TaskScheduler {
    Queue<Task> tasks = new PriorityQueue<>();
     Set<String> cancelledIds = new HashSet<>();

    public void submit(Task task){
        tasks.add(task);
    }
    public Task processNext(){
        while (!tasks.isEmpty()){
            Task task = tasks.poll();
            if(cancelledIds.contains(task.getTaskId())){
                continue;
            }
            return task;
        }
        return null;
    }
    public List<Task> processBatch(int n){
        List<Task> batchTasks = new ArrayList<>();
        for(int i=0;i<n;i++){
            Task task = processNext();
              if(task == null){
                  break;
              }
            batchTasks.add(task);
        }
        return batchTasks;
    }
    public Task peekNext(){

        for (Task task: tasks){
            if(cancelledIds.contains(task.getTaskId())){
                continue;
            }
            return task;
        }
        return null;
    }
    public void cancelTask(String taskId){
        cancelledIds.add(taskId);

    }

    public List<Task>getQueueSnapshot() {
        PriorityQueue<Task> copy = new PriorityQueue<>(tasks);
        List<Task> result = new ArrayList<>();

        while (!copy.isEmpty()) {
            Task t = copy.poll();

            if (!cancelledIds.contains(t.getTaskId())) {
                result.add(t);
            }
        }

        return result;
    }

}
