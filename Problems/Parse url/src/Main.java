import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String pass = null;

            String url = scanner.next();
            String[] values = url.split("\\?")[1].split("&");

            for (String value : values) {
                String[] parts = value.split("=");

                System.out.printf("%s : ", parts[0]);

                if (parts.length == 1) {
                    System.out.println("not found");
                    continue;
                }

                System.out.println(parts[1]);

                if (parts[0].equals("pass")) {
                    pass = parts[1];
                }
            }

            if (pass != null) System.out.printf("password : %s", pass);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}