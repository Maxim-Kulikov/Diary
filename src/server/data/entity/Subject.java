package server.data.entity;

import java.util.UUID;

public class Subject {

    private UUID subjectId;
    private String name;

    public Subject(UUID subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = UUID.randomUUID();
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
