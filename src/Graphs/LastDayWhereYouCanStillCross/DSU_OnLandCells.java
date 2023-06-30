package Graphs.LastDayWhereYouCanStillCross;

// https://leetcode.com/problems/last-day-where-you-can-still-cross/description/
// https://leetcode.com/problems/last-day-where-you-can-still-cross/editorial/

public class DSU_OnLandCells {
    /******************************** Disjoint Set (on Land cells) ***************************************
     * Fast Solution (85%)

     * Intuition:
        * Read Editorial
        * We need to reverse the days cells, which equals replacing water cells with land cells.
        * This is necessary because we are searching for the last day when there is a path, which is the
            same as the first day in reversed order.
        * During the union-find process, will we traverse through cells in reverse and replace the
            corresponding water cell cells[i] by land cell.
        * For each newly added land cell, we connect it with all of its neighboring land cells.
        * We repeat this process until either the first row and the last row are connected or the
            traversal is complete.
     * Time Complexity: O(3*row*col) ~ O(row * col)
     * Space Complexity: O(row * col)
     */
    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] grid = new int[row][col];
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};

        // Two extra nodes in disjoint set to check for 0th row and last row
        DisjointSet ds = new DisjointSet(row * col + 2);

        for (int i = 0; i < row*col; i++){
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        for (int i = 0; i < row; i++){
             for (int j = 0; j < col; j++){
                 if (grid[i][j] != 0) continue;
                 for (int a = 0; a < 4; a++){
                     int nextI = i + di[a];
                     int nextJ = j + dj[a];
                     if (nextI < 0 || nextJ < 0 || nextI >= row || nextJ >= col || grid[nextI][nextJ] != 0)
                         continue;
                     ds.union(i * col + j + 1, nextI * col + nextJ + 1);
                 }
             }
        }
        // Traverse the water cells in reverse order & convert water cell to land cell
        for (int day = row*col - 1; day >= 0; day--){
            int i = cells[day][0] - 1;
            int j = cells[day][1] - 1;
            grid[i][j] = 0;

            for (int a = 0; a < 4; a++){
                int nextI = i + di[a];
                int nextJ = j + dj[a];
                if (nextI < 0 || nextJ < 0 || nextI >= row || nextJ >= col || grid[nextI][nextJ] != 0)
                    continue;
                ds.union(i * col + j + 1, nextI * col + nextJ + 1);
            }
            if (i == 0)
                ds.union(0, i * col + j + 1);
            if (i == row - 1)
                ds.union(row * col + 1,  i * col + j + 1);
            if (!ds.areDisjoint(0 , row * col + 1))
                return day;
        }
        return -1;
    }


    // *****************************8 Disjoint Set Data structure ***********************************
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
        public boolean areDisjoint(int u, int v){
            return parent(u) != parent(v);
        }
    }
}
