package Graphs.AlienDictionary;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// https://youtu.be/U3N_je7tWAs
// https://takeuforward.org/data-structure/alien-dictionary-topological-sort-g-26/
// https://practice.geeksforgeeks.org/problems/alien-dictionary/1
// https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
// TIP to REMEMBER: Whenever there is mentioned "something before something", always think of topological sort

public class AlienDictionary {
    /**************************************** BFS Solution ******************************************
     * Intuition:
        * If a character 'b' comes before 'd', then we need to keep character with the smallest character first.
        * We can say that character b has an edge to d (b --> d), and hence b should come before d in
            topo-sort of graph.
        * Convert the Dictionary data to graph
     * Time Complexity: O(V) + O(n*m) + O(V+E) + O(V) + O(V+E)  ~  O(m*n) + O(V+E)
        * n -> length of dictionary
        * m -> average length of each word
        * V, E -> Vertices and Edges
     * Space Complexity: O(V+E) + O(V) + O(V)  ~  O(V+E)
        * O(V+E) for Adjacency_List_graph
        * O(V) for each InDegree_Array & BFS_Queue
     */
    public String findOrder(String [] dict, int n, int V){
        // Generate the Adjacency List from the Dictionary data
        // If information about V vertices are not given, take V = 26 (all 26 alphabetical characters)
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int node = 0; node < V; node++)
            adj[node] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            String s1 = dict[i], s2 = dict[i + 1];
            int l1 = s1.length(), l2 = s2.length();
            for (int j = 0; j < Math.min(l1, l2); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adj[s1.charAt(j) - 'a'].add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }

        // Same as topological sort
        int[] inDegree = new int[V];
        for (int node = 0; node < V; node++) {
            for (int neighbour : adj[node])
                inDegree[neighbour]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < V; node++) {
            if (inDegree[node] == 0)
                queue.add(node);
        }
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int node = queue.remove();
            sb.append((char) ('a' + node));

            for (int neighbour : adj[node]) {
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0)
                    queue.add(neighbour);
            }
        }
        return sb.toString();
    }
}
