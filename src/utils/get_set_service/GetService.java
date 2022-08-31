package utils.get_set_service;

import utils.exception.DateException;
import utils.exception.EmptyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GetService {
    private static final Scanner sc = new Scanner(System.in);

    public static String getStr(String mes) {
        String results;
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
            }
        }
        return results;
    }

    public static String getDate(String mes) {
        String result;
        String regexDate = "\\d{1,2}[-|/]\\d{2}[-|/]\\d{4}";
        boolean isDate;
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
            }
        }
        return result;
    }

    /**
     *Lấy ra ngày lớn hơn ngày tháng năm lớn hơn hoặc bằng 18 tuổi
     *
     * @param mes lời mô tả cho yêu cầu
     * @param age giới hạn tuổi
     * @return String
     */
    public static String getDate(String mes, int age) {
        String result;
        String regexDate = "\\d{1,2}[-|/]\\d{2}[-|/]\\d{4}";
        int year = LocalDate.now().getYear();
        while (true) {
            try {
                System.out.print(mes);
                result = sc.nextLine();
                if (!Pattern.matches(regexDate, result)) {
                    throw new DateException("please enter format : dd/mm/yyyy");
                }
                if (year - getYear(result) < age) {
                    throw new DateException("Please age > " + age);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    private static int getYear(String result) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate date = LocalDate.parse(result, formatter);
        return date.getYear();
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
                System.out.printf("Please enter number [%f...%f]\n", min, max);
            }

        }

        return result;
    }

    /**
     * Tạo ra một chuỗi được nhập vào từ bàn phím, chữ cái đầu mỗi từ là in hoa
     * bắt đầu là chữ
     *
     * @param mes mô tả cho chuỗi sẽ nhập, ex: nhập vào tên của bạn
     * @return String
     */
    public static String getName(String mes) {
        final String REGEX_NAME = "^([A-Z]\\w*\\s*)+$";
        String name;
        while (true) {
            System.out.print(mes);
            name = sc.nextLine();
            if (name.matches(REGEX_NAME)) {
                return name.trim();
            } else if (name.length() == 0) {
                System.out.println("Not empty!:");
            } else {
                System.out.println("upper case first character \nExample: Nguyen Van A, Nguyen Van B, ...");
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
        String phoneNumber;
        while (true) {
            System.out.print(mes);
            phoneNumber = sc.nextLine();
            if (phoneNumber.matches(REGEX_PHONE_NUMBER)) {
                return phoneNumber.trim();
            } else {
                System.out.println("invalid, Enter phoneNumber again [0-9] length(10-11) :");
            }
        }
    }

    public static String getEmail(String mes) {
        final String REGEX_EMAIL = "^[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
        String email;
        while (true) {
            System.out.print(mes);
            email = sc.nextLine();
            if (email.matches(REGEX_EMAIL)) {
                return email.trim();
            } else {
                System.out.println("invalid, Enter email again :");
            }
        }
    }

    /**
     * Tạo mới một LocalDate được nhập vào từ bàn phím
     * ngày nhập vào phải lớn hơn hoặc bằng ngày giới hạn
     * Ex:
     *      Ngày qui định: 1/11/2011
     *      ngày nhập vào phải lớn hơn ngày giới hạn
     *
     * @param mes Câu mô tả cho ngày cần nhập
     * @param day ngày giới hạn
     * @return LocalDate
     */
    public static LocalDate getDate(String mes, LocalDate day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String result;
        String regexDate = "\\d{1,2}/\\d{2}/\\d{4}";
        while (true) {
            try {
                System.out.print(mes);
                result = sc.nextLine();
                if (!Pattern.matches(regexDate, result)) {
                    throw new DateException("please enter format : dd/mm/yyyy");
                }
                if (day.compareTo(LocalDate.parse(result, formatter)) > 0) {
                    throw new DateException("Starting from " + day);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return LocalDate.parse(result, formatter);
    }
}
