import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList<Task> todoList = new LinkedList<>();

        Persistence.readTasks(todoList);

        Menu menu = new Menu(todoList);
        menu.run();

        Persistence.writeTasks(todoList);

    }


}
