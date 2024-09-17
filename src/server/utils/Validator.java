package server.utils;

public class Validator {

    public static boolean isLoginValid(String email) {
        return email != null;
    }

    public static boolean isPasswordValid(String password) {
        return password != null;
    }
}
