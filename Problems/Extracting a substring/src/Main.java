import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            char[] s = scanner.next().trim().toCharArray();
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            for (int i = a; i <= b; i++) {
                System.out.print(s[i]);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}