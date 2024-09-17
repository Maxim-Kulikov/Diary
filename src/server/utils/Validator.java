package server.utils;

public class Validator {

    public static boolean isLoginValid(String email) {
        return email != null && !email.isEmpty();
    }

    public static boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty() && password.length() <= 20;
    }
}
