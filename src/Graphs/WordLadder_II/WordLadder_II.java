package Graphs.WordLadder_II;
import java.util.*;

// PRE_REQUISITE: "Word Ladder - I"
// https://youtu.be/AD4SFl7tu7I (Great VIDEO)
// https://takeuforward.org/graph/word-ladder-ii-optimised-approach-g-31/
// https://leetcode.com/problems/word-ladder-ii/description/

public class WordLadder_II {
    /**************************************** Efficient Solution ***********************************
     * Time Complexity: O(V * M * 26) +  O(V * M * 26)
        * One O(V * M * 26) for BFS to find the shortest paths
        * Another O(V * M * 26) for DFS to find all the sequence
     * Space Complexity: O(V)
        * BFS_Queue, Not_Visited_Set, Shortest_Path_Map, Sequence_ArrayList and Recursion_stack
     */

    // Answer list for storing all the possible transformation sequence
    List<List<String>> allSequences;

    // HashMap to store the shortest paths to all nodes starting from the beginWord till endWord
    HashMap<String, Integer> shortestPath;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        allSequences = new ArrayList<>();
        shortestPath = new HashMap<>();

        // Compute the Shortest path to all the nodes starting from the beginWord until endWord
        findShortestPaths(beginWord, endWord, wordList);

        // If endWord is not present in HashMap, it is not reachable from the beginNode
        if (shortestPath.containsKey(endWord)) {
            // Start from the endNode and go towards beginNode to avoid the redundant possibilities (SEE VIDEO)
            dfs(endWord, beginWord, new ArrayList<>());
        }
        return allSequences;
    }

    // DFS to store all the transformation sequence
    public void dfs(String node, String beginWord, ArrayList<String> sequence){
        // Base case when beginNode is reached
        if (node.equals(beginWord)){
            ArrayList<String> seq = new ArrayList<>(sequence);
            seq.add(beginWord);
            Collections.reverse(seq);
            allSequences.add(seq);
            return;
        }
        sequence.add(node);

        // Traverse all the neighbour nodes of current nodes
        char[] arr = node.toCharArray();
        for (int i = 0; i < arr.length; i++){
            char ch = arr[i];
            for (char j = 'a'; j <= 'z'; j++){
                arr[i] = j;
                String neighbour = new String(arr);
                // call DFS for only those nodes that are neighbours and are in level-wise format
                // acc. to the shortest path given by BFS traversal
                if (shortestPath.containsKey(neighbour)  &&  shortestPath.get(neighbour) + 1 == shortestPath.get(node)){
                    dfs(neighbour, beginWord, sequence);
                }
            }
            arr[i] = ch;
        }
        sequence.remove(sequence.size() - 1);
    }


    // Similar to the "Word Ladder-I" Problem
    public void findShortestPaths(String beginWord, String endWord, List<String> wordList) {
        // HashSet to keep track of nodes that are not visited yet in BFS
        HashSet<String> notVisited = new HashSet<>();
        notVisited.add(beginWord);
        for (String word : wordList)
            notVisited.add(word);

        // BFS Queue
        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        notVisited.remove(beginWord);   // start node has been visited, so remove it from thr unvisited set
        shortestPath.put(beginWord, 0);

        while (!queue.isEmpty()){
            String node = queue.remove();
            char[] arr = node.toCharArray();
            if (node.equals(endWord))
                return;

            for (int i = 0; i < arr.length; i++){
                char ch = arr[i];
                for (char j = 'a'; j <= 'z'; j++){
                    arr[i] = j;
                    String neighbour = new String(arr);
                    if (notVisited.contains(neighbour)){
                        notVisited.remove(neighbour);
                        queue.add(neighbour);
                        shortestPath.put(neighbour, shortestPath.get(node) + 1);
                    }
                }
                arr[i] = ch;
            }
        }
    }
}
