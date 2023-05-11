package Arrays.SpiralMatrix_II;

// https://leetcode.com/problems/spiral-matrix-ii/description/

public class SpiralMatrix_II {
    /************************************ Only one Solution *****************************************
     * Intuition: Spiral Traversal of Matrix
     * TC -> O(mn)
     * SC -> O(1)
     */
    public int[][] generateMatrix(int n) {
        int counter = 1;
        int[][] mat = new int[n][n];

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;

        while (top <= bottom && left <= right){
            for (int i = left; i <= right; i++){
                mat[top][i] = counter++;
            }
            top++;

            for (int i = top; i <= bottom; i++){
                mat[i][right] = counter++;
            }
            right--;

            if (top <= bottom){
                for  (int i = right; i >= left; i--){
                    mat[bottom][i] = counter++;
                }
                bottom--;
            }

            if (left <= right){
                for (int i = bottom; i >= top; i--){
                    mat[i][left] = counter++;
                }
                left++;
            }
        }
        return mat;
    }
}
