import java.util.Scanner;

class RomanCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число:");
        String input1 = scanner.nextLine().toUpperCase(); // Преобразуем введенное значение в верхний регистр

        System.out.println("Введите второе число:");
        String input2 = scanner.nextLine().toUpperCase(); // Преобразуем введенное значение в верхний регистр

        System.out.println("Выберите операцию (+, -, *, /):");
        char operator = scanner.nextLine().charAt(0);

        int num1, num2;
        boolean isRoman = false; // Флаг, указывающий на использование римских цифр

        // Проверяем, является ли введенное значение римским числом
        try {
            num1 = convertRomanToDecimal(input1);
            num2 = convertRomanToDecimal(input2);
            isRoman = true;
        } catch (IllegalArgumentException e) {
            // Если введенное значение не является римским числом, пытаемся преобразовать его в арабское число
            num1 = Integer.parseInt(input1);
            num2 = Integer.parseInt(input2);
        }

        int result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Ошибка: деление на ноль!");
                    return;
                }
                break;
            default:
                System.out.println("Ошибка: неверная операция!");
                return;
        }

        // Если использовались римские цифры, выводим результат в виде римского числа
        if (isRoman) {
            if (result <= 0) {
                System.out.println("Результат: Римские числа не могут быть отрицательными или равными нулю!");
            } else {
                System.out.println("Результат: " + convertDecimalToRoman(result));
            }
        } else {
            System.out.println("Результат: " + result);
        }
    }

    // Метод для преобразования римских чисел в десятичное представление
    private static int convertRomanToDecimal(String roman) {
        if (roman == null || roman.length() == 0) {
            throw new IllegalArgumentException("Пустая строка не может быть преобразована в число.");
        }

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentValue = getRomanValue(currentChar);

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        return result;
    }

    // Метод для получения числового значения римской цифры
    private static int getRomanValue(char roman) {
        switch (roman) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("Недопустимый символ римской цифры: " + roman);
        }
    }

    // Метод для преобразования десятичных чисел в римские
    private static String convertDecimalToRoman(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Число должно быть положительным и больше нуля.");
        }

        StringBuilder roman = new StringBuilder();

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int i = 0;
        while (num > 0) {
            if (num - values[i] >= 0) {
                roman.append(symbols[i]);
                num -= values[i];
            } else {
                i++;
            }
        }

        return roman.toString();
    }
}