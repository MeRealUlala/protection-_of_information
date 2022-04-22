package Лаба2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ZI2_real {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String alpha = "йцукенгшщзхъфывапролджэячсмитьбюёЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁ";
    public static void main(String[] args) {
        System.out.println("Введите текст");
        String text = scanner.next();
        System.out.println("Введите ключ");
        int key = scanner.nextInt();
        System.out.println("1. Шифрование\n2. Дешифрование");
        int command = scanner.nextInt();
        if (command == 1) {
            System.out.println(codeText(text, key));
        } else {
            System.out.println(decodeTest(text, key));
        }
        scanner.close();
    }
    public static String codeText(String text, int lenRow) {
        Map<Integer, Character> notLetter = new HashMap<>();//мапа без букв
        char[] textAsChars = text.toCharArray();
        int countRow = text.length() % lenRow > 0 ? text.length() / lenRow + 1 : text.length() / lenRow;
        char[][] codeTextAsChars = new char[lenRow][countRow];
        int i = 0;
        int j = 0;
        for (int x = 0; x < textAsChars.length; x++) {
            if (!isLetter(textAsChars[x])) {
                notLetter.put(x, textAsChars[x]);
                continue;
            }
            codeTextAsChars[i][j] = textAsChars[x];
            i++;
            if (i > lenRow - 1) {
                i = 0;
                j++;
            }
        }
        char[] textAsChars1 = new char[text.length()];
        i = 0;
        for (Map.Entry<Integer, Character> map: notLetter.entrySet()) {
            textAsChars1[map.getKey()] = map.getValue();
        }
        for (int x = 0; x < codeTextAsChars.length; x++) {//перебираем матрицу по строкам
            System.out.println(codeTextAsChars[x]);
            for (int y = 0; y < codeTextAsChars[x].length; y++) {
                if (codeTextAsChars[x][y] != 0) {
                    i = getTrueIndex(textAsChars1, i);
                    textAsChars1[i] = codeTextAsChars[x][y];
                }
            }
        }
        return String.valueOf(textAsChars1);
    }
    public static boolean isLetter(char ch) {
        return alpha.contains(String.valueOf(ch));
    }
    public static int getTrueIndex(char[] text, int i) {
        int trueIndex = i;
        while (text[trueIndex] != 0) {
            trueIndex++;
        }
        return trueIndex;
    }
    public static String decodeTest(String text, int lenRow) {
        Map<Integer, Character> notLetter = new HashMap<>();
        char[] textAsChars = addAndDeleteNoLetter(text.toCharArray(), notLetter);//массив только букв
        int countRow = textAsChars.length % lenRow > 0 ? textAsChars.length / lenRow + 1 : textAsChars.length / lenRow;
        int countChars = textAsChars.length;//количество символов в строчке
        int countBigStrings = countChars % lenRow;//количество больших строк
        char[][] matrixText = new char[lenRow][countRow];//создаем матрицу
        countRow = countBigStrings > 0 ? countRow - 1 : countRow;//количество строк
        int sizeString = countBigStrings > 0 ? countRow + 1 : countRow;//размер строки
        int x = 0;
        int y = 0;
        for (int i = 0; i < textAsChars.length; i++) {//заполнение матрицы только буквами
            matrixText[x][y] = textAsChars[i];
            y++;
            if (y > sizeString - 1) {//проверка на то что заполнение стольца закончено и переход к следующему
                x += 1;
                y = 0;
            }
            if (x > countBigStrings - 1) {//окончание всех длинных строк и переход к другому размеру строки
                sizeString = countRow;
            }
        }
        char[] decodeText = new char[text.length()];
        for (Map.Entry<Integer, Character> map: notLetter.entrySet()) {
            decodeText[map.getKey()] = map.getValue();
        }
        int index = 0;
        for (int i = 0; i < matrixText[0].length; i++) {
            for (int j = 0; j < matrixText.length; j++) {
                if (matrixText[j][i] != 0) {
                    index = getTrueIndex(decodeText, index);
                    decodeText[index] = matrixText[j][i];
                }
            }
        }
        return String.valueOf(decodeText);
    }
    public static char[] addAndDeleteNoLetter(char[] text, Map<Integer, Character> noLetter) {
        for (int i = 0; i < text.length; i++) {
            if (!isLetter(text[i])) {
                noLetter.put(i, text[i]);
            }
        }
        char[] newText = new char[text.length - noLetter.size()];
        int j = 0;
        for (char c : text) {
            if (isLetter(c)) {
                newText[j] = c;
                j++;
            }
        }
        return newText;
    }
}
