// { Driver Code Starts
// Initial Template for Java

import java.util.*;
class Rat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) a[i][j] = sc.nextInt();

            Solution obj = new Solution();
            ArrayList<String> res = obj.findPath(a, n);
            Collections.sort(res);
            if (res.size() > 0) {
                for (int i = 0; i < res.size(); i++)
                    System.out.print(res.get(i) + " ");
                System.out.println();
            } else {
                System.out.println(-1);
            }
        }
    }
}
// } Driver Code Ends


// User function Template for Java

// m is the given matrix and n is the order of matrix
class Solution {
     public ArrayList<String> findPath(int[][] maze, int n) {
      // Below given are the directions & corresponding (di, dj) pairs, i.,e value by which by (i,j) changes
        // Directions:     down, left, right, up
        String[] directions = {"D", "L", "R", "U"};
        int[] di =          {+1,   0,   0,   -1};
        int[] dj =          { 0,  -1,  +1,    0};

        ArrayList<String> allPaths = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];

        if (maze[0][0] == 1)
            findAllPathsToDestination(0, 0, maze, visited, n, "", allPaths, directions, di, dj);
        return allPaths;
    }

    private void findAllPathsToDestination(int i, int j, int[][] maze, boolean[][] visited, int n,
                                           String path, ArrayList<String> allPaths, String[] directions,
                                           int[] di, int[] dj){
        if (i == n-1 && j == n-1){
            allPaths.add(path);
            return;
        }

        for (int index = 0; index < 4; index++){
            int nextI = i + di[index];
            int nextJ = j + dj[index];
            String directionToMove = directions[index];

            if (nextI < n  && nextI >= 0  &&  nextJ >= 0  && nextJ < n  && !visited[nextI][nextJ]  && maze[nextI][nextJ] == 1){
                visited[i][j] = true;
                findAllPathsToDestination(nextI, nextJ, maze, visited, n, path + directionToMove, allPaths, directions, di, dj);
                visited[i][j] = false;
            }
        }
    }
}