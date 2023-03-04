package Graphs.FloodFill;
import java.util.ArrayDeque;
import java.util.Queue;

// https://youtu.be/C-2_uSRli8o
// https://takeuforward.org/graph/flood-fill-algorithm-graphs/
// https://leetcode.com/problems/flood-fill/description/
// https://www.geeksforgeeks.org/flood-fill-algorithm/

public class FloodFill {
    /*********************************** DFS Solution **************************************8
     * Time Complexity: O(4*mn)
        * O(mn) in worst case, if all pixels need to be colored
     * Space Complexity: O(mn)
        * O(mn) recursive stack space in worst case
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;

        // avoid pixel coloring if already colored with same color
        if (image[sr][sc] != color)
            dfs(sr, sc, m, n, image, color, image[sr][sc]);
        return image;
    }

    private void dfs(int i, int j, int m, int n, int[][] image, int color, int prevColor){
        if (i < 0 || j < 0 || i >= m || j >= n || image[i][j] != prevColor) return;

        image[i][j] = color;
        dfs(i - 1, j, m, n, image, color, prevColor);
        dfs(i + 1, j, m, n, image, color, prevColor);
        dfs(i, j - 1, m, n, image, color, prevColor);
        dfs(i, j + 1, m, n, image, color, prevColor);
    }


    /************************************** BFS Solution ************************************
     * Time Complexity: O(4*mn)
        * O(mn) in worst case, if all pixels need to be colored
     * Space Complexity: O(mn)
        * O(mn) bfs queue size in worst case
     */
    public int[][] floodFillAlgorithm(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, -1, 1};
        int prevColor = image[sr][sc];

        Queue<int[]> queue = new ArrayDeque<>();
        if (image[sr][sc] != color){
            queue.add(new int[]{sr, sc});
            image[sr][sc] = color;
        }

        while (!queue.isEmpty()){
            int i = queue.peek()[0];
            int j = queue.peek()[1];
            queue.remove();

            for (int a = 0; a < 4; a++){
                int newI = i + di[a];
                int newJ = j + dj[a];
                if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && image[newI][newJ] == prevColor){
                    queue.add(new int[]{newI, newJ});
                    image[newI][newJ] = color;
                }
            }
        }
        return image;
    }
}
