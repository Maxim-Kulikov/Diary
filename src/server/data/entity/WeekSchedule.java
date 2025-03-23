package server.data.entity;

import java.util.Objects;
import java.util.UUID;

public class WeekSchedule {

    private UUID lesson_id;
    private UUID id;
    private Integer week_day_id;
    private Integer lesson_number;

    public WeekSchedule(UUID id, Integer week_day_id, UUID lesson_id, Integer lesson_number) {
        this.lesson_id = lesson_id;
        this.id = id;
        this.week_day_id = week_day_id;
        this.lesson_number = lesson_number;
    }

    public WeekSchedule(){}

    public UUID getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(UUID lesson_id) {
        this.lesson_id = lesson_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getWeek_day_id() {
        return week_day_id;
    }

    public void setWeek_day_id(Integer week_day_id) {
        this.week_day_id = week_day_id;
    }

    public Integer getLesson_number() {
        return lesson_number;
    }

    public void setLesson_number(Integer lesson_number) {
        this.lesson_number = lesson_number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WeekSchedule that = (WeekSchedule) o;
        return Objects.equals(getLesson_id(), that.getLesson_id()) && Objects.equals(getId(), that.getId()) && Objects.equals(getWeek_day_id(), that.getWeek_day_id()) && Objects.equals(getLesson_number(), that.getLesson_number());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLesson_id(), getId(), getWeek_day_id(), getLesson_number());
    }
}
