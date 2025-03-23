package server.data.entity;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

public class Absense {
    private UUID id;
    private UUID lesson_id;
    private UUID pupil_id;
    private boolean is_absent;
    private Date date;

    public Absense(UUID id, UUID lesson_id, UUID pupil_id, boolean is_absent) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.pupil_id = pupil_id;
        this.is_absent = is_absent;
    }

    public boolean isIs_absent() {
        return is_absent;
    }

    public void setIs_absent(boolean is_absent) {
        this.is_absent = is_absent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Absense() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(UUID lesson_id) {
        this.lesson_id = lesson_id;
    }

    public UUID getPupil_id() {
        return pupil_id;
    }

    public void setPupil_id(UUID pupil_id) {
        this.pupil_id = pupil_id;
    }

    public boolean is_absent() {
        return is_absent;
    }

    public void setAbsence(boolean is_absent) {
        this.is_absent = is_absent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Absense that = (Absense) o;
        return isIs_absent() == that.isIs_absent() && Objects.equals(getId(), that.getId()) && Objects.equals(getLesson_id(), that.getLesson_id()) && Objects.equals(getPupil_id(), that.getPupil_id()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLesson_id(), getPupil_id(), isIs_absent(), getDate());
    }
}
