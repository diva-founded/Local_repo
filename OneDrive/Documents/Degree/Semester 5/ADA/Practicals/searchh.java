import java.util.*;

public class searchh {

    static void display(int arr[]) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    static int[] random(int size, int bound) {
        int[] arr = new int[size];
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = ran.nextInt(bound);
        }
        return arr;
    }

    public static long bubble(int arr[], int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        long end = System.nanoTime();
        return end - start;
    }

    static class SearchResult {
        int index;
        long timeTaken;

        SearchResult(int index, long timeTaken) {
            this.index = index;
            this.timeTaken = timeTaken;
        }
    }

    public static SearchResult binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        long start = System.nanoTime();
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key) {
                long end = System.nanoTime(); // It is used because if the ky matched then how much time it contains 
                return new SearchResult(mid, end - start);
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        long end = System.nanoTime();
        return new SearchResult(-1, end - start);
    }
    
    
    
    public static SearchResult linearSearch(int[] arr, int key) {
        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                long end = System.nanoTime();
                return new SearchResult(i, end - start);
            }
        }
        long end = System.nanoTime();
        return new SearchResult(-1, end - start);
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int size = sc.nextInt();
        int bound = 1000;
        int[] arr = random(size, bound);
        System.out.println("Original Array:");
        display(arr);
        int[] bubbleArr = arr.clone();
        System.out.println("\nBubble Sort:");
        long bubbleTime = bubble(bubbleArr, size);
        display(bubbleArr);
        System.out.printf("Bubble Sort Time: %.4f milliseconds%n", bubbleTime / 1_000_000.0);


        System.out.print("\nEnter the element to search: ");
        int key = sc.nextInt();

        System.out.println("");
        SearchResult linear = linearSearch(bubbleArr, key);
        if (linear.index != -1) {
            System.out.println("Linear Search: Element found at index " + linear.index);
        } else {
            System.out.println("Linear Search: Element not found.");
        }
        System.out.printf("Linear Search Time: %d ns (%.10f ms)%n", linear.timeTaken, linear.timeTaken / 1_000_000.0);


        System.out.println(" ");
        SearchResult result = binarySearch(bubbleArr, key);
        if (result.index != -1) {
            System.out.println("Element found at index: " + result.index);
        } else {
            System.out.println("Element not found in the array.");
        }
         System.out.printf("Binary Search Time: %d ns (%.10f ms)%n", result.timeTaken, result.timeTaken / 1_000_000.0);





        sc.close();
    }
}
