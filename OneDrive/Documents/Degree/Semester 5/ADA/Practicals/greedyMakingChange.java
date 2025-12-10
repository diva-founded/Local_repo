import java.util.*;
public class greedyMakingChange {

    public static List<Integer> findMinimumCoins(Integer[] coins, int amount) {

         Arrays.sort(coins, Collections.reverseOrder());

        List<Integer> resultCoins = new ArrayList<>();// create the blank contanier for hold the coin that make final answer
        int remainingAmount = amount;

        // Iterate through each coin denomination from largest to smallest
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            // While the current coin can be used, use it
            while (remainingAmount >= coin) {
                // Subtract the coin value from the remaining amount
                remainingAmount -= coin;
                // Add the coin to our result list
                resultCoins.add(coin);
            }
        }

        // After checking all coins, if the remaining amount is 0, we have a solution.
        if (remainingAmount == 0) {
            return resultCoins;
        } else {
            // If the remaining amount is not 0, it means we couldn't make the exact change.
            System.out.println("Cannot make exact change for the given amount with the provided coins.");
            return null;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

       
        int numCoins = rand.nextInt(6) + 5; // you can change this as you want the random coins
        HashSet<Integer> coinSet = new HashSet<>();
        coinSet.add(1); // Always include a '1' coin to guarantee a solution is possible

        while (coinSet.size() < numCoins) {
            coinSet.add(rand.nextInt(100) + 1); // Add random coins up to a value of 100
        }

        Integer[] coins = coinSet.toArray(new Integer[0]);
        Arrays.sort(coins); // Sort for clean display

        System.out.print("\nEnter the target amount for which you need change: ");
        int amount = scanner.nextInt();

        System.out.println("--- Coin Vending Machine ---");
        System.out.println("Available coin denominations are: " + Arrays.toString(coins));

    

        System.out.println("\n--- Calculating Change ---");
        List<Integer> result = findMinimumCoins(coins, amount);

        if (result != null) {
            System.out.println("Coins required to make " + amount + " are: " + result);
            System.out.println("Total number of coins: " + result.size());
            System.out.println("\nNote: The greedy approach is optimal only for canonical coin systems.");
        }

        scanner.close(); // Close the scanner to prevent resource leaks
    }
}