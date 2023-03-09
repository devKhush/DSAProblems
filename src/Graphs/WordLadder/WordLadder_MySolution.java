package Graphs.WordLadder;
import java.util.*;

// PREREQUISITE: "Shortest distance between two nodes in an Undirected Graph with unit weights"
// https://leetcode.com/problems/word-ladder/description/

public class WordLadder_MySolution {
    /***************************************** Solution 1 **************************************
     * Change the problem to Undirected graph with unit weights
     * Then, the solution to this problem is the Shortest distance between two nodes in an Undirected Graph
        with unit weights.

     * Time Complexity: O(n*n*m) + O(V) + O(V+E)  ~  O(n*n*m) + O(V+E)
        * V = n + 1 ~ n
        * E = total edges
        * n -> no. of words
        * m -> size of each word
     * Space Complexity: O(m*(V + E)) + O(V) + O(V)
        * O(m*(V + E)) for Adjacency list having String entries of size m
        * O(V) + O(V) for Shortest_path_array & BFS_Queue
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, ArrayList<String>> adj = getAdjacencyList(beginWord, wordList);
        if (!adj.containsKey(endWord))
            return 0;

        // Shortest Path array
        HashMap<String, Integer> shortestPath = new HashMap<>();
        for (String word : wordList) {
            shortestPath.put(word, Integer.MAX_VALUE);
        }
        shortestPath.put(beginWord, 0);

        // BFS queue
        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        // Same as Problem "Shortest distance between two nodes in an Undirected Graph with unit weights"
        while (!queue.isEmpty()) {
            String node = queue.remove();

            for (String neighbour : adj.get(node)) {
                if (shortestPath.get(neighbour) > shortestPath.get(node) + 1) {
                    shortestPath.put(neighbour, shortestPath.get(node) + 1);
                    queue.add(neighbour);
                }
            }
        }
        return shortestPath.get(endWord) != Integer.MAX_VALUE ? shortestPath.get(endWord) + 1: 0;
    }

    // Generate the Adjacency list from the graph
    public HashMap<String, ArrayList<String>> getAdjacencyList(String beginWord, List<String> wordList){
        int n = wordList.size();

        HashMap<String, ArrayList<String>> adj = new HashMap<>();
        adj.put(beginWord, new ArrayList<>());
        for (String word : wordList) {
            adj.put(word, new ArrayList<>());
        }
        for (String word : wordList) {
            if (differByOneChar(beginWord, word)) {
                adj.get(beginWord).add(word);
                adj.get(word).add(beginWord);
            }
        }
        for (int i = 0; i < n; i++) {
            String word = wordList.get(i);
            for (int j = i + 1; j < n; j++) {
                if (differByOneChar(word, wordList.get(j))) {
                    adj.get(word).add(wordList.get(j));
                    adj.get(wordList.get(j)).add(word);
                }
            }
        }
        return adj;
    }

    // Function to check if two string differ by just single character
    private boolean differByOneChar(String s1, String s2) {
        int n = s1.length();
        int unEqual = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i))
                unEqual++;
            if (unEqual > 1)
                return false;
        }
        return unEqual == 1;
    }
}
