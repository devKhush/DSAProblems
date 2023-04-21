//{ Driver Code Starts
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
        public static ArrayList<String> findPath(int[][] mat, int n) {
        ArrayList<String> paths = new ArrayList<>();
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        dfs(0, 0, mat, visited, "", paths);
        return paths;
    }

    private static void dfs(int i, int j, int[][] mat, boolean[][] visited, String path, ArrayList<String> paths) {
        if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length || mat[i][j] == 0 || visited[i][j])
            return;

        if (i == mat.length-1 && j == mat[0].length-1) {
            paths.add(path);
            return;
        }
        visited[i][j] = true;
        dfs(i + 1, j, mat, visited, path + "D", paths);
        dfs(i, j - 1, mat, visited, path + "L", paths);
        dfs(i, j + 1, mat, visited, path + "R", paths);
        dfs(i - 1, j, mat, visited, path + "U", paths);
        visited[i][j] = false;
    }

}