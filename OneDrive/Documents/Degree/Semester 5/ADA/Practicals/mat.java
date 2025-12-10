import java.util.*;

public class mat {
    public static void main(String arr[]) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of row in matrix1");
            int row1 = sc.nextInt();
            System.out.println("Enter number of column in matrix1");
            int col1 = sc.nextInt();
            int A[][] = generateRandomMatrix(row1, col1);
            
            System.out.println("Enter number of row in matrix2");
            int row2 = sc.nextInt();
            System.out.println("Enter number of column in matrix1");
            int col2 = sc.nextInt();
            int B[][] = generateRandomMatrix(row2, col2);
            
            System.out.println("Matrix 1:");
            display(A);
            System.out.println(" ");
            System.out.println("Matrix 2:");
            display(B);
            
            if (col1 != row2 || col2!= row1 ) {
                System.out.println("Matrix multiplication is not possible.");
            	System.out.println("Stressen's matrics only support the Power of 2's matrics ");
                return;
            } else {
                int[][] result = mul(A, B, row1); 
            	System.out.println(" ");
                System.out.println("Resultant Matrix:");
                display(result);
            }
        }
    }

    public static int[][] generateRandomMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = rand.nextInt(100); // numbers from 0 to 9
        return matrix;
    }

    public static void display(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
	public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    public static int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    public static int[][] mul(int A[][], int B[][], int n) {
        int[][] C = new int[n][n];
		
		if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        } 
		
		int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + newSize];
                A21[i][j] = A[i + newSize][j];
                A22[i][j] = A[i + newSize][j + newSize];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + newSize];
                B21[i][j] = B[i + newSize][j];
                B22[i][j] = B[i + newSize][j + newSize];
            }
        }
		
		int s1[][] = mul(add(A11, B22),add(B11 , B22), newSize);
		int s2[][] = mul(add(A21 , A22),B11, newSize);
		int s3[][] = mul(A11,sub(B12 , B22), newSize);
		int s4[][] = mul(A22,sub(B21 , B11), newSize);
		int s5[][] = mul(add(A11 , B12), B22, newSize);
		int s6[][] = mul(sub(A21, B11),add(B11 , B12), newSize);
		int s7[][] = mul(sub(A12, B22),add(B21 , B22), newSize);
		
		int C11[][] = add(sub(add(s1,s4),s5),s7);
		int C12[][] = add(s3,s5);
		int C21[][] = add(s2,s4);
		int C22[][] = add(sub(add(s1,s3),s2),s6);
		
		for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                C[i][j] = C11[i][j];
                C[i][j + newSize] = C12[i][j];
                C[i + newSize][j] = C21[i][j];
                C[i + newSize][j + newSize] = C22[i][j];
            }
        }
		return C;
    }
}