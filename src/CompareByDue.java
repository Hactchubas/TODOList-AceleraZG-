import java.util.Comparator;

public class CompareByDue  implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        // Search better way of reversion
        return task1.getDue().compareTo(task2.getDue());
        }
}
