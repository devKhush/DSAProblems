package Graphs.NumberOfDistinctIslands;
import java.util.ArrayList;
import java.util.HashSet;

// https://youtu.be/7zmgQSJghpo
// https://takeuforward.org/data-structure/number-of-distinct-islands/
// https://www.geeksforgeeks.org/find-the-number-of-distinct-islands-in-a-2d-matrix/
// https://practice.geeksforgeeks.org/problems/number-of-distinct-islands/1

public class NumberOfDistinctIslands_DFS {
    /*********************************** DFS Solution *******************************************
     * Time Complexity: O(mn) + O(4*mn) + O(mn)  ~  O(mn)
        * O(mn) for BFS loop
        * O(mn) for BFS traversal in worst case
        * O(mn) for Hashing of coordinates in worst case
     * Space Complexity: O(mn) + O(mn) + O(mn)  ~  O(mn)
        * Each O(mn) for Visited_array, HashSet_size and Recursion_stack_space in worst case.
     */
    public int countDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // set for shape's coordinate
        HashSet<ArrayList<Integer>> set = new HashSet<>();

        // Run DFS on each cell with value 1
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1 && !visited[i][j]){
                    ArrayList<Integer> shape = new ArrayList<>();
                    dfs(i, j, grid, visited, shape, i, j);
                    set.add(shape);
                }
            }
        }
        return set.size();
    }

    private void dfs(int i, int j, int[][] grid, boolean[][] visited, ArrayList<Integer> shape,
            int srcI, int srcJ){
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1 || visited[i][j])
            return;

        visited[i][j] = true;
        shape.add(i - srcI);
        shape.add(j - srcJ);

        dfs(i - 1, j, grid, visited, shape, srcI, srcJ);
        dfs(i, j + 1, grid, visited, shape, srcI, srcJ);
        dfs(i, j - 1, grid, visited, shape, srcI, srcJ);
        dfs(i + 1, j, grid, visited, shape, srcI, srcJ);
    }
}
