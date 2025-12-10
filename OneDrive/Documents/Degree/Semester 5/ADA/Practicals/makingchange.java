import java.util.*;

public class makingchange {

    static void display(List<Integer> arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static List<Integer> findchange(Integer[] coins, int amount) {
        Arrays.sort(coins, Collections.reverseOrder());

        int remainingAmount = amount;
        List<Integer> resultCoins = new ArrayList<>();

        for (int coin : coins) {
            while (remainingAmount >= coin) {
                resultCoins.add(coin);
                remainingAmount -= coin;
            }
        }

        if (remainingAmount == 0) {
            return resultCoins;
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" ");

        System.out.print("Enter the number of coin denominations: ");
        int n = sc.nextInt();
        Integer[] coins = new Integer[n];

        System.out.println("Enter the coin values:");
        for(int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        System.out.print("Enter the target amount to make change for: ");
        int amount = sc.nextInt();
        
        System.out.println("");

        List<Integer> result = findchange(coins, amount);

        if (result != null) {
            System.out.println("The coins needed to make the change are:");
            display(result);
            System.out.println("Total coins used: " + result.size());
        } else {
            System.out.println("Cannot make exact change for the given amount with these coins.");
        }
        
        sc.close();
    }
}