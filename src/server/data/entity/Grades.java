package server.data.entity;

import java.util.UUID;

public class Grades {
    private UUID id;
    private UUID pupil_id;
    private String grade;
    private UUID lesson_id;

    public Grades(UUID id, UUID pupil_id, String grade, UUID lesson_id) {
        this.id = id;
        this.pupil_id = pupil_id;
        this.grade = grade;
        this.lesson_id = lesson_id;
    }

    public Grades() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPupil_id() {
        return pupil_id;
    }

    public void setPupil_id(UUID pupil_id) {
        this.pupil_id = pupil_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public UUID getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(UUID lesson_id) {
        this.lesson_id = lesson_id;
    }
}
