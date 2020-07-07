import java.util.Scanner;

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}

class Time {

    int hour;
    int minute;
    int second;

    public static Time noon() {
        // write your code here

        Time time = new Time();
        time.hour = 12;
        time.minute = 0;
        time.second = 0;

        return time;
    }

    public static Time midnight() {
        // write your code here

        Time time = new Time();
        time.hour = 0;
        time.minute = 0;
        time.second = 0;

        return time;
    }

    public static Time ofSeconds(long seconds) {
        // write your code here

        Time time = new Time();
        time.second = (int) (seconds % 60);
        time.minute = (int) (seconds % (60 * 60)) / 60;
        time.hour = (int) (seconds / 3600) % 24;

        return time;
    }

    public static Time of(int hour, int minute, int second) {
        // write your code here

        Time time = new Time();

        if (hour < 0 || 23 < hour) return null;
        time.hour = hour;

        if (minute < 0 || 59 < minute) return null;
        time.minute = minute;

        if (second < 0 || 59 < second) return null;
        time.second = second;

        return time;
    }
}