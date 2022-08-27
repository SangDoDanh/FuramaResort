package utils.get_set_service;

import utils.exception.DateException;
import utils.exception.EmptyException;

import java.util.Scanner;
import java.util.regex.Pattern;

public class GetService {
    private static final Scanner sc = new Scanner(System.in);

    public static String getStr(String mes) {
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
                System.out.print(mes);
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

    public static String getName(String mes) {
        final String REGEX_NAME = "[a-zA-Z\\s]{1,25}";
        String name ;
        while (true) {
            System.out.print(mes);
            name = sc.nextLine();
            if(name.matches(REGEX_NAME)){
                return name.trim();
            } else {
                System.out.println("invalid, Enter name again [a-z, A-Z] not number and length > 1:");
            }
        }
    }

    public static String getGender() {
        String[] genders = {"Male", "FMale", "Other"};
        int choose;
        System.out.println("1. Male \t2. FMale \t3. Other");
        choose = getNumberInteger("Your choose: ", 1, 3);
        return genders[choose - 1];
    }

    public static String getPhoneNumber(String mes) {
        final String REGEX_PHONE_NUMBER = "^0\\d{9,10}$";
        String phoneNumber ;
        while (true) {
            System.out.print(mes);
            phoneNumber = sc.nextLine();
            if(phoneNumber.matches(REGEX_PHONE_NUMBER)){
                return phoneNumber.trim();
            } else {
                System.out.println("invalid, Enter phoneNumber again [0-9] length(10-11) :");
            }
        }
    }

    public static String getEmail(String mes) {
        final String REGEX_EMAIL = "^[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
        String email ;
        while (true) {
            System.out.print(mes);
            email = sc.nextLine();
            if(email.matches(REGEX_EMAIL)){
                return email.trim();
            } else {
                System.out.println("invalid, Enter email again :");
            }
        }
    }

    public static String getPosition() {
        String[] positions = {"Worker", "Manager", "President", "Other"};
        int choose;
        System.out.println("1. Worker \t2. Manager \t3. President \t4. Other");
        choose = getNumberInteger("Your choose: ", 1, 4);
        return positions[choose - 1];
    }

    public static String getLevel() {
        String[] positions = {"University", "High school", "Nothing"};
        int choose;
        System.out.println("1. University \t2. High school \t3. Nothing");
        choose = getNumberInteger("Your choose: ", 1, 3);
        return positions[choose - 1];
    }

    public static String getRank() {
        String[] positions = {"Diamond", "Platinum", "Gold", "Silver", "Member"};
        int choose;
        System.out.println("1. Diamond \t2. Platinum \t3. Gold \t4. Silver \t5. Member");
        choose = getNumberInteger("Your choose: ", 1, 5);
        return positions[choose - 1];
    }

    public static String getRentalStyle() {
        String[] rentalStyles = {"Year", "Month", "Day", "hours"};
        int choose;
        System.out.println("1. Year \t2. Month \t3. Day \t4. hours");
        choose = getNumberInteger("Your choose: ", 1, 4);
        return rentalStyles[choose - 1];
    }

    public static String getRomStand() {
        String[] romStand = {"President", "vip", "Normal"};
        int choose;
        System.out.println("1. President \t2. vip \t3. Normal");
        choose = getNumberInteger("Your choose: ", 1, 3);
        return romStand[choose - 1];
    }
}
