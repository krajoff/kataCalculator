import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Character, Integer> roman = new HashMap<Character, Integer>()
    {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    private static int romanToInt(String s)
    {
        int sum = 0;
        int n = s.length();

        for(int i = 0; i < n; i++)
        {
            if (i != n - 1 &&
                    roman.get(s.charAt(i)) <
                            roman.get(s.charAt(i + 1)))
            {
                sum += roman.get(s.charAt(i + 1)) -
                        roman.get(s.charAt(i));
                i++;
            }
            else
            {
                sum += roman.get(s.charAt(i));
            }
        }
        return sum;
    }

    public static String intToRoman(int number) {
        String[] thousands = new String[]{"", "M", "MM", "MMM"};
        String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[number / 1000] + hundreds[number % 1000 / 100] + tens[number % 100 / 10] + units[number % 10];
    }

    public static String calc(String input) throws NumberFormatException {
        String textConsole = input;
        textConsole = textConsole.replaceAll("\\s+", "");
        String[] splitArabic = textConsole.split("\\D+");
        String[] splitRoman = textConsole.split("[^a-zA-Z]+");
        String splitOperand = textConsole.replaceAll("[^+*-/]", "");
        String textControl = textConsole.replaceAll("[[\\-,*,+,/]*, \\w*]", "");
        boolean system = true;
        textConsole = textConsole.replaceAll("[^+*-/]", "");



        if (textControl.length() != 0) {
            throw new NumberFormatException("throws Exception //т.к. используются недопустимые символы");
        } else if (splitArabic.length > 0 && splitRoman.length > 0) {
            throw new NumberFormatException("throws Exception //т.к. используются одновременно разные системы счисления");
        } else if (splitArabic.length + splitRoman.length < 2) {
            throw new NumberFormatException("throws Exception //т.к. строка не является математической операцией");
        } else if (splitOperand.length() > 1 | textConsole.length() > 1) {
            throw new NumberFormatException("throws Exception //т.к. формат математической операции неудовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else {
            if (splitArabic.length > 0) {
                for (String value : splitArabic) {
                    if (Integer.parseInt(value) != (int) Integer.parseInt(value)) {
                        throw new NumberFormatException("throws Exception //т.к. числа не целые");
                    }
                    if (Integer.parseInt(value) > 10) {
                        throw new NumberFormatException("throws Exception //т.к. числа выходят за диапазон от 1 до 10");
                    }
                }
            }


            if (splitRoman.length > 0) {
                system = false;
                splitArabic = splitRoman;
                for(int i = 0; i < splitRoman.length; ++i){
                    int ArabicInteger = romanToInt(splitRoman[i]);
                    splitArabic[i] = String.valueOf(ArabicInteger);
                    if (ArabicInteger > 10) {
                        throw new NumberFormatException("throws Exception //т.к. числа выходят за диапазон от I до X");
                    }
                }
            }

            int result;
            if (textConsole.contains("+")) {
                result = Integer.parseInt(splitArabic[0]) + Integer.parseInt(splitArabic[1]);
            } else if (textConsole.contains("-")) {
                result = Integer.parseInt(splitArabic[0]) - Integer.parseInt(splitArabic[1]);
            } else if (textConsole.contains("*")) {
                result = Integer.parseInt(splitArabic[0]) * Integer.parseInt(splitArabic[1]);
            } else {
                result = Integer.parseInt(splitArabic[0]) / Integer.parseInt(splitArabic[1]);
            }

            if (!system) {
                System.out.println(intToRoman(result));
                return intToRoman(result);
            } else {
                System.out.println(result);
                return Integer.toString(result);
            }

        }
    }
// main-body
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        String textConsole = scanner.next();
        Main.calc(textConsole);
    }
}

