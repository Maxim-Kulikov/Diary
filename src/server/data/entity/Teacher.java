package server.data.entity;

import java.io.IOException;

public class Teacher extends User {

    private Subject subject;
    private final Pupil pupil = new Pupil();

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
