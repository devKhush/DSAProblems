package Queues.FirstNonRepeatingCharacter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// https://www.youtube.com/watch?v=2Z--YYbzigU&t=195s

public class FirstNonRepeatingCharacter_UsingQueue {
    /*
    * Intuition: We will maintain the first non-repeating character on the front of the queue.
                  And will keep on removing the character from the queue if their count becomes more than 1.
    * Time Complexity: O(2 * n)  =  O(n)
    * Space Complexity: O(n) + O(n) = O(n)
     */
    public int firstUniqChar_ExtendedQuestion(String s) {
        // HashMap/ArrayMap to stores the count of the characters
        int[] count = new int[26];

        // The Queue will store the first non-repeating characters
        // Since we need first non-repeating character, we use Queue as it follows FIFO principle
        Queue<Character> nonRepeatingCharQueue = new LinkedList<>();

        for (int i = 0; i < s.length(); i++){
            // Maintains the count of all characters
            char ch = s.charAt(i);
            count[ch - 'a']++;

            // If frequency of current char is 1 (character is newly found), add it into the queue
            // as it can be first non-repeating character for later indices.
            if (count[ch - 'a'] == 1)
                nonRepeatingCharQueue.add(ch);

            // Removing all repeating characters
            while (!nonRepeatingCharQueue.isEmpty()  &&  count[nonRepeatingCharQueue.peek() -'a'] != 1)
                nonRepeatingCharQueue.remove();

            // To print the first non-repeating characters at each index, some extra part of
            // this question, can be counted as next level problem
             if (!nonRepeatingCharQueue.isEmpty())
                 System.out.print(nonRepeatingCharQueue.peek() + " ");
             else
                 System.out.print(-1 + " ");
        }
        return !nonRepeatingCharQueue.isEmpty() ? s.indexOf(nonRepeatingCharQueue.peek()) : -1;
    }


    /*
    * Intuition: We will maintain the first non-repeating character on the front of the queue.
                  And will keep on removing the character from the queue if their count becomes more than 1.
    * This is same as leetcode version of above Question
    * Time Complexity: O(2 * n)  =  O(n)
    * Space Complexity: O(n) + O(n) = O(n)
    */
    public int firstUniqChar_LeetCode(String s) {
        int[] count = new int[26];

        Queue<Integer> nonRepeatingCharIndices = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            count[ch - 'a']++;

            if (count[ch - 'a'] == 1)
                nonRepeatingCharIndices.add(i);

            while (!nonRepeatingCharIndices.isEmpty()  &&  count[s.charAt(nonRepeatingCharIndices.peek()) - 'a'] != 1)
                nonRepeatingCharIndices.remove();
        }
        return !nonRepeatingCharIndices.isEmpty()? nonRepeatingCharIndices.peek() : -1;
    }


    public static void main(String[] args) {
        String s = "aabbcdddakgsac";
        int answer = new FirstNonRepeatingCharacter_UsingQueue().firstUniqChar_ExtendedQuestion(s);
        System.out.println();

        System.out.println(answer);
    }
}
