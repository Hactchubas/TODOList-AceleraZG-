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

}
