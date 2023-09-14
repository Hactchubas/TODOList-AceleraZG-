
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
        Task task1 = new Task("Varrer a casa","Varrer toda casa antes que minha mulher chegue", now,1, "Casa", 4);
        Task task2 = new Task("Passear com a Brisa","Passear com a Brisa pra bixinha fazer as necessidades dela", now,2, "Brisa", 2);
        Task task3 = new Task("Terminar a atividade da trilha de java","Terminar de fazer o MVP da aplicação de TODOList em java da trilha de java do AceleraZg", now,5, "AceleraZg", 1);
        Task task4 = new Task("Assistir Barbie","Assistir barbie no cinema Iguatemi", now,1, "Lazer", 0);
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

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("""
                            List by:
                               Status, press 0
                               Priority, press 1
                               Category, press 2""");

                    int listBy;
                    do {
                        String listByString = scanner.nextLine();
                        try {
                            listBy = Integer.parseInt(listByString);
                            break;
                        } catch (NumberFormatException err) {
                            System.out.println("Please enter a valid number");
                        }
                    } while (true);
                    manager.listTasks(listBy);

                    do {
                        System.out.println("To see task full info, write it's name or 'quit' to exit: ");
                        String name = scanner.nextLine();
                        if (name.equals("quit")) {
                            break;
                        } else {
                            int index = manager.findByName(name);
                            if (index == -1) {
                                System.out.println("Could not find given task name");
                            }else {
                                System.out.println(todoList.get(index).completeInfo());
                            }
                        }
                    } while (true);

                    break;
                case "2":
                    manager.addTask();
                    break;
                case "3":
                    manager.editTask();
                    break;
                case "4":
                    manager.removeTask();
                    break;
                case "5":
                    run = false;
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }
}
