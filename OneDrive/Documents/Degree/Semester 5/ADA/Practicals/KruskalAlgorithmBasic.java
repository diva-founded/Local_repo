import java.util.Arrays;

public class KruskalAlgorithmBasic {

    public static int findTeamCaptain(int person, int[] teamCaptains) {
        if (teamCaptains[person] == person) {
            return person;
        }
        return teamCaptains[person] = findTeamCaptain(teamCaptains[person], teamCaptains);
    }

    public static void mergeTeams(int personA, int personB, int[] teamCaptains) {
        int captainA = findTeamCaptain(personA, teamCaptains);
        int captainB = findTeamCaptain(personB, teamCaptains);
        if (captainA != captainB) {
            teamCaptains[captainA] = captainB;
        }
    }

    public static void findMinimumSpanningTree(int numVertices, int[][] edges) {
        
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int[][] mst = new int[numVertices - 1][3];
        int mstEdgeCount = 0;
        
        int[] teamCaptains = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            teamCaptains[i] = i;
        }

        System.out.println("\nChoosing paths for the Minimum Spanning Tree...");

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            if (findTeamCaptain(u, teamCaptains) != findTeamCaptain(v, teamCaptains)) {
                
                mst[mstEdgeCount] = edge;
                mstEdgeCount++;
                
                System.out.println("  - Keep path: " + u + " to " + v + " (length " + weight + ")");

                mergeTeams(u, v, teamCaptains);

                if (mstEdgeCount == numVertices - 1) {
                    break;
                }
            } else {
                System.out.println("  - Skip path: " + u + " to " + v + " (length " + weight + ") - would create a cycle!");
            }
        }

        System.out.println("\n--- The Final Minimum Spanning Tree ---");
        int totalWeight = 0;
        for (int[] edge : mst) {
            if (edge != null) { 
                System.out.println("Path from " + edge[0] + " to " + edge[1] + " with length " + edge[2]);
                totalWeight += edge[2];
            }
        }
        System.out.println("Total length of all paths: " + totalWeight);
    }

    public static void main(String[] args) {
        int numVertices = 4;

        int[][] allPaths = {
            {0, 1, 10},
            {0, 2, 6},
            {0, 3, 5},
            {1, 3, 15},
            {2, 3, 4}
        };
        
        findMinimumSpanningTree(numVertices, allPaths);
    }
}