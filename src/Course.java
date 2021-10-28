import java.util.ArrayList;

public class Course {
    private final String department;
    private final String number;
    private final ArrayList<Course> prerequisite;
    private final ArrayList<Course> corequisite;
    private boolean completed;
    private final String descrption;
    private final String title;

    public Course(String department, String number, String descrption, ArrayList<Course> prerequisite, ArrayList<Course> corequisite, boolean completed, String title) {
        this.department = department;
        this.number = number;
        this.descrption = descrption;
        this.prerequisite = prerequisite;
        this.corequisite = corequisite;
        this.completed = completed;
        this.title = title;
    }

    public Course(String department, String number, String descrption, String title) {
        this.department = department;
        this.number = number;
        this.descrption = descrption;
        this.completed = false;
        this.title = title;
        this.prerequisite = null;
        this.corequisite = null;
    }

    public boolean selectable(){
        return coMet() && preMet();
    }

    private boolean preMet(){
        if (prerequisite.isEmpty())
            return true;
        else {
            for (Course course : prerequisite) {
                if (!course.completed) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean coMet(){
        if (corequisite.isEmpty())
            return true;
        else {
            for (Course course : corequisite) {
                if (!course.completed) {
                    if (!course.selectable()) {
                        return false;
                    }
                    // return true;
                }
            }
            return true;
        }
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

    public String getDescrption() {
        return descrption;
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

    @Override
    public String toString() {
        return "Course{" +
                "department='" + department + '\'' +
                ", number='" + number + '\'' +
                ", prerequisite=" + prerequisite +
                ", corequisite=" + corequisite +
                ", completed=" + completed +
                ", descrption='" + descrption + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
