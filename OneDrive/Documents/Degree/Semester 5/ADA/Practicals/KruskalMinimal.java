import java.util.*;
 
//take the edges 
//sort edges in accending order 
//take the edges 
public class KruskalMinimal {

    public static int find(int i, int[] parent) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i], parent);
    }

    public static void union(int i, int j, int[] parent) {
        int rootI = find(i, parent);
        int rootJ = find(j, parent);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }

    public static int[][] kruskalMST(int numVertices, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(edge -> edge[2]));

        int[][] mst = new int[numVertices - 1][3];
        int mstEdgeCount = 0;

        int[] parent = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            if (find(edge[0], parent) != find(edge[1], parent)) {
                union(edge[0], edge[1], parent);
                mst[mstEdgeCount] = edge;
                mstEdgeCount++;
                if (mstEdgeCount == numVertices - 1) {
                    break;
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of vertices: ");
        int numVertices = scanner.nextInt();

        System.out.print("Enter the total number of edges: ");
        int numEdges = scanner.nextInt();

        int[][] allPaths = new int[numEdges][3];

        System.out.println("Enter details for each edge (source destination weight):");
        for (int i = 0; i < numEdges; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            allPaths[i][0] = scanner.nextInt(); // Source
            allPaths[i][1] = scanner.nextInt(); // Destination
            allPaths[i][2] = scanner.nextInt(); // Weight
        }

        int[][] resultMST = kruskalMST(numVertices, allPaths);

        System.out.println("\n--- Minimum Spanning Tree Edges ---");
        int totalWeight = 0;
        for (int[] edge : resultMST) {
            // Check if the edge was added, as MST might not be possible for disconnected graphs
            if (edge != null) {
                System.out.println("Path from " + edge[0] + " to " + edge[1] + " with length " + edge[2]);
                totalWeight += edge[2];
            }
        }
        System.out.println("Total length of all paths: " + totalWeight);
        
        scanner.close();
    }
}