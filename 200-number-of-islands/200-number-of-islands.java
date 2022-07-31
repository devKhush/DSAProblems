class Solution {
   public void bfs(int i, int j, int m, int n, char[][] grid, boolean[][] visited){
        int[] dx = {0,  0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new int[]{i, j});

        visited[i][j] = true;

        while (!bfsQueue.isEmpty()){
            i  = bfsQueue.peek()[0];
            j  = bfsQueue.peek()[1];
            bfsQueue.remove();

            for (int a = 0; a < 4; a++){
                int nextI = i + dx[a];
                int nextJ = j + dy[a];

                if (nextI >= 0  &&  nextJ >= 0  && nextI < m  &&  nextJ < n  && grid[nextI][nextJ] == '1' && !visited[nextI][nextJ]) {
                    bfsQueue.add(new int[]{nextI, nextJ});
                    visited[nextI][nextJ] = true;
                }
            }
        }
    }

    
    public int numIslands(char[][] grid) {
       int m = grid.length, n = grid[0].length;
        int numberOfIslands = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'  &&  !visited[i][j]){
                    numberOfIslands++;
                    bfs(i, j, m, n, grid, visited);
                }
            }
        }
        return numberOfIslands;
    }
}