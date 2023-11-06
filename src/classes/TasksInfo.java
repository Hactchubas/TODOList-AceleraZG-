package classes;

import java.util.LinkedList;

public class TasksInfo {
    LinkedList<Task> todoList;

    LinkedList<Task> doneList;
    String fileRegistered;
    String fileDone;

    public  TasksInfo(LinkedList<Task> todoList, LinkedList<Task> doneList, String fileRegistered, String fileDone){
        this.doneList = doneList;
        this.todoList = todoList;
        this.fileDone = fileDone;
        this.fileRegistered = fileRegistered;
    }

    public LinkedList<Task> getTodoList() {
        return todoList;
    }

    public void setTodoList(LinkedList<Task> todoList) {
        this.todoList = todoList;
    }

    public LinkedList<Task> getDoneList() {
        return doneList;
    }

    public void setDoneList(LinkedList<Task> doneList) {
        this.doneList = doneList;
    }

    public String getFileRegistered() {
        return fileRegistered;
    }

    public void setFileRegistered(String fileRegistered) {
        this.fileRegistered = fileRegistered;
    }

    public String getFileDone() {
        return fileDone;
    }

    public void setFileDone(String fileDone) {
        this.fileDone = fileDone;
    }
}
