package server.data.entity;

import server.business.enums.RoleEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pupil extends User {

    private double averageGrade;
    private final RoleEnum role = setRole();
    private String name;
    private Subject subject;

    public double calculateAverageGrade() {
        return 1.01;
    }

    public RoleEnum setRole() {
        setRole(RoleEnum.PUPIL);
        return role;
    }
}
