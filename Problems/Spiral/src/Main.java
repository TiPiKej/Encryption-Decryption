import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.close();

        int[][] tab = new int[n][n];

        int nbr = 1;
        int xLayer = 0;
        int yLayer = 0;
        char direction = 'r';
        while (nbr <= Math.pow(n, 2)) {
            tab[xLayer][yLayer] = nbr;
//            System.out.println(direction);
            switch (direction) {
                case 'r':
                    if (yLayer == n - 1 || tab[xLayer][yLayer + 1] != 0) {
                        direction = 'd';
                        xLayer++;
                    }
                    else
                        yLayer++;
                    break;
                case 'd':
                    if (xLayer == n - 1 || tab[xLayer + 1][yLayer] != 0) {
                        direction = 'l';
                        yLayer--;
                    }
                    else
                        xLayer++;
                    break;
                case 'l':
                    if (yLayer == 0 || tab[xLayer][yLayer - 1] != 0) {
                        direction = 'u';
                        xLayer--;
                    }
                    else
                        yLayer--;
                    break;
                case 'u':
                    if (xLayer == 0 || tab[xLayer - 1][yLayer] != 0) {
                        direction = 'r';
                        yLayer++;
                    }
                    else
                        xLayer--;
                    break;
            }

            nbr++;
        }

        for (int[] layer : tab) {
            for (int item : layer) {
                System.out.printf("%d ", item);
            }
            System.out.println();
        }
    }
}