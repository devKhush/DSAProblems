package Graphs.CourseSchedule_I;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// Exactly same as question "Detect Cycle In Directed Graph"
// https://youtu.be/WAOfKpxYHR8
// https://leetcode.com/problems/course-schedule/description/
// https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/

public class CourseSchedule_I {
    /********************************* BFS Solution *******************************************
     * Time Complexity : O(V+E) + O(V) + O(V+E)  ~  O(V+E)
        * BFS Time Complexity
     * Space Complexity: O(V+E) + O(V) + O(V)    ~  O(V+E)
        * O(V+E) for adjacency list
        * two O(V) for inDegree and BFS queue each
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int V = numCourses;
        ArrayList<Integer>[] adj = getAdjList(numCourses, prerequisites);

        // Same as problem "Detect Cycle in Directed Graph"
        int[] inDegree = new int[V];
        for (int node = 0; node < V; node++)
            for (int neighbour : adj[node])
                inDegree[neighbour]++;

        Queue<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < V; node++)
            if (inDegree[node] == 0)
                queue.add(node);
        int nodesFound = 0;

        while (!queue.isEmpty()){
            int node = queue.remove();
            nodesFound++;
            for (int neighbour : adj[node]){
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0)
                    queue.add(neighbour);
            }
        }
        return nodesFound == V;
    }

    private ArrayList<Integer>[] getAdjList(int V, int[][] edges){
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int node = 0; node < V; node++){
            adj[node] = new ArrayList<Integer>();
        }
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
        }
        return adj;
    }
}
