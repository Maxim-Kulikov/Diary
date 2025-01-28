package server.data.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Lesson {
    private UUID id;
    private Class class_id;
    private Teacher_Of_Subject teacher_of_subject_id;
    private LocalDateTime date;

    public Lesson(UUID id, Class class_id, Teacher_Of_Subject teacher_of_subject_id, LocalDateTime date) {
        this.id = id;
        this.class_id = class_id;
        this.teacher_of_subject_id = teacher_of_subject_id;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Class getClass_id() {
        return class_id;
    }

    public void setClass_id(Class class_id) {
        this.class_id = class_id;
    }

    public Teacher_Of_Subject getTeacher_of_subject_id() {
        return teacher_of_subject_id;
    }

    public void setTeacher_of_subject_id(Teacher_Of_Subject teacher_of_subject_id) {
        this.teacher_of_subject_id = teacher_of_subject_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
