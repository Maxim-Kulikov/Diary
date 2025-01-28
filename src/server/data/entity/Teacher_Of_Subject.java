package server.data.entity;

import java.util.UUID;

public class Teacher_Of_Subject {
    private UUID id;
    private Subject subject_id;
    private User teacher_id;

    public Teacher_Of_Subject(UUID id, Subject subject_id, User teacher_id) {
        this.id = id;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Subject getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Subject subject_id) {
        this.subject_id = subject_id;
    }

    public User getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(User teacher_id) {
        this.teacher_id = teacher_id;
    }
}
