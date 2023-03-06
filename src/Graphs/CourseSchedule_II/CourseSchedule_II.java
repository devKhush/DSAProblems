package Graphs.CourseSchedule_II;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// PRE-REQUISITE: "Detect cycle in undirected graph" and "Topological Sort"
// https://youtu.be/WAOfKpxYHR8
// https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/
// https://leetcode.com/problems/course-schedule-ii/submissions/910222285/
// https://www.geeksforgeeks.org/find-the-ordering-of-tasks-from-given-dependencies/

public class CourseSchedule_II {
    /************************************ BFS Solution **********************************
     * Combined both the solution "Detect Cycle and Topological sort" of Directed graph
     * Time Complexity: O(V+E) + O(V) + O(V+E)        ~  O(V+E)
     * Space Complexity: O(V+E) + O(V) + O(V)  ~  O(V+E)
     */
    public int[] findCourseOrder_BFS(int numCourses, int[][] prerequisites) {
        int V = numCourses;
        ArrayList<Integer>[] adj = getAdjList(numCourses, prerequisites);

        int[] inDegree = new int[V];
        for (int node = 0; node < V; node++){
            for (int neighbour : adj[node])
                inDegree[neighbour]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < V; node++){
            if (inDegree[node] == 0)
                queue.add(node);
        }

        int nodesFound = 0;
        int[] topoSort = new int[V];
        int i = V - 1;

        while (!queue.isEmpty()){
            int node = queue.remove();
            topoSort[i--] = node;
            nodesFound++;

            for (int neighbour : adj[node]){
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0)
                    queue.add(neighbour);
            }
        }
        if (nodesFound == V) return topoSort;
        return new int[0];
    }


    /*************************************** DFS Solution ***********************************8
     * Combined both the solution "Detect Cycle and Topological sort" of Directed graph
     * Time Complexity: O(V+E) + O(V) + O(V+E)        ~  O(V+E)
     * Space Complexity: O(V+E) + O(V) + O(V) + O(V)  ~  O(V+E)
     */
    public ArrayList<Integer> findCourseOrder_DFS(int numCourses, int[][] prerequisites) {
        int V = numCourses;
        ArrayList<Integer>[] adj = getAdjList(numCourses, prerequisites);

        ArrayList<Integer> courseOrder = new ArrayList<>();
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        for (int node = 0; node < V; node++){
            // If there is a cycle in the Directed graph, return an empty list
            if (!visited[node]) {
                if (dfs(node, adj, visited, pathVisited, courseOrder))
                    return new ArrayList<>();
            }
        }
        return courseOrder;
    }

    private boolean dfs(int node, ArrayList<Integer>[] adj, boolean[] visited, boolean[] pathVisited, ArrayList<Integer> courseOrder){
        visited[node] = true;
        pathVisited[node] = true;

        for (int neighbour : adj[node]){
            if (!visited[neighbour]){
                if (dfs(neighbour, adj, visited, pathVisited, courseOrder))
                    return true;
            }
            else if (pathVisited[neighbour])
                return true;
        }
        pathVisited[node] = false;
        courseOrder.add(node);
        return false;
    }


    // Generate Adjacency list
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