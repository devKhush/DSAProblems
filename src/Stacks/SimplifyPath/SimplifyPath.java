package Stacks.SimplifyPath;
import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/simplify-path/description/

public class SimplifyPath {
    /************************************** Deque Solution ********************************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String simplifyPath(String path) {
        String[] dirs = path.split("/");
        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < dirs.length; i++){
            if (dirs[i].equals("") || dirs[i].equals("."))
                continue;
            if (dirs[i].equals("..")){
                if (!stack.isEmpty())
                    stack.removeLast();
            }
            else
                stack.addLast(dirs[i]);
        }

        // Add directories to stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append("/");
            sb.append(stack.removeFirst());
        }
        if (sb.isEmpty())
            sb.append("/");
        return sb.toString();
    }
}
