// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++){
                String[] S = br.readLine().trim().split(" ");
                for(int j = 0; j < m; j++){
                    grid[i][j] = Integer.parseInt(S[j]);
                }
            }
            Solution obj = new Solution();
            int ans = obj.orangesRotting(grid);
            System.out.println(ans);
        }
    }
}// } Driver Code Ends


class Solution{
    //Function to find minimum time required to rot all oranges. 
    public int orangesRotting(int[][] grid)    {
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