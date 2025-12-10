import java.util.*;

public class mqsort{
    static void display(int arr[]){
        for(int val:arr){
            System.out.print(val+" ");
        }
        System.out.println();
    }
   static int[] random(int size, int bound) {
        int[] arr = new int[ size ];
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = ran.nextInt(bound);
        }
        return arr;
    }
    static void combine(int arr[],int left,int mid,int right){   
        int l1 = mid-left+1;
        int l2 = right-mid;

        int L[] = new int[ l1 ];
        int R[] = new int[ l2 ];

        for (int i = 0;i < l1; i++){
            L[ i ] = arr[ left+i ];
        }
        for (int j=0;j<l2;j++){
            R[ j ]=arr[ mid+1+j ];
        }

        int i = 0,j = 0,k = left;
        while(i < l1 && j < l2){
            if (L [ i ] <= R [ j ]){ //here i do the mistek that was i was comapring L[i] and R[i] but it actuall was L[i] and R[j]
                arr [ k ] = L [ i ];
                i++;
            }
            else{
                arr [ k ] = R [ j ];
                j++;
            }
            k++;
        }
        while (i < l1){
            arr[ k ] = L [ i ];
            i++;
            k++;
        }
        while(j<l2){
            arr[ k ]=R[ j ];
            j++;
            k++;
        }  
    }
    static void murge_sort(int arr[],int left,int right){
        if( left<right ){
            int mid = ( left + right )/2;
            murge_sort(arr , left , mid);
            murge_sort(arr , mid+1, right);

            combine(arr, left, mid, right);
           
        }
    }
    static int partition( int[] arr, int low, int high){
        int pivot = arr[ high ];
        int i = low;
        int j = high - 1 ;

        while (true){
            while(i <= j && arr[i] < pivot){
                i++;
            }
            while(i <= j && arr[j] > pivot){
                j--;
            }
            if (i >= j){
                break;
            }
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;

            i++;
            j--;
        }
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i;
    }

    static void quick_sort(int arr[],int low,int high){
        if (low<high){
            int pivot = partition(arr,low,high);
            quick_sort(arr,low,pivot-1);
            quick_sort(arr,pivot+1,high);
        }
    }
    public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.println(" ");
    System.out.println(" ");
    System.out.print("Enter the number of element which you want :- ");
    
    int n = sc.nextInt();
    int bound = 1000;
    int arr[] = random(n,bound);

    System.out.println(" ");
    System.out.println("Original array:");
    display(arr);
    System.out.println("");

    double start = System.nanoTime();
    murge_sort(arr,0,n-1);
    double end = System.nanoTime();
    
    System.out.println("Sorted array by Merge sort is :");
    display(arr);

    System.out.println("");

    double sta = System.nanoTime();
    quick_sort(arr, 0,n-1);
    double ed = System.nanoTime();

    System.out.println("Sorted arry by using QUICK SORT is ");
    display(arr);

    System.out.println("");
    System.out.println("Total time Taken using  MERGE SORT is : "+(end-start)/1_000_000.0+" ms");
    System.out.println("Total Time taken using Quick sort :"+(ed-sta)/1_000_000.0+" ms");
    
    sc.close();
    }
}


