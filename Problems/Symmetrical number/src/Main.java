import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder nbr = new StringBuilder(scanner.next().trim());

        // completting by zeros in the beggining
        while (nbr.length() < 4) {
            nbr.insert(0, "0");
        }

        //check if is symmetric
        boolean isSymmetric = true;

        for (int i = 0; i < nbr.length(); i++) {
            if (nbr.charAt(i) != nbr.charAt(nbr.length() - 1 - i)) {
                isSymmetric = false;
            }
        }

        System.out.println(isSymmetric ? 1 : 37);

        scanner.close();
    }
}