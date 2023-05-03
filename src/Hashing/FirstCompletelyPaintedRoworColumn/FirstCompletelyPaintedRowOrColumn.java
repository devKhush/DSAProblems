package Hashing.FirstCompletelyPaintedRoworColumn;
import java.util.HashMap;

// https://leetcode.com/problems/first-completely-painted-row-or-column/description/

public class FirstCompletelyPaintedRowOrColumn {
    /********************************** Hashing Solution ******************************************8
     * Intuition: Keep Track/Hash of whenever a row or column get entirely filled.

     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], n * i + j);
            }
        }
        int[] rowFilled = new int[m];
        int[] colFilled = new int[n];

        for (int i = 0; i < arr.length; i++) {
            int index = map.get(arr[i]);
            rowFilled[index / n]++;
            colFilled[index % n]++;
            if (rowFilled[index / n] == n || colFilled[index % n] == m)
                return i;
        }
        return -1;
    }
}
