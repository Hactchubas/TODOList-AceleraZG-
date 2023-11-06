package comparators;

import classes.Task;

import java.util.Comparator;

public class CompareByPriority implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        // Search better way of reversion
        return Integer.compare(task1.getPriority(),task2.getPriority())*(-1);
    }
}
