import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    LinkedList<Task> todoList;
    public Menu(LinkedList<Task> todoList){
        this.todoList = todoList;
    }

    public void run(){
        Date now = new Date(2023,8,20);
        Task task = new Task("Varrer a casa","Varrer toda casa", now,4, "Casa", 0);
        todoList.add(task);

        Manager manager = new Manager(todoList);
        Scanner scanner = new Scanner(System.in);

        boolean run= true;
        while (run) {
            System.out.println("""
                    TODO List handler:
                       Press 1 to see the list
                       Press 2 to add new Task
                       Press 3 to edit Task
                       Press 4 to remove Task
                       Press 5 to exit"""
            );

            switch (scanner.nextInt()) {
                case 1:
                    manager.listTasks();
                    break;
                case 2:
                    manager.addTask();
                    break;
                case 3:
                    manager.editTask();
                    break;
                case 4:
                    manager.removeTask();
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }
}
