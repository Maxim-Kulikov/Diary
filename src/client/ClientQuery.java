package client;

import server.data.entity.*;
import server.presentation.controller.*;
import server.presentation.dto.request.*;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class ClientQuery {

    AuthenticationService authenticationService;
    private final UserController userController;
    private final AbsenseController absenseController;
    private final SchoolClassController schoolClassController;
    private final WeekScheduleController weekScheduleController;
    private final SubjectController subjectController;
    private final LessonController lessonController;
    private final GradesController gradesController;
    private final TeacherController teacherController;
    private final Scanner scanner = new Scanner(System.in);

    public ClientQuery() throws SQLException {
        authenticationService = new AuthenticationService();
        userController = new UserController();
        absenseController = new AbsenseController();
        schoolClassController = new SchoolClassController();
        weekScheduleController = new WeekScheduleController();
        subjectController = new SubjectController();
        lessonController = new LessonController();
        gradesController = new GradesController();
        teacherController = new TeacherController();
    }

    public void adminMethods() throws SQLException, ConstraintViolationException {
        System.out.println("Admin Menu:");
        System.out.println("1. Create new user account");
        System.out.println("2. Delete user");
        System.out.println("3. Update user");
        System.out.println("4. Update attendance");
        System.out.println("5. Calculate attendance");
        System.out.println("6. Find attendance");
        System.out.println("7. Give grade");
        System.out.println("8. Remove grade");
        System.out.println("9. Calculate average grade");
        System.out.println("10. Create lesson");
        System.out.println("11. Delete lesson");
        System.out.println("12. Find lessons by date");
        System.out.println("13. Add subject");
        System.out.println("14. Delete subject");
        System.out.println("15. Find all subjects");
        System.out.println("16. Add teacher");
        System.out.println("17. Delete teacher");
        System.out.println("18. Update teacher");
        System.out.println("19. Delete school class");
        System.out.println("20. Create school class");
        System.out.println("21. Find all pupils of class");
        System.out.println("22. Add week schedule");
        System.out.println("23. Remove lesson from schedule");
        System.out.println("24. Find all lessons in a day");

        int value = scanner.nextInt();
        scanner.nextLine();

        if (value == 1) {
            System.out.println("Enter user login:");
            String login = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter lastname:");
            String lastname = scanner.nextLine();
            System.out.println("Enter role ID:");
            UUID role = UUID.fromString(scanner.nextLine());
            UUID classId = null;
            if (!role.equals(Role.ADMIN.getUuid()) && !role.equals(Role.TEACHER.getUuid())) {
                System.out.println("Enter class ID:");
                classId = UUID.fromString(scanner.nextLine());
            }
            CreateUserRqDto createUserRqDto = new CreateUserRqDto(login, password, name, lastname, role, classId);
            userController.createAccount(createUserRqDto);
        }

        if (value == 2) {
            System.out.println("Enter user login to delete:");
            String login = scanner.nextLine();
            userController.deleteUser(userController.findUserByLogin(login).getResult().orElse(null));
        }

        if (value == 3) {
            System.out.println("Enter user login to update:");
            String login = scanner.nextLine();
            userController.updateUser(login);
        }

        if (value == 4) {
            System.out.println("Enter attendance ID:");
            String id = scanner.nextLine();
            System.out.println("Enter absence:");
            String absence = scanner.nextLine();
            absenseController.updateAttendance(absenseController.findAttendance(UUID.fromString(id)).getResult().orElse(null), Boolean.parseBoolean(absence));
        }

        if (value == 5) {
            System.out.println("Enter class ID:");
            String classId = scanner.nextLine();
            System.out.println("Enter pupil ID:");
            String pupilId = scanner.nextLine();
            System.out.println(absenseController.calculateAttendance(userController.findUserById(UUID.fromString(pupilId)).getResult().orElse(null), UUID.fromString(classId)));
        }

        if (value == 6) {
            System.out.println("Enter attendance ID:");
            String pupilId = scanner.nextLine();
            System.out.println(absenseController.findAttendance(UUID.fromString(pupilId)));
        }

        if (value == 7) {
            System.out.println("Enter lesson ID:");
            String lessonId = scanner.nextLine();
            System.out.println("Enter pupil ID:");
            String pupilId = scanner.nextLine();
            System.out.println("Enter grade:");
            String grade = scanner.nextLine();
            GradeRqDto gradeRqDto = new GradeRqDto(UUID.fromString(pupilId), UUID.fromString(lessonId), grade);
            gradesController.giveGrade(gradeRqDto);
        }

        if (value == 8) {
            System.out.println("Enter grade ID:");
            String Id = scanner.nextLine();
            System.out.println("Enter lesson ID:");
            String lessonId = scanner.nextLine();
            System.out.println("Enter pupil ID:");
            String pupilId = scanner.nextLine();
            System.out.println("Enter grade:");
            String grade = scanner.nextLine();
            Grades grades = new Grades(UUID.fromString(Id), UUID.fromString(pupilId), grade, UUID.fromString(lessonId));
            gradesController.removeGrade(grades);
        }

        if (value == 9) {
            System.out.println("Enter login:");
            String login = scanner.nextLine();
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            User user = userController.findUserByLogin(login).getResult().orElse(null);
            System.out.println(gradesController.calculateAverageGrade(user, UUID.fromString(subjectId)).getResult());
        }
        if (value == 10) {
            System.out.println("Enter class ID:");
            String classId = scanner.nextLine();
            System.out.println("Enter teacher ID:");
            String teacherId = scanner.nextLine();
            System.out.println("Enter date (YYYY-MM-DDTHH:MM:SS format):");
            String date = scanner.nextLine();
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            LessonRqDto lessonRqDto = new LessonRqDto(UUID.fromString(classId), UUID.fromString(teacherId), LocalDateTime.parse(date), UUID.fromString(subjectId));
            lessonController.createLesson(lessonRqDto);
        }

        if (value == 11) {
            System.out.println("Enter lesson ID:");
            String id = scanner.nextLine();
            Lesson lesson = lessonController.findLessonById(UUID.fromString(id)).getResult().orElse(null);
            lessonController.deleteLesson(lesson);
        }

        if (value == 12) {
            System.out.println("Enter date (YYYY-MM-DDTHH:MM:SS format):");
            String date = scanner.nextLine();
            System.out.println(lessonController.findAllLessonsByDate(LocalDateTime.parse(date)));
        }

        if (value == 13) {
            System.out.println("Enter subject name:");
            String subjectName = scanner.nextLine();
            SubjectRqDto subjectRqDto = new SubjectRqDto(subjectName);
            subjectController.addSubject(subjectRqDto);
        }

        if (value == 14) {
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            subjectController.deleteSubject(subjectController.findSubjectById(UUID.fromString(subjectId)).getResult().orElse(null));
        }

        if (value == 15) {
            System.out.println(subjectController.findAllSubjects());
        }

        if (value == 16) {
            System.out.println("Enter subject ID: ");
            String subjectId = scanner.nextLine();
            System.out.println("Enter teacher ID: ");
            String teacherId = scanner.nextLine();
            TeacherRqDto teacherRqDto = new TeacherRqDto(UUID.fromString(subjectId), UUID.fromString(teacherId));
            teacherController.addTeacher(teacherRqDto);
        }
        if (value == 17) {
            System.out.println("Enter teacherOfSubject ID:");
            String teacherId = scanner.nextLine();
            TeacherOfSubject teacherOfSubject = teacherController.getTeacherById(UUID.fromString(teacherId)).getResult().orElse(null);
            teacherController.deleteTeacher(teacherOfSubject);
        }
        if (value == 18) {
            System.out.println("Enter teacherOfSubject ID:");
            String teacherId = scanner.nextLine();
            TeacherOfSubject teacherOfSubject = teacherController.getTeacherById(UUID.fromString(teacherId)).getResult().orElse(null);
            teacherController.updateTeacher(teacherOfSubject);
        }
        if (value == 19) {
            System.out.println("Enter class ID");
            String classId = scanner.nextLine();
            SchoolClass schoolClass = schoolClassController.findSchoolClassById(UUID.fromString(classId)).getResult().orElse(null);
            schoolClassController.deleteSchoolClass(schoolClass);
        }
        if (value == 20) {
            System.out.println("Enter class letter: ");
            String classLetter = scanner.nextLine();
            System.out.println("Enter class number: ");
            String classNumber = scanner.nextLine();
            System.out.println("Enter teacher ID");
            String teacherId = scanner.nextLine();
            SchoolClassRqDto schoolClassRqDto = new SchoolClassRqDto(classLetter, classNumber, UUID.fromString(teacherId));
            schoolClassController.createSchoolClass(schoolClassRqDto);
        }
        if (value == 21) {
            System.out.println("Enter class ID");
            String classId = scanner.nextLine();
            SchoolClass schoolClass = schoolClassController.findSchoolClassById(UUID.fromString(classId)).getResult().orElse(null);
            System.out.println(schoolClassController.findAllPupilsOfClass(schoolClass));
        }
        if (value == 22) {
            System.out.println("Enter day of week in CAPS: ");
            String dayOfWeek = scanner.nextLine();
            System.out.println("Enter lesson ID: ");
            String lessonId = scanner.nextLine();
            System.out.println("Enter lesson number: ");
            String lessonNumber = scanner.nextLine();
            weekScheduleController.addWeekSchedule(new WeekScheduleRqDto(Integer.valueOf(dayOfWeek), UUID.fromString(lessonId), Integer.valueOf(lessonNumber)));
        }
        if (value == 23) {
            System.out.println("Enter weekschedule ID: ");
            String weekScheduleId = scanner.nextLine();
            System.out.println("Enter day of week in CAPS: ");
            String dayOfWeek = scanner.nextLine();
            System.out.println("Enter lesson ID: ");
            String lessonId = scanner.nextLine();
            System.out.println("Enter lesson number: ");
            String lessonNumber = scanner.nextLine();
            weekScheduleController.removeLessonFromSchedule(new WeekSchedule(UUID.fromString(weekScheduleId), Integer.valueOf(dayOfWeek), UUID.fromString(lessonId), Integer.valueOf(lessonNumber)));
        }
        if (value == 24) {
            System.out.println("Enter your login:");
            String login = scanner.nextLine();
            UUID classId = userController.findUserByLogin(login).getResult().orElse(null).getClass_id();
            System.out.println("Enter day of week in CAPS");
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(scanner.nextLine());
            SchoolClass schoolClass = schoolClassController.findSchoolClassById(classId).getResult().orElse(null);
            System.out.println(weekScheduleController.findAllLessonsInADay(dayOfWeek, schoolClass));
        }
    }

    public void pupilMethods() throws SQLException {
        System.out.println("1. to view your grades");
        System.out.println("2. to view schedule for a specific day");
        System.out.println("3. to calculate your attendance");
        System.out.println("4. to calculate your grade");
        int value = scanner.nextInt();
        scanner.nextLine();

        if (value == 1) {
            System.out.println("Enter your login:");
            String login = scanner.nextLine();
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            System.out.println(gradesController.findAllGradesOfPupil(userController.findUserByLogin(login).getResult().orElse(null), UUID.fromString(subjectId)));
        }
        if (value == 2) {
            System.out.println("Enter your login:");
            String login = scanner.nextLine();
            UUID classId = userController.findUserByLogin(login).getResult().orElse(null).getClass_id();
            System.out.println("Enter day of week in CAPS");
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(scanner.nextLine());
            SchoolClass schoolClass = schoolClassController.findSchoolClassById(classId).getResult().orElse(null);
            System.out.println(weekScheduleController.findAllLessonsInADay(dayOfWeek, schoolClass));
        }
        if (value == 3) {
            System.out.println("Enter class ID:");
            String classId = scanner.nextLine();
            System.out.println("Enter login");
            String login = scanner.nextLine();
            System.out.println(absenseController.calculateAttendance(userController.findUserByLogin(login).getResult().orElse(null), UUID.fromString(classId)));
        }
        if (value == 4) {
            System.out.println("Enter login:");
            String login = scanner.nextLine();
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            User user = userController.findUserByLogin(login).getResult().orElse(null);
            System.out.println(gradesController.calculateAverageGrade(user, UUID.fromString(subjectId)).getResult());
        }
    }

    public void teacherMethods() throws SQLException, ConstraintViolationException {
        System.out.println("Teacher Menu:");
        System.out.println("1. Find all pupils of a class");
        System.out.println("2. Mark student absence");
        System.out.println("3. Update attendance");
        System.out.println("4. Calculate student attendance");
        System.out.println("5. Give a grade");
        System.out.println("6. Remove a grade");
        System.out.println("7. Calculate average grade for a student");
        System.out.println("8. Find all grades for a pupil in a subject");
        System.out.println("9. Create a lesson");
        System.out.println("10. Delete a lesson");
        System.out.println("11. Find lessons by date");
        System.out.println("12. Find all pupils of a class");

        int value = scanner.nextInt();
        scanner.nextLine();

        if (value == 1) {
            System.out.println("Enter class ID:");
            String id = scanner.nextLine();
            System.out.println(schoolClassController.findAllPupilsOfClass(schoolClassController.findSchoolClassById(UUID.fromString(id)).getResult().orElse(null)));
        }
        if (value == 2) {
            System.out.println("Enter pupil ID:");
            String pupilId = scanner.nextLine();
            System.out.println("Enter date (YYYY-MM-DDTHH:MM:SS format):");
            String date = scanner.nextLine();
            System.out.println("Enter lesson ID:");
            String lessonId = scanner.nextLine();
            AbsenseRqDto absenseRqDto = new AbsenseRqDto(UUID.fromString(lessonId), UUID.fromString(pupilId), true, Date.valueOf(date));
            absenseController.insertAbsence(absenseRqDto);
        }
        if (value == 3) {
            System.out.println("Enter attendance ID:");
            String id = scanner.nextLine();
            System.out.println("Enter absence: ");
            String absence = scanner.nextLine();
            absenseController.updateAttendance(absenseController.findAttendance(UUID.fromString(id)).getResult().orElse(null), Boolean.parseBoolean(absence));
        }
        if (value == 4) {
            System.out.println("Enter pupil ID:");
            String pupilId = scanner.nextLine();
            System.out.println("Enter class ID:");
            String classId = scanner.nextLine();
            System.out.println(absenseController.calculateAttendance(userController.findUserById(UUID.fromString(pupilId)).getResult().orElse(null), UUID.fromString(classId)));
        }
        if (value == 5) {
            System.out.println("Enter pupil ID:");
            String pupilId = scanner.nextLine();
            System.out.println("Enter lesson ID:");
            String lessonId = scanner.nextLine();
            System.out.println("Enter grade:");
            String grade = scanner.nextLine();
            GradeRqDto gradeRqDto = new GradeRqDto(UUID.fromString(pupilId), UUID.fromString(lessonId), grade);
            gradesController.giveGrade(gradeRqDto);
        }
        if (value == 6) {
            System.out.println("Enter grade ID:");
            String gradesId = scanner.nextLine();
            gradesController.removeGrade(gradesController.findGradeById(UUID.fromString(gradesId)).getResult().orElse(null));
        }
        if (value == 7) {
            System.out.println("Enter student login:");
            String login = scanner.nextLine();
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            System.out.println(gradesController.calculateAverageGrade(userController.findUserByLogin(login).getResult().orElse(null), UUID.fromString(subjectId)));
        }
        if (value == 8) {
            System.out.println("Enter student login:");
            String login = scanner.nextLine();
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            System.out.println(gradesController.findAllGradesOfPupil(userController.findUserByLogin(login).getResult().orElse(null), UUID.fromString(subjectId)));
        }
        if (value == 9) {
            System.out.println("Enter teacher ID:");
            String teacherId = scanner.nextLine();
            System.out.println("Enter date (YYYY-MM-DDTHH:MM:SS):");
            String date = scanner.nextLine();
            System.out.println("Enter class ID:");
            String classId = scanner.nextLine();
            System.out.println("Enter subject ID:");
            String subjectId = scanner.nextLine();
            LessonRqDto lessonRqDto = new LessonRqDto(UUID.fromString(classId), UUID.fromString(teacherId), LocalDateTime.parse(date), UUID.fromString(subjectId));
            lessonController.createLesson(lessonRqDto);
        }
        if (value == 10) {
            System.out.println("Enter lesson ID:");
            String id = scanner.nextLine();
            lessonController.deleteLesson(lessonController.findLessonById(UUID.fromString(id)).getResult().orElse(null));
        }
        if (value == 11) {
            System.out.println("Enter date (YYYY-MM-DDTHH:MM:SS):");
            String date = scanner.nextLine();
            System.out.println(lessonController.findAllLessonsByDate(LocalDateTime.parse(date)));
        }
        if (value == 12) {
            System.out.println("Enter class ID:");
            String classId = scanner.nextLine();
            System.out.println(schoolClassController.findAllPupilsOfClass(schoolClassController.findSchoolClassById(UUID.fromString(classId)).getResult().orElse(null)));
        }
        if (value == 13) {
            System.out.println("Enter grade ID:");
            String id = scanner.nextLine();
            System.out.println(gradesController.updateGrade(UUID.fromString(id)));
        }
    }
}
