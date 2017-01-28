package models;

/**
 * Created by Gebruiker on 25-1-2017.
 */
public class Registration {
    private CourseInstance courseInstance;
    private Student student;

    public CourseInstance getCourseInstance() {
        return courseInstance;
    }

    public void setCourseInstance(CourseInstance courseInstance) {
        this.courseInstance = courseInstance;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
