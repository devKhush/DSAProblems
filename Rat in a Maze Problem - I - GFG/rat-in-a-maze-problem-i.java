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
    public static ArrayList<String> findPath(int[][] maze, int n) {
        ArrayList<String> allPaths = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        findPath(0, 0, maze, n, new ArrayList<>(), allPaths, visited);
        return allPaths;
    }
    
    private static void findPath(int i, int j, int[][] maze, int n, ArrayList<Character> path, ArrayList<String> allPaths, boolean[][] visited){
        if (i < 0 || j < 0 || j >= n || i >= n)
            return;
        if (maze[i][j] == 0)
            return;
            
        if (i == n-1 && j == n-1){
            String pathTaken = "";
            for (char ch : path)
                pathTaken += ch;
            allPaths.add(pathTaken);
            return;
        }
        
        if (i+1 < n && !visited[i+1][j]){
            visited[i][j] = true;
            path.add('D');
            findPath(i+1, j, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }
        
        if (j-1 >= 0 && !visited[i][j-1]){
            visited[i][j] = true;
            path.add('L');
            findPath(i, j-1, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }
        
        if (j+1 < n && !visited[i][j+1]){
            visited[i][j] = true;
            path.add('R');
            findPath(i, j+1, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }
        
        if (i-1 >= 0 && !visited[i-1][j]){
            visited[i][j] = true;
            path.add('U');
            findPath(i-1, j, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }
    }
}