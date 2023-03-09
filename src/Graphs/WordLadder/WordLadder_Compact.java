package Graphs.WordLadder;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

// PREREQUISITE: "Shortest distance between two nodes in an Undirected Graph with unit weights"
// https://youtu.be/tRPda0rcf8E
// https://takeuforward.org/graph/word-ladder-i-g-29/
// https://leetcode.com/problems/word-ladder/description/
// https://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/

public class WordLadder_Compact {
    /*************************************** Easy Solution *********************************************888
     * Time Complexity: O(V * M * 26)
        * Time Complexity is O(V * M * 26 * log(V)) in worst case, duw to set of size V.
        * V = n = no. of words
        * M = length of each word
        * At max the size of Queue can be 'V'
        * For each word, we change all the 'M' characters one by one.
     * Space Complexity: O(V) + O(V) + O(V)  ~  O(V)
        * BFS_Queue, Visited_Array and Set to search all words efficiently.
        * We can have O(2*V) solution also, see the video, GFG and tuf article.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Set to store all words, so that searching can be done in O(1) time
        HashSet<String> set = new HashSet<>(wordList);

        // Visited array
        HashSet<String> visited = new HashSet<>();

        // BFS Queue
        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        visited.add(beginWord);

        // Sequence length
        int sequence = 1;

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                // Take out a String node from the BFS queue
                String node = queue.remove();
                char[] word = node.toCharArray();
                if (node.equals(endWord))
                    return sequence;

                // Change all the M characters one by one from 'a' to 'z', and check if its present in set or not
                for (int i = 0; i < word.length; i++){
                    char ch = word[i];
                    for (char j = 'a'; j <= 'z'; j++){
                        word[i] = j;
                        String modified = new String(word);
                        if (!visited.contains(modified) && set.contains(modified)){
                            visited.add(modified);
                            queue.add(modified);
                        }
                    }
                    word[i] = ch;
                }
            }
            sequence++;
        }
        return 0;
    }
}
