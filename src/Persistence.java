
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class Persistence {

    public static void readTasks(LinkedList<Task> todoList) throws IOException {
        File file = new File("src/data/registeredTasks.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] taskFields = line.split("/",0);
            String[] dueFields = taskFields[2].split(",",3);
            int year = Integer.parseInt(dueFields[0])-1900;
            int month = Integer.parseInt(dueFields[1])-1;
            int day = Integer.parseInt(dueFields[2]);
            Date due = new Date(year,month,day);
            int priority = Integer.parseInt(taskFields[3]);
            int status = Integer.parseInt(taskFields[5]);
            Task task =  new Task(taskFields[0],taskFields[1],due,priority,taskFields[4],status);

            todoList.add(task);
//            System.out.println(task.completeInfo());
        }

        br.close();
    }

    public static void writeTasks(LinkedList<Task> todoList) throws IOException{

        File file = new File("src/data/registeredTasks.txt");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        for(Task task : todoList){

            SimpleDateFormat formatDue = new SimpleDateFormat("yyy,MM,dd");
            String formattedDue = formatDue.format(task.getDue());


            printWriter.println(task.getName()+"/"
                            + task.getDescription()
                            +"/"+formattedDue
                            +"/"+task.getPriority()
                            +"/"+task.getCategory()
                            +"/"+task.getStatus());
//            System.out.println(task.completeInfo());
        }
//        System.out.println("Done");

        printWriter.close();

    }
}
