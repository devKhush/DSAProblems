package Graphs.NumberOfDistinctIslands;
import java.util.*;

// https://youtu.be/7zmgQSJghpo
// https://takeuforward.org/data-structure/number-of-distinct-islands/
// https://www.geeksforgeeks.org/find-the-number-of-distinct-islands-in-a-2d-matrix/
// https://practice.geeksforgeeks.org/problems/number-of-distinct-islands/1

public class NumberOfDistinctIslands_BFS {
    /*********************************** BFS Solution *******************************************
     * Time Complexity: O(mn) + O(4*mn) + O(mn)  ~  O(mn)
        * O(mn) for BFS loop
        * O(mn) for BFS traversal in worst case
        * O(mn) for Hashing of coordinates in worst case
     * Space Complexity: O(mn) + O(mn) + O(mn) + O(mn)  ~  O(mn)
        * Each O(mn) for Visited_array, BFS_queue, HashSet_size, and Coordinate_objects in worst case.
     */
    public int countDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        // set for shape's coordinate
        HashSet<ArrayList<Coordinate>> set = new HashSet<>();

        // Run BFS on each cell with value 1
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1  && !visited[i][j]){

                    // Start BFS Traversal
                    ArrayList<Coordinate> shape = new ArrayList<>();
                    int srcI = i;
                    int srcJ = j;

                    Queue<Coordinate> queue = new ArrayDeque<>();           // BFS queue
                    queue.add(new Coordinate(i, j));
                    shape.add(new Coordinate(i - srcI, j - srcJ));
                    visited[i][j] = true;

                    while (!queue.isEmpty()){
                        Coordinate loc = queue.remove();
                        for (int a = 0; a < 4; a++){
                            int newI = loc.i + di[a];
                            int newJ = loc.j + dj[a];
                            if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ]==1 && !visited[newI][newJ]){
                                queue.add(new Coordinate(newI, newJ));
                                visited[newI][newJ] = true;
                                shape.add(new Coordinate(newI - srcI, newJ - srcJ));
                            }
                        }
                    }
                    // BFS ends
                    set.add(shape);
                }
            }
        }
        return set.size();
    }


    // *********************  Class to store Coordinate in grid  *********************
    static class Coordinate{
        int i, j;
        public Coordinate(int i, int j){
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            if (this.getClass() != obj.getClass())
                return false;
            Coordinate point = (Coordinate) obj;
            return this.i == point.i && this.j == point.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}
