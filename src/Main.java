import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректный формат ввода");
        }
        int num1, num2;
        String operator;
        try {
            if (isRoman(parts[0]) && isRoman(parts[2])) {
                num1 = romanToArabic(parts[0]);
                num2 = romanToArabic(parts[2]);
                operator = parts[1];
                if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                    throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
                }
                int result = calculate(num1, num2, operator);
                return arabicToRoman(result);
            } else if (isNumeric(parts[0]) && isNumeric(parts[2])) {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[2]);
                operator = parts[1];
                if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                    throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
                }
                int result = calculate(num1, num2, operator);
                return String.valueOf(result);
            } else {
                throw new IllegalArgumentException("Неверный формат чисел");
            }
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
    private static int calculate(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Неверно введён знак арифметической операции");
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isRoman(String str) {
        return str.matches("^[IVXLC]+$");
    }

    private static int romanToArabic(String input) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);

        int result = 0;
        int previousValue = 0;

        for (int i = input.length() - 1; i >= 0; i--) {
            int currentValue = map.get(input.charAt(i));

            if (currentValue < previousValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            previousValue = currentValue;
        }
        return result;
    }
    private static String arabicToRoman(int number) {

        String[] hundreds = {"", "C"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return hundreds[(number % 1000) / 100] + tens[(number % 100) / 10] + ones[number % 10];

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 2 + 3):");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
}
