import java.util.Arrays;
import java.util.Scanner;

public class makingchange {

    // Function to perform greedy coin change
    static void greedyChange(int[] coins, int amount) {
        // Sort coins in descending order
        Arrays.sort(coins);
        int n = coins.length;
        int[] sortedCoins = new int[n];
        for (int i = 0; i < n; i++) {
            sortedCoins[i] = coins[n - i - 1];
        }

        int remainingAmount = amount;
        int totalCoins = 0;

        System.out.println("\nCoins used:");
        for (int coin : sortedCoins) {
            if (remainingAmount >= coin) {
                int count = remainingAmount / coin;
                remainingAmount -= count * coin;
                totalCoins += count;
                System.out.println(coin + " x " + count);
            }
        }

        if (remainingAmount > 0) {
            System.out.println("Change cannot be given exactly with the provided denominations.");
        } else {
            System.out.println("Total coins used: " + totalCoins);
        }
    }

    // Main function — only takes input and calls greedyChange()
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of coin denominations: ");
        int n = sc.nextInt();
        int[] coins = new int[n];

        System.out.println("Enter coin denominations:");
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        System.out.print("Enter the amount to make change for: ");
        int amount = sc.nextInt();

        // Call the function
        greedyChange(coins, amount);

        sc.close();
    }
}
