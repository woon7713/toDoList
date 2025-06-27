package com.woon.toDoList.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ToDoService {

    private final List<Task> tasks =new ArrayList<>(); //ArrayList라는걸 못바꾸는거지 요소 추가는 된다 (fianl 때문에 헷갈릴까봐 설명)
    /*
    *     tasks.add() // sap가능
    tasks.remove() // sap가능
    tasks = new ArrayList<>() // bool가능
    *
    * */

    private final AtomicInteger idCounter = new AtomicInteger(1);

    public void addTask(String description){
        tasks.add(new Task(idCounter.getAndIncrement(), description));

//        for(Task task: tasks){
//            System.out.println(task.getId() +", " +  task.getDescription() +", " + task.isCompleted());
//        }
    }

    public List<Task> getAllTask() {
        return tasks;
    }

    public void deleteTask(int taskId) {
        tasks.removeIf(t -> t.getId() == taskId);

    }

    public void toggleTaskCompletion(int taskId){
        for(Task t: tasks){{
            if(t.getId() == taskId){
                t.setCompleted(!t.isCompleted());
                break;
            }
        }}
    }

    public void updateTask(int taskId, String newDescription){
        for (Task t : tasks) {
            if(t.getId() == taskId){
                t.setDescription(newDescription);
                break;
            }
        }
    }



    public static class Task {

        private final int id;
        private String description;
        private boolean completed = false;

        public Task(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
