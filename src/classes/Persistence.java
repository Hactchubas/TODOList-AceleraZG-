package classes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class Persistence {

    public static void readTasks(LinkedList<Task> todoList, String fileToRead) throws IOException {
        File file = new File(fileToRead);
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
        }

        br.close();
    }

    public static void writeTasks(LinkedList<Task> todoList, String fileToWrite) throws IOException{

        File file = new File(fileToWrite);
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
        }
        printWriter.close();

    }

    public  static TasksInfo initialize(String registeredTasksFile, String doneTasksFile) throws IOException {
        LinkedList<Task> todoList = new LinkedList<>();
        LinkedList<Task> doneList = new LinkedList<>();
        TasksInfo tasksInfo = new TasksInfo(todoList,doneList,registeredTasksFile, doneTasksFile);

        Persistence.readTasks(tasksInfo.todoList, tasksInfo.fileRegistered);
        Persistence.readTasks(tasksInfo.doneList, tasksInfo.fileDone);

        return tasksInfo;
    }

    public static void updateDoneTasks(TasksInfo tasksInfo){

        LinkedList<Task> toRemove = new LinkedList<>();
        for(Task task: tasksInfo.todoList){
            if(task.getStatus() == 2){
                toRemove.add(task);
                tasksInfo.doneList.add(task);

            }
        }
        tasksInfo.todoList.removeAll(toRemove);

        try {
            Persistence.writeTasks(tasksInfo.doneList, tasksInfo.fileDone);
            Persistence.writeTasks(tasksInfo.todoList, tasksInfo.fileRegistered);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
