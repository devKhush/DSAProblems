package Graphs.FloodFill;

// https://youtu.be/C-2_uSRli8o
// https://takeuforward.org/graph/flood-fill-algorithm-graphs/
// https://leetcode.com/problems/flood-fill/description/
// https://www.geeksforgeeks.org/flood-fill-algorithm/

public class FloodFill_V2 {
    /*********************************** DFS Solution **************************************8
     * Time Complexity: O(mn)
        * O(mn) in worst case, if all pixels need to be colored
     * Space Complexity: O(mn)
        * O(mn) recursive stack space in worst case
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int m = image.length;
        int n = image[0].length;
        int[][] new_img = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                new_img[i][j] = image[i][j];
            }
        }

        int di[] = {-1, 0, +1, 0};
        int dj[] = {0, +1, 0, -1};
        int initColor = image[sr][sc];

        dfs(sr, sc, m, n, new_img, image, newColor, di, dj, initColor);
        return new_img;
    }

    private void dfs(int row, int col, int m, int n, int[][] new_img, int[][] image,
                     int newColor, int[] di, int[] dj, int initColor) {
        new_img[row][col] = newColor;

        for(int i = 0; i < 4; i++) {
            int newI = row + di[i];
            int newJ = col + dj[i];
            if(newI>=0 && newI<m && newJ>=0 && newJ < n &&
                    image[newI][newJ] == initColor && new_img[newI][newJ] != newColor) {
                dfs(newI, newJ, m, n, new_img, image, newColor, di, dj, initColor);
            }
        }
    }
}
