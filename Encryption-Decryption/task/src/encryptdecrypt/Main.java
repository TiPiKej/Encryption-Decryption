package encryptdecrypt;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Encrypt encrypt = new Encrypt("we found a treasure!");
        encrypt.reverse();
        System.out.println(encrypt.toString());
    }
}

class Encrypt {
    private char[] sentence;

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
        Character[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Arrays.sort(alphabet, Collections.reverseOrder());
        return alphabet[(int)ch - 97];
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
