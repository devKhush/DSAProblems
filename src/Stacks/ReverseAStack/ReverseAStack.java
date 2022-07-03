package Stacks.ReverseAStack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// https://www.geeksforgeeks.org/reverse-a-stack-using-recursion/

public class ReverseAStack {
    /* ********************************* Brute Force: Using a Queue *******************************
    * Use a queue to reverse a Stack, since Queue follows FIFO
    * Time Complexity: O(n)
    * Space Complexity: O(n)
     */
    public Stack<Integer> reverseStack_UsingQueue(Stack<Integer> stack){
        Queue<Integer> queue = new LinkedList<>();

        while (!stack.isEmpty())
            queue.add(stack.pop());

        while (!queue.isEmpty())
            stack.push(queue.remove());
        return stack;
    }



    /* ********************************* Brute Force: Using a Stack *******************************
     * Use a stack to reverse a Stack, since Stack follows LIFO (think the approach)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public Stack<Integer> reverseStack_UsingStack(Stack<Integer> stack){
        Stack<Integer> reversedStack = new Stack<>();

        while (!stack.isEmpty())
            reversedStack.push(stack.pop());
        return reversedStack;
    }



    /*
    * INTUITION: The idea of the solution is to hold all values of stack in the
                 "Recursive Function Call Stack" until the stack becomes empty.
                 When the stack becomes empty, insert all held items one by one
                 at the bottom of the stack.
    * So we need a function that inserts at the bottom of a stack using the basic stack function.

     */
    public Stack<Integer> reverseStack(Stack<Integer> stack){
        if (stack.isEmpty())
            return stack;

        // Hold all items in Recursion Stack Space until we reach end of the stack
        int top = stack.pop();

        reverseStack(stack);

        // Insert all the values held in Function Call Stack one by one into the bottom
        // Every item is inserted at the bottom
        insertAtBottomOfStack(stack, top);

        return stack;
    }

    // Below is a recursive function that inserts an element at the bottom of a stack.
    private void insertAtBottomOfStack(Stack<Integer> stack, int bottomElement){
        if (stack.isEmpty())
            stack.push(bottomElement);
        else {
            // All items are held in Function Call Stack until we reach end of the stack.
            // When the stack becomes empty, the st.size() becomes 0, the
            // above if part is executed and the item is inserted at the bottom
            int top = stack.pop();

            insertAtBottomOfStack(stack, bottomElement);

            // push the top 'element' held in Function Call Stack, once the "bottomElement" is inserted
            // at the bottom
            stack.push(top);
        }
    }
}
