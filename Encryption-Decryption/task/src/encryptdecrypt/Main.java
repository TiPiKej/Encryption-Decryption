package encryptdecrypt;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Encrypt encrypt = new Encrypt(scanner.nextLine());
        encrypt.shift(scanner.nextInt());
        System.out.println(encrypt.toString());
    }
}

class Encrypt {
    private char[] sentence;
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Encrypt(String sentence) {
        this.sentence = sentence.toCharArray();
    }

    public void reverse() {
        for (int i = 0; i < this.sentence.length; i++) {
            if ((int)this.sentence[i] > 96 && (int)this.sentence[i] < 123)
                this.sentence[i] = letterToReverse(this.sentence[i]);
        }
    }

    private char letterToReverse(char ch) {
        StringBuilder reversedAlphabet = new StringBuilder();
        for (int i = alphabet.length() - 1; i > 0; i--) {
            reversedAlphabet.append(alphabet.charAt(i));
        }
        return reversedAlphabet.charAt((int)ch - 97);
    }

    public void shift(int key) {
        for (int i = 0; i < this.sentence.length; i++) {
            if ((int)this.sentence[i] > 96 && (int)this.sentence[i] < 123) {
                this.sentence[i] = alphabet.charAt(((int)this.sentence[i] - 97 + key) % alphabet.length());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (char ch : sentence) {
            s.append(ch);
        }

        return s.toString();
    }
}
