import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {

        TasksInfo tasksInfo = Persistence.initialize("src/data/registeredTasks.txt","src/data/doneTasks.txt");

        Menu menu = new Menu(tasksInfo);
        menu.run();

        Persistence.updateDoneTasks(tasksInfo);
    }
}
