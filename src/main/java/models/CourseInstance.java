package models;

import java.sql.Date;

/**
 * Created by Gebruiker on 24-1-2017.
 */

public class CourseInstance {
    private String code;
    private Date start;
    private Course parentCourse;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Course getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(Course parentCourse) {
        this.parentCourse = parentCourse;
    }
}
