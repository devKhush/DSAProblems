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
        // Stores all the paths that Rat can take to reach destination
        ArrayList<String> allPaths = new ArrayList<>();

        // visited boolean array
        boolean[][] visited = new boolean[n][n];

        // Finding all paths starting from maze[0][0] to maze[n-1][m-1]
        if (maze[0][0] == 1)
            findAllPathsToDestination(0, 0, maze, n, "", allPaths, visited);
        return allPaths;
    }

    private void findAllPathsToDestination(int i, int j, int[][] maze, int n, String path, ArrayList<String> allPaths, boolean[][] visited){
        // If we reached destination, store the path taken into an arraylist
        if (i == n-1 && j == n-1){
            allPaths.add(path);
            return;
        }

        // Moving downwards: Marks current cell as visited, add the Down 'D' direction into the 'path' String
        // and move downwards, after backtracking mark it as unvisited
        if (i+1 < n  &&  !visited[i+1][j]  &&  maze[i+1][j] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i+1, j, maze, n, path + "D", allPaths, visited);
            visited[i][j] = false;
        }

        // Moving Left: Marks current cell as visited, add the Left 'L' direction into the 'path' String
        // and move left, after backtracking mark it as unvisited
        if (j-1 >= 0  &&  !visited[i][j-1]  &&  maze[i][j-1] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i, j-1, maze, n, path + "L", allPaths, visited);
            visited[i][j] = false;
        }

        // Moving Right: Marks current cell as visited, add the Right 'R' direction into the 'path' String
        // and move right, after backtracking mark it as unvisited
        if (j+1 < n  &&  !visited[i][j+1]  &&  maze[i][j+1] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i, j+1, maze, n, path + "R", allPaths, visited);
            visited[i][j] = false;
        }

        // Moving Upwards: Marks current cell as visited, add the Upwards 'U' direction into the 'path' String
        // and move upwards, after backtracking mark it as unvisited
        if (i-1 >= 0  &&  !visited[i-1][j] &&  maze[i-1][j] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i - 1, j, maze, n, path + "U", allPaths, visited);
            visited[i][j] = false;
        }
    }
}