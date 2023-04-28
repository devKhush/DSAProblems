package Recursions_And_BackTracking.RatInAMaze;
import java.util.ArrayList;

// https://youtu.be/bLGZhJlt4y0
// https://takeuforward.org/data-structure/rat-in-a-maze/
// https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/

public class RatInAMaze_Simple {
    public ArrayList<String> findAllPathsToDestination(int[][] maze, int n) {
        // Below given are the directions & corresponding (di, dj) pairs, i.,e value by which by (i,j) changes
        // Directions:     down, left, right, up
        String[] directions = {"D", "L", "R", "U"};
        int[] di =          {+1,   0,   0,   -1};
        int[] dj =          { 0,  -1,  +1,    0};

        // Stores all the paths that Rat can take to reach destination
        ArrayList<String> allPaths = new ArrayList<>();
        // visited boolean array
        boolean[][] visited = new boolean[n][n];

        // Finding all paths starting from maze[0][0] to maze[n-1][n-1]
        // we need to check first element in maze to be 1 because we are not checking inside recursion
        // if maze[0][0] we can't even move a single step
        if (maze[0][0] == 1)
            findAllPathsToDestination(0, 0, maze, visited, n, "", allPaths, directions, di, dj);
        return allPaths;
    }

    private void findAllPathsToDestination(int i, int j, int[][] maze, boolean[][] visited, int n,
                                           String path, ArrayList<String> allPaths, String[] directions,
                                           int[] di, int[] dj){
        // If we reached destination, store the path taken into an arraylist
        if (i == n-1 && j == n-1){
            allPaths.add(path);
            return;
        }
        visited[i][j] = true;

        // Moving Down, Left, Right & Up one by one in a for loop
        // Marks current cell as visited, add the current direction into the 'path' String
        // and move into that direction, after backtracking mark it as unvisited
        for (int index = 0; index < 4; index++){
            int nextI = i + di[index];
            int nextJ = j + dj[index];
            String directionToMove = directions[index];

            if (nextI < n  && nextI >= 0  &&  nextJ >= 0  && nextJ < n  && !visited[nextI][nextJ]  && maze[nextI][nextJ] == 1){
                findAllPathsToDestination(nextI, nextJ, maze, visited, n, path + directionToMove, allPaths, directions, di, dj);
            }
        }
        visited[i][j] = false;
    }

    /******************************************* Another Solution **************************************
     * TC -> O(9^(mn))
     * SC -> O(mn)
        * Recursion Stack Space
     */
    public static ArrayList<String> findPath(int[][] mat, int n) {
        // Paths from src to destination
        ArrayList<String> paths = new ArrayList<>();

        // Visited array
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
