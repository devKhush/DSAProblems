package Arrays.SpiralMatrix_I;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/3Zv-s9UUrFM
// https://takeuforward.org/data-structure/spiral-traversal-of-matrix/
// https://leetcode.com/problems/spiral-matrix/description/

public class SpiralMatrix {
    /*************************************** Only possible solution *************************************
     * TC -> O(m*n)
     * SC -> O(1)
     */
    public List<Integer> spiralOrderTraversal(int[][] matrix) {
        ArrayList<Integer> spiral = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        while (top <= bottom && left <= right){
            for (int i = left; i <= right; i++){
                spiral.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++){
                spiral.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom){         // Handle case of single row
                for (int i = right; i >= left; i--){
                    spiral.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right){         // Handle case of single column
                for (int i = bottom; i >= top; i--){
                    spiral.add(matrix[i][left]);
                }
                left++;
            }
        }
        return spiral;
    }
}
