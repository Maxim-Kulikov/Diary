package server.data.entity;

import java.util.Objects;
import java.util.UUID;

public class TeacherOfSubject {

    private UUID id;
    private UUID teacherId;
    private UUID subjectId;

    public TeacherOfSubject(UUID id, UUID teacherId, UUID subjectId) {
        this.id = id;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public TeacherOfSubject() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeacherOfSubject that = (TeacherOfSubject) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTeacherId(), that.getTeacherId()) && Objects.equals(getSubjectId(), that.getSubjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTeacherId(), getSubjectId());
    }
}
