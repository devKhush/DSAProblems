class Solution {
  public void dfs(int i, int j, int m, int n, char[][] grid, boolean[][] visited){
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0' || visited[i][j])
            return;

        grid[i][j] = '0';
        visited[i][j] = true;

        dfs(i - 1, j, m, n, grid, visited);
        dfs(i, j + 1, m, n, grid, visited);
        dfs(i + 1, j, m, n, grid, visited);
        dfs(i, j - 1, m, n, grid, visited);
    }

    
    public int numIslands(char[][] grid) {
       int m = grid.length, n = grid[0].length;
        int numberOfIslands = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'  &&  !visited[i][j]){
                    numberOfIslands++;
                    dfs(i, j, m, n, grid, visited);
                }
            }
        }
        return numberOfIslands;
    }
}