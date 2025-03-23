package server.data.entity;

import java.util.UUID;

public class SchoolClass {
    private UUID id;
    private String letter;
    private String number;
    private UUID teacher_id;

    public SchoolClass(UUID id, String letter, String number, UUID teacher_id) {
        this.id = id;
        this.letter = letter;
        this.number = number;
        this.teacher_id = teacher_id;
    }

    public SchoolClass() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UUID getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(UUID teacher_id) {
        this.teacher_id = teacher_id;
    }
}
