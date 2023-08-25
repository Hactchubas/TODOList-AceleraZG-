import java.time.LocalDate;
import java.util.*;

public class Manager {
    LinkedList<Task> todoList;
    public Manager(LinkedList<Task> todoList) {
        Collections.sort(todoList, new CompareByStatus());
        this.todoList = todoList;
    }

    public void listTasks(int listBy){
        if(listBy == 2){
            Collections.sort(todoList, new CompareByCategory());
        } else if (listBy == 1) {
            Collections.sort(todoList,new CompareByPriority());
        } else {
            Collections.sort(todoList, new CompareByStatus());
        }
        int taskOrderNum = 1;
        for(Task task : todoList) {
            System.out.println(taskOrderNum + " " + task);
            taskOrderNum++;
        }
    }

    public void addTask(){
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        System.out.println("New task:\n" +
                "Name: ");

        String name = scannerString.nextLine();

        System.out.println("Description: ");
        String description = scannerString.nextLine();

        System.out.println("Due (day/month): ");
        Date due;
        do {
            String dueString = scannerString.nextLine();
            String[] dateAux = dueString.split("/", 2);
            try {
                int[] date = {Integer.parseInt(dateAux[1]) - 1, Integer.parseInt(dateAux[0])};
                LocalDate now = LocalDate.now();
                int year = now.getYear() - 1900;
                due = new Date(year, date[0], date[1]);
                break;
            } catch (ArrayIndexOutOfBoundsException err) {
                System.out.println("Please enter a valid date format [day/month]: ");
            }
        } while (true);


        System.out.println("Priority[int]: ");
        int priority;
        do {
            String priorityString = scannerString.nextLine();
            try {
                priority = Integer.parseInt(priorityString);
                break;
            } catch (NumberFormatException err){
                System.out.println("Please enter a valid number [1-5]: ");
            }
        } while (true);


        System.out.println("Category: ");
        String category = scannerString.nextLine();

        System.out.println("Status[int]: ");
        int status;
        do {
            String statusString = scannerString.nextLine();
            try{
                status = Integer.parseInt(statusString);
                break;
            } catch (NumberFormatException err){
                System.out.println("Please enter a valid number [0-2]: ");
            }
        } while (true);

        Task newTask = new Task(name, description, due, priority, category, status);

        todoList.add(newTask);
        Collections.sort(todoList, new CompareByPriority());
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
            Scanner scannerString = new Scanner(System.in);
            Task editingTask = todoList.get(index);

            System.out.println("Name [" + editingTask.getName() + "]: ");
            String editingName = scannerString.nextLine();
            if(!editingName.isEmpty()){
                editingTask.setName(editingName);
            }

            System.out.println("Description  [" + editingTask.getDescription() + "]: ");
            String editingDescription = scannerString.nextLine();
            if(!editingDescription.isEmpty()){
                editingTask.setDescription(editingDescription);
            }

            System.out.println("Due (day/month) [" + editingTask.getDue() + "]: ");
            String editingDueString = scannerString.nextLine();
            if(!editingDueString.isEmpty()){
                String[] dateAux = editingDueString.split("/",2);
                int[] date = {Integer.parseInt(dateAux[1])-1, Integer.parseInt(dateAux[0])};
                LocalDate now = LocalDate.now();
                int year = now.getYear();
                Date editingDue = new Date(year,date[0],date[1]);
                editingTask.setDue(editingDue);
            }

            System.out.println("Priority [" + editingTask.getPriority() + "]: ");
            Integer intEditingPriority = null;
            do {
                String editingPriority = scannerString.nextLine();

                if(!editingPriority.isEmpty()){

                    try{
                        intEditingPriority = Integer.parseInt(editingPriority);
                        editingTask.setPriority(intEditingPriority);
                    } catch (NumberFormatException exp) {
                        System.out.println(editingPriority + " is not a valid input\n" +
                                "Priority [" + editingTask.getPriority() + "]: ");

                    }
                }else {
                    break;
                }
            }while (intEditingPriority == null);

            System.out.println("Category [" + editingTask.getCategory() + "]: ");
            String editingCategory = scannerString.nextLine();
            if (!editingCategory.isEmpty()){
                editingTask.setCategory(editingCategory);
            }

            System.out.println("Status [" + editingTask.getStatus() + "]");
            Integer intEditingStatus = null;
            do {
                String editingStatus = scannerString.nextLine();

                if(!editingStatus.isEmpty()){
                    try{
                        intEditingStatus = Integer.parseInt(editingStatus);
                        editingTask.setStatus(intEditingStatus);
                    } catch (NumberFormatException exp) {
                        System.out.println(editingStatus + " is not a valid input\n" +
                                "Status [" + editingTask.getStatus() + "]: ");

                    }
                } else {
                    break;
                }
            }while (intEditingStatus == null);
            Collections.sort(todoList, new CompareByPriority());
        }else {
            System.out.println("Could not find given task");
        }


    }

}
