package Hashing.EqualRowAndColumnPairs;
import java.util.ArrayList;
import java.util.HashMap;

// https://leetcode.com/problems/equal-row-and-column-pairs/

public class EqualRowAndColumnPairs {
    /************************************ Brute Force Solution ****************************************
     * Intuition: Compare every wor with every column in the grid.
     * Time complexity: O(n^3)
     * Time complexity: O(1)
     */
    public int equalPairs_BruteForce(int[][] grid) {
        int n = grid.length;
        int pairs = 0;

        for (int i = 0; i < n; i++){
            for (int col = 0; col < n; col++){
                boolean rowEquals = true;
                int j = 0, row = 0;
                while (row < n){
                    if (grid[i][j] != grid[row][col]){
                        rowEquals = false;
                        break;
                    }
                    row++;
                    j++;
                }
                if (rowEquals)
                    pairs++;
            }
        }
        return pairs;
    }

    /****************************************** HashMap Solution *************************************
     * In python dictionary keys are immutable, but not in java
     * Intuition:
        * Store the count of occurrence of every row in the map

     * Time Complexity: O(n*n)
     * Space Complexity: O(n*n)
     */
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int pairs = 0;
        HashMap<ArrayList<Integer>, Integer> map = new HashMap<>();

        // Add all the values in the map
        for (int i = 0; i < n; i++){
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < n; j++){
                arr.add(grid[i][j]);
            }
            map.put(arr, map.getOrDefault(arr, 0) + 1);
        }

        // Check the row-columns pairs
        for (int j = 0; j < n; j++){
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++){
                arr.add(grid[i][j]);
            }
            if (map.containsKey(arr))
                pairs += map.get(arr);
        }
        return pairs;
    }
}
