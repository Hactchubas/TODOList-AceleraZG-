import java.io.IOException;
import java.util.*;

public class Manager {
    LinkedList<Task> todoList;
    public Manager(LinkedList<Task> todoList) {
        todoList.sort(new CompareByStatus());
        this.todoList = todoList;
    }

    public void listTasks(int listBy){
        if(listBy == 2){
            todoList.sort(new CompareByCategory());
        } else if (listBy == 1) {
            todoList.sort(new CompareByPriority());
        } else {
            todoList.sort(new CompareByStatus());
        }
        // int taskOrderNum = 1;
        for(Task task : todoList) {
            System.out.println(task);
        }
    }

    public void addTask(Task newTask){

        todoList.add(newTask);
        todoList.sort(new CompareByPriority());
        try {
            Persistence.writeTasks(todoList);
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
            Persistence.writeTasks(todoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTask(Task taskAux, int index){
        
        Task editingTask = todoList.get(index);
        editingTask.copyTaskInfo(taskAux);

        try {
            Persistence.writeTasks(todoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
