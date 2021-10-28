import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Course {
    private final String department;
    private final String number;
    private ArrayList<Course> prerequisite;
    private ArrayList<Course> corequisite;
    private boolean completed;
    private final String description;
    private final String title;

    public Course(String department, String number, String description, ArrayList<Course> prerequisite, ArrayList<Course> corequisite, boolean completed, String title) {
        this.department = department;
        this.number = number;
        this.description = description;
        this.prerequisite = prerequisite;
        this.corequisite = corequisite;
        this.completed = completed;
        this.title = title;
    }

    public Course(String department, String number, String description, String title) {
        this.department = department;
        this.number = number;
        this.description = description;
        this.completed = false;
        this.title = title;
        this.prerequisite = null;
        this.corequisite = null;
    }

    public @NotNull ArrayList<Course> select(){
        ArrayList<Course> arr = coMet();
        arr.addAll(preMet());
        return arr;
    }

    private @NotNull ArrayList<Course> preMet() {
        ArrayList<Course> arr = new ArrayList<>();
        for (Course course : prerequisite)
            if (!course.completed) {
                arr.add(course);
                arr.addAll(course.select());
            }
        return arr;
    }

    private @NotNull ArrayList<Course> coMet(){
        ArrayList<Course> arr = new ArrayList<>();
        for (Course course : corequisite)
            if (!course.completed) {
                arr.add(course);
                arr.addAll(course.select());
            }
        return arr;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public ArrayList<Course> getPrerequisite() {
        return prerequisite;
    }

    public ArrayList<Course> getCorequisite() {
        return corequisite;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setPrerequisite(ArrayList<Course> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public void setCorequisite(ArrayList<Course> corequisite) {
        this.corequisite = corequisite;
    }

    @Override
    public String toString() {
        return department + " " + number + " " + title;
    }
}
