class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int totalOranges = 0;
        
        Queue<int[]> rottenOrangesQueue = new ArrayDeque<>();
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1 || grid[i][j] == 2)
                    totalOranges++;
                if (grid[i][j] == 2)
                    rottenOrangesQueue.add(new int[]{i, j});
            }
        
        int[] dx = {1, -1, 0,  0};
        int[] dy = {0,  0, 1, -1};
        int totalOrangesRottened = 0;
        int totalMinutes = 0;
        
        while (!rottenOrangesQueue.isEmpty()){
            int currRottenOranges = rottenOrangesQueue.size();
            totalOrangesRottened += currRottenOranges; 
            
            for (int i = 1; i <= currRottenOranges; i++){
                int[] currRottenOrangeLocation = rottenOrangesQueue.remove();
                
                for (int j = 0; j < 4; j++){
                
                    int nextX = currRottenOrangeLocation[0] + dx[j];
                    int nextY = currRottenOrangeLocation[1] + dy[j];
                
                    if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n && grid[nextX][nextY] == 1){
                        grid[nextX][nextY] = 2;
                        rottenOrangesQueue.add(new int[]{nextX, nextY});
                    }
                }
            }
            if (!rottenOrangesQueue.isEmpty())
                totalMinutes++;
        }
        
        return totalOranges == totalOrangesRottened ? totalMinutes : -1;
    }
}