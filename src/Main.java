import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<Task> todoList = new LinkedList<>();
        Menu menu = new Menu(todoList);
        
        menu.run();

    }
}
