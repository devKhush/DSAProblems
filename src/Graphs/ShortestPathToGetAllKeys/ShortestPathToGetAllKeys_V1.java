package Graphs.ShortestPathToGetAllKeys;
import java.util.*;

// https://youtu.be/boKdhdIjE5E
// https://leetcode.com/problems/shortest-path-to-get-all-keys/description/
// https://leetcode.com/problems/shortest-path-to-get-all-keys/editorial/

public class ShortestPathToGetAllKeys_V1 {
    /********************************** Compact BFS + BitMasking solution *********************************
     * See Video for detail intuition

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

        // Visited array/set & Queue in BFS
        HashSet<Pair> visited =new HashSet<>();
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(srcI, srcJ, 0));
        visited.add(new Pair(srcI, srcJ, 0));

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                Pair pair = queue.remove();
                if (pair.third == (1 << maxKey) - 1)
                    return distance;

                for (int a = 0; a < 4; a++){
                    int i = pair.first;
                    int j = pair.second;
                    int keys = pair.third;
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
                        Pair next = new Pair(nextI, nextJ, keys);
                        if (!visited.contains(next)){
                            queue.add(next);
                            visited.add(next);
                        }
                    }
                }
            }
            distance++;
        }
        return -1;
    }


    /************************************ BFS + BitMasking solution **************************************
     * See Video for detail intuition

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
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        // Find th starting cell in the matrix and number of keys
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

        // Visited array/set & Queue in BFS
        HashSet<Pair> visited =new HashSet<>();
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(srcI, srcJ, 0));
        visited.add(new Pair(srcI, srcJ, 0));

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                Pair pair = queue.remove();
                int i = pair.first;
                int j = pair.second;
                int keys = pair.third;
                if (keys == (1 << maxKey) - 1)
                    return distance;

                for (int a = 0; a < 4; a++){
                    int nextI = i + di[a];
                    int nextJ = j + dj[a];

                    if (nextI >= 0 && nextJ >= 0 && nextI < m && nextJ < n  &&  grid[nextI].charAt(nextJ) != '#'){
                        char ch = grid[nextI].charAt(nextJ);

                        // Traverse to empty OR already revisited cell only if we got more keys than previously
                        if ((ch == '.' || ch == '@')  &&  !visited.contains(new Pair(nextI, nextJ, keys))){
                            queue.add(new Pair(nextI, nextJ, keys));
                            visited.add(new Pair(nextI, nextJ, keys));
                        }
                        // We got an key
                        else if (ch >= 'a' && ch <= 'z'){
                            int newKeyPosition = ch - 'a';
                            int newKeys = keys | (1 << newKeyPosition);
                            if (!visited.contains(new Pair(nextI, nextJ, newKeys))){
                                queue.add(new Pair(nextI, nextJ, newKeys));
                                visited.add(new Pair(nextI, nextJ, newKeys));
                            }
                        }
                        // We got an lock
                        else if (ch >= 'A' && ch <= 'Z'){
                            int lock = ch - 'A';
                            if (((keys >> lock) & 1) == 1   &&  !visited.contains(new Pair(nextI, nextJ, keys))){
                                queue.add(new Pair(nextI, nextJ, keys));
                                visited.add(new Pair(nextI, nextJ, keys));
                            }
                        }
                    }
                }
            }
            distance++;
        }
        return -1;
    }




    static class Pair{
        int first;
        int second;
        int third;
        public Pair(int f, int s, int t){
            first = f;
            second = s;
            third = t;
        }
        @Override
        public boolean equals(Object o){
            Pair p = (Pair) o;
            return first == p.first && second == p.second && third == p.third;
        }
        @Override
        public int hashCode(){
            return Objects.hash(first, second, third);
        }
    }
}
