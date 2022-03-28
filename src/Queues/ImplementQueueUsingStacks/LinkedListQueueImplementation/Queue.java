package Queues.ImplementQueueUsingStacks.LinkedListQueueImplementation;
import java.util.Deque;
import java.util.LinkedList;

/*
Another option is DEQUE using LinkedList
// https://www.geeksforgeeks.org/implementation-deque-using-doubly-linked-list/
 */

public class Queue {
    public static void main(String[] args) {
        LinkedList<Integer> q = new LinkedList<>();

        // Appends the specified element to the end of this list.
        q.add(1);

        // Inserts the specified element at the specified position in this list.
        // Shifts the element currently at that position (if any) and any subsequent
        // elements to the right (adds one to their indices). Format -> (index, element)
        q.add(2,3);

        // Inserts the specified element at the beginning of this list.
        q.addFirst(2);

        // Appends the specified element to the end of this list.
        //This method is equivalent to add().
        q.addLast(2);

        // Returns the number of elements in this list.
        q.size();

        // Returns the element at the specified position in this list.
        q.get(1);

        // Returns the first element in this list
        q.getFirst();

        // Returns the last element in this list
        q.getLast();

        // Retrieves, but does not remove, the head (first element) of this list. getFirst()
        q.peek();

        // Returns the first element in this list.
        q.getFirst();

        // Returns the last element in this list.
        q.getLast();

        // Returns true if this list contains the specified element.
        q.contains(2);

        // Returns the index of the first occurrence of the specified
        // element in this list, or -1 if this list does not contain the element.
        q.indexOf(2);

        // Returns the index of the last occurrence of the specified
        // element in this list, or -1 if this list does not contain the element.
        q.lastIndexOf(2);

        // Retrieves, but does not remove, the first element of
        // this list, or returns null if this list is empty.
        q.peekFirst();

        // Retrieves, but does not remove, the last element of
        // this list, or returns null if this list is empty.
        q.peekLast();

        // Pops an element from the stack represented by this list.
        // In other words, removes and returns the first element of this list.
        // This method is equivalent to removeFirst().
        q.pop();

        // Pushes an element onto the stack represented by this list.
        // In other words, inserts the element at the front of this list.
        //This method is equivalent to addFirst().
        q.push(2);

        // Removes the element at the specified position in this list. Shifts any
        // subsequent elements to the left (subtracts one from their indices).
        // Returns the element that was removed from the list.
        q.remove(1);

        // Retrieves and removes the head (first element) of this list.
        q.remove();

        // Removes and returns the first element from this list.
        q.removeFirst();

        // Removes and returns the last element from this list.
        q.removeLast();

        // Replaces the element at the specified position in this list with the specified element.
        q.set(2,3);

        q.removeFirstOccurrence(1);
        q.removeLastOccurrence(1);
        q.clear();
        q.contains(1);
        q.size();

        // Retrieves and removes the head (first element) of this list.
        q.poll();

        // You can use the enhanced for loop to iterate over its elements.
        for (int val: q)
            System.out.println(val);
    }
}
