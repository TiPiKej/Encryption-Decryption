package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String mode = "enc";
        int key = 0;
        String data = "";
        String fileInLocation = null;
        String fileOutLocation = null;
        String alg = "shift";

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
                case "-in":
                    fileInLocation = args[i + 1];
                    break;
                case "-out":
                    fileOutLocation = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }

        if (fileInLocation != null) {
            File f = new File(fileInLocation);
            try (Scanner scanner = new Scanner(f)) {
                data = scanner.nextLine().trim();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                return;
            }
        }

        Encrypt encrypt = new Encrypt(data);

        encrypt.setAlgorithm(alg);

        if (mode.equals("enc")) {
            encrypt.hash(key);
        } else if (mode.equals("dec")) {
            encrypt.unHash(key);
        }

        if (fileOutLocation == null) {
            System.out.println(encrypt.toString());
        } else {
            try (PrintWriter outFile = new PrintWriter(fileOutLocation)){
                outFile.print(encrypt.toString());
            } catch (IOException e) {
                System.out.println("IO file exception!");
            }
        }
    }
}

class Encrypt {
    private char[] sentence;
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private char alg;

    public Encrypt(String sentence) {
        this.sentence = sentence.toCharArray();
    }

    public void hash(int key) {
        if (alg == 'u') this.shiftUnicode(key);
        else this.shiftAlphabet(key);
    }

    public void unHash(int key) {
        if (alg == 'u') this.unShiftUnicode(key);
        else this.unShiftAlphabet(key);
    }

    public void shiftUnicode(int key) {
        for (int i = 0; i < this.sentence.length; i++) {
            this.sentence[i] += key;
        }
    }

    public void unShiftUnicode(int key) {
        for (int i = 0; i < this.sentence.length; i++) {
            this.sentence[i] -= key;
        }
    }

    public void shiftAlphabet(int key) {
        this.shiftAlphabetAlgorithm(key, "shift");
    }

    public void unShiftAlphabet(int key) {
        this.shiftAlphabetAlgorithm(key, "unShift");
    }

    private void shiftAlphabetAlgorithm(int key, String mode) {
        int nbrOfFirstLetter;
        for (int i = 0; i < this.sentence.length; i++) {
            nbrOfFirstLetter = 0;

//            check if letter is lower case (a - 97) or upper case (A - 65)
            if (96 < (int)this.sentence[i] && (int)this.sentence[i] < 123) nbrOfFirstLetter = 97;
            if (65 <= (int)this.sentence[i] && (int)this.sentence[i] < 90) nbrOfFirstLetter = 65;

            if (nbrOfFirstLetter == 0) continue;

//            shift letter in alphabet (!!! in lower case)

            int shiftNbr = -nbrOfFirstLetter;
            if (mode.charAt(0) == 'u') shiftNbr -= key;
            else shiftNbr += key;

            while ((int)this.sentence[i] + shiftNbr < 0) {
                shiftNbr += alphabet.length();
            }

            this.sentence[i] = alphabet.charAt(((int)this.sentence[i] + shiftNbr) % alphabet.length());

//            if letter is upper case -> i move it into upper case
            if (nbrOfFirstLetter == 65) {
                this.sentence[i] = (char) ((int) this.sentence[i] - 32);
            }
        }
    }

    public void setAlgorithm(String alg) {
        this.alg = alg.charAt(0);
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
