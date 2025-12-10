
import java.io.*;
import java.lang.*;
import java.util.*;

class MST {
    int minKey(int key[], Boolean mstSet[]) {
  
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        int V = mstSet.length;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        int V = graph.length;
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t"
                    + graph[i][parent[i]]);
    }

    void primMST(int graph[][]) {
        int V = graph.length;

        int parent[] = new int[V];

      
        int key[] = new int[V];

        
        Boolean mstSet[] = new Boolean[V];

        
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;

        // First node is always root of MST
        parent[0] = -1;

        
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && mstSet[v] == false
                        && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }
        printMST(parent, graph);
    }

   
    public static int[][] generateRandomGraph(int V, int E) {
        int maxEdges = (V * (V - 1)) / 2;
        if (E > maxEdges) {
            System.out.println("Error: Number of edges exceeds the maximum possible for " + V + " vertices. Using max edges.");
            E = maxEdges;
        }

        int[][] graph = new int[V][V];
        Random rand = new Random();
        int edgesAdded = 0;

        while (edgesAdded < E) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);
            
            
            if (u != v && graph[u][v] == 0) {
                int weight = rand.nextInt(50) + 1; 
                graph[u][v] = weight;
                graph[v][u] = weight; 
                edgesAdded++;
            }
        }
        return graph;
    }

    // *** NEW: Utility function to print the adjacency matrix ***
    public static void printGraph(int[][] graph) {
        int V = graph.length;
        System.out.println("Generated Adjacency Matrix:");
        System.out.print("   ");
        for (int i = 0; i < V; i++) {
            System.out.printf("%-4d", i);
        }
        System.out.println("\n--------------------------");

        for (int i = 0; i < V; i++) {
            System.out.printf("%-2d| ", i);
            for (int j = 0; j < V; j++) {
                System.out.printf("%-4d", graph[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();
        
        System.out.print("Enter the number of edges: ");
        int E = sc.nextInt();
        
        MST t = new MST();

        int graph[][] = generateRandomGraph(V, E);

        printGraph(graph);
        System.out.println("\nCalculating MST...");

        t.primMST(graph);
        
        sc.close();
    }
}