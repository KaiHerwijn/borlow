package models;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 24-1-2017.
 */
public class Course {

    private String code;
    private String title;
    private int duration;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
