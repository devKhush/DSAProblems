package Graphs.LastDayWhereYouCanStillCross;

// https://leetcode.com/problems/last-day-where-you-can-still-cross/description/
// https://leetcode.com/problems/last-day-where-you-can-still-cross/editorial/

public class DSU_OnWaterCells {
    /******************************* Disjoint Set (on Water cells) ***********************************
     * Fastest solution to this problem (98%)
     * Intuition:
        * Do DSU_onLandCells first (similar to that approach)
        * In contrast to the previous method of connecting the land cells in a reversed time, we now aim
            to connect water cells instead.
        * If we find a water pathway that connects the leftmost and rightmost columns on a particular
            day i, it implies that there is no land pathway that connects the top and bottom rows,
            which is the day we are looking for.
        * In short: the last day when a land pathway exists is right before the first day when a water
            pathway exists.

     * Time Complexity:  O(row * col)
        * DSU almost runs in constant time
     * Space Complexity: O(row * col)
     */
    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] grid = new int[row][col];
        int[] di = {1, -1, 0, 0, -1, -1, 1, 1};
        int[] dj = {0, 0, 1, -1, -1, 1, -1, 1};

        // Two extra nodes in disjoint set to check for 0th col and last col
        DisjointSet ds = new DisjointSet(row*col + 2);

        // Traverse the water cells & convert land cell to water cell
        for (int day = 0; day < row*col; day++){
            int i = cells[day][0] - 1;
            int j = cells[day][1] - 1;
            grid[i][j] = 1;

            for (int a = 0; a < 8; a++){
                int nextI = i + di[a];
                int nextJ = j + dj[a];
                if (nextI < 0 || nextJ < 0 || nextI >= row || nextJ >= col || grid[nextI][nextJ] != 1)
                    continue;
                ds.union(i*col + j + 1, nextI*col + nextJ + 1);
            }

            // Check if all water cells can separate all path from top to bottom
            if (j == 0)
                ds.union(0, i*col + j + 1);
            if (j == col - 1)
                ds.union(row*col + 1, i*col + j + 1);
            if (ds.areConnected(0, row*col+1))
                return day;
        }
        return -1;
    }

    static class DisjointSet{
        int[] parent, size;
        int V;
        public DisjointSet(int V){
            this.V = V;
            parent = new int[V];
            size = new int[V];
            for (int i = 0; i < V; i++){
                size[i] = 1;
                parent[i] = i;
            }
        }

        public int parent(int u){
            if (parent[u] == u)
                return u;
            return parent[u] = parent(parent[u]);
        }
        public void union(int u, int v){
            int parU = parent(u), parV = parent(v);
            if (parU == parV)
                return;
            if (size[parU] >= size[parV]){
                parent[parV] = parU;
                size[parU] += size[parV];
            }
            else{
                parent[parU] = parV;
                size[parV] += size[parU];
            }
        }
        public boolean areConnected(int u, int v){
            return parent(u) == parent(v);
        }
    }
}
