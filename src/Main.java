import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        LinkedList<Task> todoList = new LinkedList<>();
//        Persistence.readTasks(todoList);
        Menu menu = new Menu(todoList);

        menu.run();


    }


}
