class Solution {
   public void bfs(int i, int j, int m, int n, char[][] grid){
        int[] dx = {0,  0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new int[]{i, j});
        
        grid[i][j] = '0';

        while (!bfsQueue.isEmpty()){
            i  = bfsQueue.peek()[0];
            j  = bfsQueue.peek()[1];
            bfsQueue.remove();

            for (int a = 0; a < 4; a++){
                int nextI = i + dx[a];
                int nextJ = j + dy[a];

                if (nextI >= 0  &&  nextJ >= 0  && nextI < m  &&  nextJ < n  && grid[nextI][nextJ] == '1') {
                    bfsQueue.add(new int[]{nextI, nextJ});
                    grid[nextI][nextJ] = '0';
                }
            }
        }
    }
    
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int numberOfIslands = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'){
                    numberOfIslands++;
                    bfs(i, j, m, n, grid);
                }
            }
        }
        return numberOfIslands;       
    }
}