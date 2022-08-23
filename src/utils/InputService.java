package utils;

import utils.exception.DateException;
import utils.exception.EmptyException;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputService {
    private static final Scanner sc = new Scanner(System.in);

    static String getStr(String mes) {
        String results = "";
        while (true) {
            try {
                System.out.print(mes);
                results = sc.nextLine();
                if (results.length() == 0) {
                    throw new EmptyException("Not empty!");
                }
                break;
            } catch (EmptyException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return results;
    }

    public static String getDate(String mes) {
        String result = "";
        String regexDate = "\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}";
        boolean isDate = false;
        while (true) {
            try {
                System.out.print(mes);
                result = sc.nextLine();
                isDate = Pattern.matches(regexDate, result);
                if (!isDate) {
                    throw new DateException("please enter format : dd/mm/yyyy");
                }
                break;
            } catch (DateException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    public static int getNumberInteger(String mes, int min, int max) {
        int result;
        while (true) {
            try {
                System.out.print(mes);
                result = Integer.parseInt(sc.nextLine());
                if (result >= min && result <= max) {
                    break;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.printf("Please enter number [%d...%d]\n", min, max);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return result;
    }

    public static double getNumberDouble(String mes, double min, double max) {
        double result;

        while (true) {
            try {
                System.out.println(mes);
                result = Double.parseDouble(sc.nextLine());
                if (result >= min && result <= max) {
                    break;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.printf("Please enter number [%d...%d]\n", min, max);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return result;
    }

}
