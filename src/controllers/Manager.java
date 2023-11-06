package controllers;

import classes.Persistence;
import classes.Task;
import classes.TasksInfo;
import comparators.CompareByCategory;
import comparators.CompareByDue;
import comparators.CompareByPriority;
import comparators.CompareByStatus;

import java.io.IOException;
import java.util.*;

public class Manager {
    LinkedList<Task> todoList;
    LinkedList<Task> doneList;
    String fileRegistered;
    String fileDone;


    public Manager(TasksInfo tasksInfo) {
        this.todoList = tasksInfo.getTodoList();
        this.todoList.sort(new CompareByStatus());

        this.doneList = tasksInfo.getDoneList();
        this.todoList.sort(new CompareByDue());

        this.fileRegistered = tasksInfo.getFileRegistered();
        this.fileDone = tasksInfo.getFileDone();
    }

    public void listTasks(int listBy, LinkedList<Task> listSorted) {
        if(listBy == 3){
            listSorted.sort(new CompareByDue());
        }else if(listBy == 2){
            listSorted.sort(new CompareByCategory());
        } else if (listBy == 1) {
            listSorted.sort(new CompareByPriority());
        } else {
            listSorted.sort(new CompareByStatus());
        }
        for(Task task : listSorted) {
            System.out.println(task);
        }
    }

    public void addTask(Task newTask){

        todoList.add(newTask);
        todoList.sort(new CompareByPriority());
        try {
            Persistence.writeTasks(todoList, fileRegistered);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int findByName(String name) {
        name = name.toLowerCase();
        int i = 0;
        for (Task task : todoList) {
            if (task.getName().toLowerCase().equals(name)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void removeTask(int index){

        if(index >= 0){
            todoList.remove(index);
        }else {
            System.out.println("Could not find given task");
        }

        try {
            Persistence.writeTasks(todoList, fileRegistered);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTask(Task taskAux, int index){
        
        Task editingTask = todoList.get(index);
        editingTask.copyTaskInfo(taskAux);

        try {
            Persistence.writeTasks(todoList, "src/data/registeredTasks.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
