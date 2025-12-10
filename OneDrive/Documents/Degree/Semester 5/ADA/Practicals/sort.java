import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class sort {

    static void display(int arr[]) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println(); 
    }

    static int[] ran(int size, int bound) {
        Random rand = new Random();
        //If you have to get the number in between  to bound so change here
        bound=1000;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(bound);
        }
        return arr;
    }

    public static long selection(int[] arr) {
        Instant start = Instant.now();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        Instant end = Instant.now();
        Duration totaldiff=Duration.between(start,end);
        return totaldiff.toNanos();
      
    }

    public static long bubble(int arr[], int n) {
         Instant start = Instant.now();
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
        Instant end = Instant.now();
        Duration totaldiff=Duration.between(start,end);
        return totaldiff.toNanos();
    }

    public static long insertion(int[] arr) {
        Instant start = Instant.now();
        int n= arr.length;
        for (int j = 0; j< n ; j++){
            int key = arr[j];
            int i=j-1;
            while (i>=0 && arr[i]>key){
                arr[i+1]=arr[i];
                i=i-1;     
            }
            arr[i+1]=key;

        }
        Instant end = Instant.now();
        Duration totaldiff=Duration.between(start,end);
        return totaldiff.toNanos();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" ");
        System.out.print("Enter the number of elements which you want to sort:");
        int n = sc.nextInt();

    
        int bound = 100;
        int[] arr = ran(n, bound);

        System.out.println("Original Array:");
        display(arr);
        System.out.println("");
        

        while(true){
            System.out.println("Enter the Choise: ");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Insertion Sort");
            System.out.println("3. Selectin sort");
            System.out.println("4. To end program");
            try (Scanner scanner = new Scanner(System.in)) {
                int choice = scanner.nextInt();
                switch (choice){ 
                    case 1:
                        int[] bubbleArr = arr.clone();
                        System.out.println("Bubble sort");
                        long bubbleTime=bubble(bubbleArr,n);
                        display(bubbleArr);
                         System.out.printf("Bubble Sort Time: %.4f milliseconds%n", bubbleTime / 1_000_000.0);
                        break;
                    case 2:
                        int[] insertionArr=arr.clone();
                         System.out.println("Insertion sort");
                        long insertionTime=insertion(insertionArr);
                        display(insertionArr);
                        System.out.printf("Insertion  Sort Time: %.4f milliseconds%n", insertionTime / 1_000_000.0);
                        break;
                    case 3:
                        int[] selectionArr=arr.clone(); 
                        System.out.println("Selection sort");
                        long selectionTime=selection(selectionArr);
                        display(selectionArr);
                        System.out.printf("Selection Sort Time: %.4f milliseconds%n", selectionTime / 1_000_000.0);
                        break;
                    case 4:
                        return;
                
                
                }
            }
            
            sc.close();
        }
        
    }

}

