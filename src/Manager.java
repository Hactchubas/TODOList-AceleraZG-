import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Manager {
    LinkedList<Task> todoList;
    public Manager(LinkedList<Task> todoList) {
        this.todoList = todoList;
    }

    public void listTasks(){
        for(Task task : todoList) {
            System.out.println(task);
        }
    }
    public void addTask(){
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        System.out.println("New task:\n" +
                "   Name: ");

        String name = scannerString.nextLine();

        System.out.println("    Description: ");
        String description = scannerString.nextLine();

        System.out.println("    Due (day/month): ");
        String dueString = scannerString.nextLine();
        String[] dateAux = dueString.split("/",2);
        int[] date = {Integer.parseInt(dateAux[1]), Integer.parseInt(dateAux[0])};
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        Date due = new Date(year,date[0],date[1]);



        System.out.println("    Priority[int]: ");
        int priority = scannerInt.nextInt();

        System.out.println("    Category: ");
        String category = scannerString.nextLine();

        System.out.println("    Status[int]: ");
        int status = scannerInt.nextInt();

        Task newTask = new Task(name, description, due, priority, category, status);

        todoList.add(newTask);

    }

    public int findByName(String name) {
        int i = 0;
        for (Task task : todoList) {
            if (task.getName().equals(name)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void removeTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Task name you´d like to remove: ");
        for (Task task : todoList){
            System.out.println(task.getName());
        }
        String name = scanner.nextLine();

        int index = this.findByName(name);

        if(index >= 0){
            todoList.remove(index);
        }else {
            System.out.println("Could not find given task");
        }
    }

    public void editTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Task name you'd like to edit: ");
        for (Task task : todoList){
            System.out.println(task.getName());
        }
        String name = scanner.nextLine();

        int index =  this.findByName(name);

        if(index >= 0){
            this.addTask();
            todoList.remove(index);
        }else {
            System.out.println("Could not find given task");
        }

    }

}
