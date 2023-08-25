import java.util.Comparator;

public class CompareByStatus implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return Integer.compare(task1.getStatus(), task2.getStatus());
    }
}
