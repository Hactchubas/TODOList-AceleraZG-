package classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private Date due;
    private int priority;
    private String category;
    private int status;

    public Task(String name, String description, Date due, int priority, String category, int status) {
        this.name = name;
        this.description = description;
        this.due = due;
        this.priority = priority > 5 ? 0 : priority < 0 ? 0 : priority;
        this.category = category;
        this.status = status > 2 ? 0 : status < 0 ? 0 : status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        int statusToParse = this.getStatus();
        String statusString = switch (statusToParse) {
            case 1 -> "DOING";
            case 2 -> "DONE";
            default -> "TO DO";
        };

        SimpleDateFormat formatDue = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDue = formatDue.format(this.getDue());

        return  "Status= " + statusString + "\n" +
                "  Name:        " + name + "\n" +
                "  Due:         " + formattedDue + "\n" +
                "  Category:    " + category + "\n";
    }

    public String completeInfo(){
        String completeInfo = this.toString();
        return  completeInfo +
                "  Priority:    " + this.priority + "\n" +
                "  Description: " + this.description + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void copyTaskInfo(Task taskAux){
        this.setName(taskAux.getName());
        this.setDescription((taskAux.getDescription()));
        this.setDue(taskAux.getDue());
        this.setPriority(taskAux.getPriority());
        this.setCategory(taskAux.getCategory());
        this.setStatus(taskAux.getStatus());
    }
}