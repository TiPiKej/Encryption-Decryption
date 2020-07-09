import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int temp;

        while (sum < 1000) {
            temp = scanner.nextInt();
            if (temp == 0) break;

            sum += temp;
        }

        scanner.close();

        System.out.println(sum >= 1000 ? Math.abs(1000 - sum) : sum);
    }
}