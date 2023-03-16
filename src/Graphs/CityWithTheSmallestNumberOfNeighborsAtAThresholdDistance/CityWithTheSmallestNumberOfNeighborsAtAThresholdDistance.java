package Graphs.CityWithTheSmallestNumberOfNeighborsAtAThresholdDistance;

// PRE-REQUISITE: "FLOYD WARSHALL ALGORITHM"
// https://youtu.be/PwMVNSJ5SLI
// https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
// https://takeuforward.org/data-structure/find-the-city-with-the-smallest-number-of-neighbours-at-a-threshold-distance-g-43/

public class CityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {
    /************************************** Floyd Warshall Algorithm *************************************
     * Time Complexity: O(V^3)
     * Space Complexity: O(V^2)
     */
    public int findTheCity(int V, int[][] edges, int distanceThreshold) {
        int[][] shortestPath = new int[V][V];


        // Floyd Warshall Algorithm
        for (int u = 0; u < V; u++){
            for (int v = 0; v < V; v++){
                if (u != v)
                    shortestPath[u][v] = (int)1e9;
                else
                    shortestPath[u][v] = 0;
            }
        }
        for (int[] edge : edges){
            shortestPath[edge[0]][edge[1]] = edge[2];
            shortestPath[edge[1]][edge[0]] = edge[2];
        }
        for (int k = 0; k < V; k++){
            for (int u = 0; u < V; u++){
                for (int v = 0; v < V; v++){
                    shortestPath[u][v] = Math.min(shortestPath[u][k] + shortestPath[k][v],
                            shortestPath[u][v]);
                }
            }
        }

        // Logic for the Question
        int city = -1;
        int cntThresholdCities = Integer.MAX_VALUE;
        for (int u = 0; u < V; u++){
            int count = 0;
            for (int v = 0; v < V; v++){
                if (shortestPath[u][v] <= distanceThreshold)
                    count++;
            }
            if (count <= cntThresholdCities){
                city = u;
                cntThresholdCities = count;
            }
        }
        return city;
    }
}
