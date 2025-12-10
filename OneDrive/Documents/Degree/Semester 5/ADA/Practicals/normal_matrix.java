import java.util.*;

public class normal_matrix {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Input dimensions
            System.out.println("");
            System.out.print("Enter rows and columns of Matrix A: ");
            int rowsA = sc.nextInt();
            int colsA = sc.nextInt();
            
            System.out.print("Enter rows and columns of Matrix B: ");
            int rowsB = sc.nextInt();
            int colsB = sc.nextInt();
            
            // Check multiplication rule
            if (colsA != rowsB) {
                System.out.println("Matrix multiplication not possible: columns of A must equal rows of B.");
                return;
            }
            
            // Generate random matrices
            int[][] A = generateRandomMatrix(rowsA, colsA);
            int[][] B = generateRandomMatrix(rowsB, colsB);
            
            System.out.println("Matrix A:");
            display(A);
            System.out.println("Matrix B:");
            display(B);
            
            // Multiply
            int[][] C = multiply(A, B);
            
            System.out.println("Resultant Matrix (A × B):");
            display(C);
        }

        
    }
    
    public static int[][] generateRandomMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = rand.nextInt(10);
        return matrix;
    }
    
    public static void display(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
    
    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;
        
        int[][] C = new int[rowsA][colsB];
        
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                C[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
