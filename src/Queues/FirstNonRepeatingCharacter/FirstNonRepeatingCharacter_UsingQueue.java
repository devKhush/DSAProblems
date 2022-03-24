package Queues.FirstNonRepeatingCharacter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// https://www.youtube.com/watch?v=2Z--YYbzigU&t=195s

public class FirstNonRepeatingCharacter_UsingQueue {
    public int firstUniqChar(String s) {
        // stores the count of the characters
        HashMap<Character, Integer> count = new HashMap<>();

        // queue stores the first non-repeating characters
        Queue<Character> queue = new LinkedList<>();

        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            count.put(ch, count.getOrDefault(ch, 0)+1);

            if (count.get(ch) == 1)
                queue.add(ch);

            while (!queue.isEmpty()  &&  count.get(queue.peek()) != 1)
                queue.remove();


            // To print the first non-repeating characters, some extra part of
            // this question, can be counted as next level problem
             if (!queue.isEmpty())
                 System.out.print(queue.peek() + " ");
             else
                 System.out.print(-1 + " ");
        }

        return !queue.isEmpty() ? s.indexOf(queue.peek()) : -1;
    }

    public static void main(String[] args) {
        String s = "aabbcdddakgsac";
        int answer = new FirstNonRepeatingCharacter_UsingQueue().firstUniqChar(s);
        System.out.println();

        System.out.println(answer);
    }
}
