package comparators;

import classes.Task;

import java.util.Comparator;

public class CompareByCategory implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        return String.CASE_INSENSITIVE_ORDER.compare(task1.getCategory(), task2.getCategory());
    }
}
