package encryptdecrypt;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        String mode = "enc";
        int key = 0;
        String data = "";

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
            }
        }

        Encrypt encrypt = new Encrypt(data);

        if (mode.equals("enc")) {
            encrypt.shift(key);
        } else if (mode.equals("dec")) {
            encrypt.unshift(key);
        }

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

    public void shift(int key) {
        for (int i = 0; i < this.sentence.length; i++) {
            this.sentence[i] += key;
        }
    }

    public void unshift(int key) {
        for (int i = 0; i < this.sentence.length; i++) {
            this.sentence[i] -= key;
        }
    }

    private char letterToReverse(char ch) {
        StringBuilder reversedAlphabet = new StringBuilder();
        for (int i = alphabet.length() - 1; i > 0; i--) {
            reversedAlphabet.append(alphabet.charAt(i));
        }
        return reversedAlphabet.charAt((int)ch - 97);
    }

    public void shiftOnlyInAplhabet(int key) {
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
