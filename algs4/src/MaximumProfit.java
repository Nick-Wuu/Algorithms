import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaximumProfit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int days = scanner.nextInt();
        int[][] products = new int[number][days];
        int[] maxItems = new int[number];
        for (int i = 0; i < number; i++) {
            maxItems[i] = scanner.nextInt();
        }

        for (int i = 0; i < number; i++) {
            for (int j = 0; j < days; j++) {
                int price = scanner.nextInt();
                products[i][j] = price;
            }
        }
        int maxProfit = 0;
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < days; j++) {
                int profit = Math.max(0, products[i][j] - products[i][j - 1]);
                maxProfit += profit * maxItems[i];
            }

        }

        System.out.println(maxProfit);

    }
}
