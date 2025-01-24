package server.data.entity;

import java.util.UUID;

public class Grades {
    private UUID id;
    private User pupil_id;
    private int grade;
    private Lesson lesson_id;

    public Grades(UUID id, User pupil_id, int grade, Lesson lesson_id) {
        this.id = id;
        this.pupil_id = pupil_id;
        this.grade = grade;
        this.lesson_id = lesson_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getPupil_id() {
        return pupil_id;
    }

    public void setPupil_id(User pupil_id) {
        this.pupil_id = pupil_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Lesson getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Lesson lesson_id) {
        this.lesson_id = lesson_id;
    }
}
