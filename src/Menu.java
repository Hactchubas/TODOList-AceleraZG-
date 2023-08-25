import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    LinkedList<Task> todoList;
    public Menu(LinkedList<Task> todoList){
        this.todoList = todoList;
    }

    public void run(){
        Date now = new Date(2023-1900,8,20);
        Task task1 = new Task("Varrer a casa1","Varrer toda casa", now,1, "Casad", 4);
        Task task2 = new Task("Varrer a casa2","Varrer toda casa", now,2, "Casac", 2);
        Task task3 = new Task("Varrer a casa3","Varrer toda casa", now,3, "Casab", 0);
        Task task4 = new Task("Varrer a casa4","Varrer toda casa", now,4, "Casaa", 1);
        todoList.add(task3);
        todoList.add(task4);
        todoList.add(task1);
        todoList.add(task2);
        Collections.sort(todoList,new CompareByPriority());

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
                    System.out.println("""
                            List by:
                               Status, press 0
                               Priority, press 1
                               Category, press 2""");
                    String listByString = scanner.nextLine();
                    int listBy;
                    do {
                        try {
                            listBy = Integer.parseInt(listByString);
                            break;
                        } catch (NumberFormatException err) {
                            System.out.println("Please enter a valid number");
                        }
                    } while (true);
                    manager.listTasks(listBy);
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
