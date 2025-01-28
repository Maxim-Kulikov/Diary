package server.data.entity;

import java.util.UUID;

public class Class {
    private UUID id;
    private String letter;
    private int number;
    private User teacher_id;

    public Class(UUID id, String letter, int number, User teacher_id) {
        this.id = id;
        this.letter = letter;
        this.number = number;
        this.teacher_id = teacher_id;
    }

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(User teacher_id) {
        this.teacher_id = teacher_id;
    }
}
