package server.data.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Lesson {
    private java.util.UUID id;
    private java.util.UUID class_id;
    private UUID teacher_of_subject_id;
    private UUID subject_id;
    private LocalDateTime date;

    public Lesson(java.util.UUID id, java.util.UUID class_id, UUID teacher_of_subject_id, UUID subject_id, LocalDateTime date) {
        this.id = id;
        this.class_id = class_id;
        this.teacher_of_subject_id = teacher_of_subject_id;
        this.date = date;
        this.subject_id = subject_id;
    }

    public Lesson() {}

    public UUID getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(UUID subject_id) {
        this.subject_id = subject_id;
    }

    public java.util.UUID getId() {
        return id;
    }

    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public java.util.UUID getClass_id() {
        return class_id;
    }

    public void setClass_id(java.util.UUID class_id) {
        this.class_id = class_id;
    }

    public UUID getTeacher_of_subject_id() {
        return teacher_of_subject_id;
    }

    public void setTeacher_of_subject_id(UUID UUID_id) {
        this.teacher_of_subject_id = UUID_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(getId(), lesson.getId()) && Objects.equals(getClass_id(), lesson.getClass_id()) && Objects.equals(getTeacher_of_subject_id(), lesson.getTeacher_of_subject_id()) && Objects.equals(getSubject_id(), lesson.getSubject_id()) && Objects.equals(getDate(), lesson.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClass_id(), getTeacher_of_subject_id(), getSubject_id(), getDate());
    }
}
