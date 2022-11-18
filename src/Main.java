import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String digitals = "0123456789";
    static String romans = "IVXLCDM";
    static String signs = "+*-/";

    static int a, b;
    static String s;
    static int numd = 0, numr = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        String[] parts = input.split(" ");

        if (parts.length == 3) {

            if (isDigital(parts[0])) {
                a = Integer.parseInt(parts[0]);
                numd++;
            }

            if (isDigital(parts[2])) {
                b = Integer.parseInt(parts[2]);
                numd++;
            }

            if (isRoman(parts[0])) {
                a = decode(parts[0]);
                numr++;
            }

            if (isRoman(parts[2])) {
                b = decode(parts[2]);
                numr++;
            }

            if (numd + numr < 2) {
                throw new Exception("Не корректрные данные");
            }

            if (isSign(parts[1])) {
                s = parts[1];
            } else {
                throw new Exception("Некорректный знак!");
            }

            if (numd == 2 || numr == 2) {
                if (numr == 2 && proccessing(a, s, b) < 0) {
                    throw new Exception("Отрицательных римских чисел не бывает");
                }
                if (numd == 2) {
                    System.out.println(proccessing(a, s, b));
                } else {
                    System.out.println(roman(proccessing(a, s, b)));
                }
            } else {
                throw new Exception("Различные системы счисления");
            }

        } else {
            throw new Exception("Не соответсвие количество переменных или знаков");
        }



    }

    static boolean isDigital(String number) {
        boolean res = true;
        for (int i = 0; i < number.length(); i++) {
            if (digitals.indexOf(number.charAt(i)) == -1) {
                res = false;
            }
        }
        return res;
    }

    static boolean isRoman(String number) {
        boolean res = true;
        for (int i = 0; i < number.length(); i++) {
            if (romans.indexOf(number.charAt(i)) == -1) {
                res = false;
            }
        }
        return res;
    }

    static boolean isSign(String sign) {
        boolean res = false;
        if (sign.length() == 1 && signs.indexOf(sign) != -1) {
            res = true;
        }
        return res;
    }

    static int proccessing(int a, String s, int b) throws Exception {
        switch (s) {
            case "+":
                return a + b;
            case "*":
                return a * b;
            case "-":
                return a - b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    throw new Exception("Деление на 0!");
                }
        }
        return 0;
    }

    public static String roman(int n) {
        StringBuilder buf = new StringBuilder();

        final Numeral[] values = Numeral.values();
        for (int i = values.length - 1; i >= 0; i--) {
            while (n >= values[i].weight) {
                buf.append(values[i]);
                n -= values[i].weight;
            }
        }
        return buf.toString();
    }

    private static int decodeSingle(char letter) throws Exception {
        switch(letter) {
            case 'M': return 1000;
            case 'D': return 500;
            case 'C': return 100;
            case 'L': return 50;
            case 'X': return 10;
            case 'V': return 5;
            case 'I': return 1;
            default: throw new Exception("Не римская цифра");
        }
    }

    public static int decode(String roman) throws Exception {
        int result = 0;
        String uRoman = roman.toUpperCase();
        for(int i = 0;i < uRoman.length() - 1;i++) {
            if (decodeSingle(uRoman.charAt(i)) < decodeSingle(uRoman.charAt(i+1))) {
                result -= decodeSingle(uRoman.charAt(i));
            } else {
                result += decodeSingle(uRoman.charAt(i));
            }
        }

        result += decodeSingle(uRoman.charAt(uRoman.length()-1));
        return result;
    }

}
