
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    LinkedList<Task> todoList;
    Scanner scanner = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    public Menu(LinkedList<Task> todoList){
        this.todoList = todoList;
    }

    public void run(){
        Collections.sort(todoList,new CompareByPriority());
        Manager manager = new Manager(todoList);

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
                    while (true){
                        String listByString = scanner.nextLine();
                        try {
                            listBy = Integer.parseInt(listByString);
                            break;
                        } catch (NumberFormatException err) {
                            System.out.println("Please enter a valid number");
                        }
                    }
                    manager.listTasks(listBy);

                    while (true){
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
                    }

                    break;
                case "2":
                    Task newTask = inputNewTask();
                    manager.addTask(newTask);
                    break;
                case "3":
                    System.out.println("Task name you'd like to edit: ");
                    for (Task task : todoList){
                        System.out.println(task.getName());
                    }
                    String name = scanner.nextLine();

                    int editingTaskOfIndex =  manager.findByName(name);
                    System.out.println(editingTaskOfIndex);


                    if(editingTaskOfIndex >= 0){
                        Task taskAux = inputEditTask(manager, editingTaskOfIndex);
                        manager.editTask(taskAux, editingTaskOfIndex);
                    }else {
                        System.out.println("Could not find given task");
                    }

                    break;
                case "4":
                    int removingTaskOfIndex = inputRemoveTask(manager);
                    manager.removeTask(removingTaskOfIndex);
                    break;
                case "5":
                    run = false;
                    scanner.close();
                    break;
                default:
                    break;
            }

        }

    }

    private Task inputNewTask(){
        System.out.println("New task:\n" +
                "Name: ");

        String name = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Due (day/month/year): ");
        Date due;
        while (true) {
            String dueString = scanner.nextLine();
            String[] dateAux = dueString.split("/", 3);
            try {
                int[] date = {Integer.parseInt(dateAux[2])-1900,Integer.parseInt(dateAux[1]) - 1, Integer.parseInt(dateAux[0])};
//
                due = new Date(date[0], date[1], date[2]);
                break;
            } catch (ArrayIndexOutOfBoundsException err) {
                System.out.println("Please enter a valid date format [day/month/year]: ");
            }
        }


        System.out.println("Priority[int]: ");
        int priority;
        while (true) {
            String priorityString = scanner.nextLine();
            try {
                priority = Integer.parseInt(priorityString);
                break;
            } catch (NumberFormatException err){
                System.out.println("Please enter a valid number [1-5]: ");
            }
        }


        System.out.println("Category: ");
        String category = scanner.nextLine();

        System.out.println("Status[int]: ");
        int status;
        while (true) {
            String statusString = scanner.nextLine();
            try{
                status = Integer.parseInt(statusString);
                break;
            } catch (NumberFormatException err){
                System.out.println("Please enter a valid number [0-2]: ");
            }
        }

        Task newTask = new Task(name, description, due, priority, category, status);
        return  newTask;
    }
    private int inputRemoveTask(Manager manager){
        System.out.println("Task name you´d like to remove: ");
        for (Task task : todoList){
            System.out.println(task.getName());
        }
        String name = scanner.nextLine();

        return manager.findByName(name);
    }
    private Task inputEditTask(Manager manager, int index){
        Task taskAux = new Task("Aux","Aux", new Date(),0,"Aux", 0);

        Task editingTask = todoList.get(index);

        System.out.println("Name [" + editingTask.getName() + "]: ");
        String editingName = scanner.nextLine();
        if(!editingName.isEmpty()){
            taskAux.setName(editingName);
        } else{
            taskAux.setName(editingTask.getName());
        }

        System.out.println("Description  [" + editingTask.getDescription() + "]: ");
        String editingDescription = scanner.nextLine();
        if(!editingDescription.isEmpty()){
            taskAux.setDescription(editingDescription);
        }else{
            taskAux.setDescription(editingTask.getDescription());
        }

        SimpleDateFormat formatDue = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDue = formatDue.format(editingTask.getDue());

        System.out.println("Due (day/month) [" + formattedDue + "]: ");
        String editingDueString = scanner.nextLine();
        if(!editingDueString.isEmpty()){
            String[] dateAux = editingDueString.split("/",2);
            int[] date = {Integer.parseInt(dateAux[1])-1, Integer.parseInt(dateAux[0])};
            LocalDate now = LocalDate.now();
            int year = now.getYear();
            Date editingDue = new Date(year,date[0],date[1]);
            taskAux.setDue(editingDue);
        }else{
            taskAux.setDue(editingTask.getDue());
        }

        System.out.println("Priority [" + editingTask.getPriority() + "]: ");
        Integer intEditingPriority = null;
        while (intEditingPriority == null) {
            String editingPriority = scanner.nextLine();

            if(!editingPriority.isEmpty()){

                try{
                    intEditingPriority = Integer.parseInt(editingPriority);
                    taskAux.setPriority(intEditingPriority);
                } catch (NumberFormatException exp) {
                    System.out.println(editingPriority + " is not a valid input\n" +
                            "Priority [" + editingTask.getPriority() + "]: ");

                }
            }else {
                taskAux.setPriority(editingTask.getPriority());
                break;
            }
        }

        System.out.println("Category [" + editingTask.getCategory() + "]: ");
        String editingCategory = scanner.nextLine();
        if (!editingCategory.isEmpty()){
            taskAux.setCategory(editingCategory);
        }else{
            taskAux.setCategory(editingTask.getCategory());
        }

        System.out.println("Status [" + editingTask.getStatus() + "]");
        Integer intEditingStatus = null;
        while (intEditingStatus == null){
            String editingStatus = scanner.nextLine();

            if(!editingStatus.isEmpty()){
                try{
                    intEditingStatus = Integer.parseInt(editingStatus);
                    taskAux.setStatus(intEditingStatus);
                } catch (NumberFormatException exp) {
                    System.out.println(editingStatus + " is not a valid input\n" +
                            "Status [" + editingTask.getStatus() + "]: ");

                }
            } else {
                taskAux.setStatus(editingTask.getStatus());
                break;
            }
        }
        Collections.sort(todoList, new CompareByPriority());

        return taskAux;
    }
}
