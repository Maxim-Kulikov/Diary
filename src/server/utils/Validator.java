package server.utils;

import server.utils.exception.badrequest.ConstraintViolationException;
import java.util.Scanner;

public class Validator {

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    public static boolean ifInputIsSmallerThanMin(int input, int min) throws ConstraintViolationException {
        if (input < min)
            throw new ConstraintViolationException("Input must be at least " + min);

        return false;
    }

    public static boolean ifInputIsGreaterThanMax(int input, int max) throws ConstraintViolationException {
        if (input > max)
            throw new ConstraintViolationException("Input must be at most " + max);

        return false;
    }

    public static boolean ifLoginIsEmpty(Object input) throws ConstraintViolationException {
        if (input == null)
            throw new ConstraintViolationException("Input cannot be null");

        return false;
    }

    public static boolean IsContainingElement(int min, int max, String input, String symbol) throws ConstraintViolationException {
        System.out.println("Введите символ для его получения: ");
        symbol = new Scanner(System.in).nextLine();
        for (int i = min; i < max; i++) {
            char iPosition = input.charAt(i);
            if (symbol.equals(String.valueOf(iPosition))) {
                System.out.println(iPosition);
            }
        }
        throw new ConstraintViolationException("The input does not contain the element");
    }

    public static boolean IsInputValid(String input, int min, int max) throws ConstraintViolationException {
        if (input.length() >= max - min)
            throw new ConstraintViolationException("The input is too long");
        return false;
    }


    public static boolean IsSizeValid(Object[] arr, int min, int max) throws ConstraintViolationException {
        if (arr.length >= max - min)
            throw new ConstraintViolationException("The input (array) is too long");
        return false;
    }

}
