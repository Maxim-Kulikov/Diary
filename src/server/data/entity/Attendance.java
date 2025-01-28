package server.data.entity;

import java.util.UUID;

public class Attendance {
    private UUID id;
    private Lesson lesson_id;
    private User pupil_id;
    private boolean is_abscent;

    public Attendance(UUID id, Lesson lesson_id, User pupil_id, boolean is_abscent) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.pupil_id = pupil_id;
        this.is_abscent = is_abscent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Lesson getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Lesson lesson_id) {
        this.lesson_id = lesson_id;
    }

    public User getPupil_id() {
        return pupil_id;
    }

    public void setPupil_id(User pupil_id) {
        this.pupil_id = pupil_id;
    }

    public boolean isIs_abscent() {
        return is_abscent;
    }

    public void setIs_abscent(boolean is_abscent) {
        this.is_abscent = is_abscent;
    }
}
