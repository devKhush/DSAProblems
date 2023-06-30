package Graphs.ShortestPathToGetAllKeys;
import java.util.*;

// https://youtu.be/boKdhdIjE5E
// https://leetcode.com/problems/shortest-path-to-get-all-keys/description/
// https://leetcode.com/problems/shortest-path-to-get-all-keys/editorial/

public class ShortestPathToGetAllKeys {
    /********************************** Compact BFS + BitMasking solution *********************************
     * See Video for detail intuition
     * Same Solution as V1
     * But here used 3d Visited Boolean array instead of set

     * Time Complexity: O(m * n * 2^k)
        * The BFS algorithm visits each cell in the grid once for each key-holding state.
        * Therefore, the worst-case time complexity is proportional to the product of the number of cells
            and the number of key-holding states. There are 2^k possible key-holding states, and
            we need to consider each one separately.
     * Space Complexity: O(m * n * 2^k)
        * To keep track of the visited cells and their key-holding states, we need to store them in seen.
        * There are at most m×n times m×n cells, and each cell can have k^2 possible key-holding states,
            so the maximum amount of space required is proportional to the product of the number of cells
            and the number of key-holding states
     */
    public int shortestPathAllKeys_compact(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        // Find the starting cell in the matrix and number of keys
        int srcI = 0, srcJ = 0;
        int maxKey = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                char ch = grid[i].charAt(j);
                if (ch == '@'){
                    srcI = i;
                    srcJ = j;
                }
                if (ch >= 'a' && ch <= 'z')
                    maxKey = Math.max(maxKey, ch - 'a' + 1);
            }
        }

        // width traversed in BFS traversal
        int distance = 0;

        // Visited array & Queue in BFS
        boolean[][][] visited = new boolean[m][n][maxKey + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{srcI, srcJ, 0});
        visited[srcI][srcJ][0] = true;

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int[] pair = queue.remove();
                if (pair[2] == (1 << maxKey) - 1)
                    return distance;

                for (int a = 0; a < 4; a++){
                    int i = pair[0];
                    int j = pair[1];
                    int keys = pair[2];
                    int nextI = i + di[a];
                    int nextJ = j + dj[a];

                    if (nextI >= 0 && nextJ >= 0 && nextI < m && nextJ < n  &&  grid[nextI].charAt(nextJ) != '#'){
                        char ch = grid[nextI].charAt(nextJ);

                        if (ch >= 'a' && ch <= 'z') {
                            keys |= (1 << (ch - 'a'));
                        }
                        else if (ch >= 'A' && ch <= 'Z'){
                            int lock = ch - 'A';
                            if (((keys >> lock) & 1) == 0)
                                continue;
                        }
                        if (!visited[nextI][nextJ][keys]){
                            visited[nextI][nextJ][keys] = true;
                            queue.add(new int[]{nextI, nextJ, keys});
                        }
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}
