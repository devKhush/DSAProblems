package SinglyLinkedList.MergeKSortedLists;
import java.util.Comparator;
import java.util.PriorityQueue;

// MUST-WATCH FOR INTUITION:    🔥🔥🔥🔥🔥
// https://youtu.be/ptYUCjfNhJY
// https://youtu.be/kpCesr9VXDA
// https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/
// https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
// https://leetcode.com/problems/merge-k-sorted-lists/solution/
// PRE-REQUISITE: "Merge K Sorted Arrays" using Min-Heap (under Binary Heaps)

/*  *********************************** INTUITION **************************************
*   It it the generalization of Question "MERGE TWO SORTED Lists"
*   In that question we used two pointers for two list 'ptr1' & 'ptr2' pointing on list1 & list2
    respectively. And we were figuring out the minimum of both the node in the lists pointed by
    these pointers.
*   In this question, we can't maintain 'k' numbers of pointers. So, we use a Min-Heap here to
    do this task, and we can find the minimum of these first 'k' values in O(1) time via MinHeap.
    We also store the indices in order to move next.
*   This is one of the great problem. FOR DETAILED INTUITION, WATCH VIDEO.
 */

public class MergeKSortedLists_MinHeap {
    /*
    ********************************** MinHeap Approach 1 **************************************
    * Arrange all Nodes in a MinHeap with sorted order (using a Comparator class)
    * MinHeap will be ordered by values of List Nodes (smaller values first)
    * Simultaneously break all links while adding into MinHeap.
    * Finally, remove Nodes from MinHeap one by one and form new links
    *
    * Time Complexity: O(N * log(N))        where 'N' is the total numbers of Node given
        This is due to Addition of elements & deletion from MinHeap
        Since, MinHeap is of 'N' size. Addition & Deletion from MinHeap will take log(N) time.
        Heap size of 'n*k' is reached at the end.
        This is the worst case Time Complexity
    * Space Complexity : O(N), The MinHeap is of size N.
     */
    public ListNode mergeKLists_Solution1(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new NodeComparator());

        for (ListNode heads : lists){
            ListNode previous = null;
            ListNode ptr = heads;

            while (ptr != null){
                previous = ptr;
                minHeap.add(ptr);
                ptr = ptr.next;
                previous.next = null;
            }
        }
        ListNode head = minHeap.poll(), ptr = head;

        while (!minHeap.isEmpty()){
            ptr.next = minHeap.remove();
            ptr = ptr.next;
        }
        return head;
    }


    /* **************************** MinHeap Approach 2: Efficient Solution **************************
        * Create a min-heap and insert the first element of all the ‘k’ linked lists.
        * As long as the min-heap is not empty, perform the following steps:
            * Remove the top element of the min-heap (which is the current minimum among all
              the elements in the min-heap) and add it to the result list.
            * If there exists an element (in the same linked list) next to the element popped
              out in previous step, insert it into the min-heap.
        * Return the head node address of the merged list.

        * Time Complexity:  O(N * log k)
            * where, ‘N’ is the total number of elements among all the linked lists and
              ‘k’ is the total number of lists
            * Insertion and deletion operation will be performed in min-heap for all N nodes.
            * Insertion and deletion in a min-heap require log k time.
        * Auxiliary Space: O(k).
            * The priority queue will have at most ‘k’ number of elements at any point of time,
            * Hence the additional space required for our algorithm is O(k).
     */
    public ListNode mergeKLists_Solution2(ListNode[] lists) {
        // Priority_queue 'queue' implemented as min heap with the help of 'compare' function
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new NodeComparator());

        // Push the head nodes of all the k lists in 'queue'
        for (ListNode head : lists)
            if (head != null)
                minHeap.add(head);

        ListNode dummy = new ListNode(0), ptr = dummy;

        // Loop till 'queue' is not empty
        while (!minHeap.isEmpty()){
            // Get the top minimum element of 'queue'
            ListNode minValueNode = minHeap.remove();

            // Add the top element of 'queue' to the resultant merged list
            ptr.next = minValueNode;
            ptr = ptr.next;

            // Check if there is a node next to the "current minimum valued" node 'minValueNode'
            // in the list. If yes, Push the next node of that node into the min-heap
            if (minValueNode.next != null){
                minHeap.add(minValueNode.next);
                minValueNode.next = null;
            }
        }
        return dummy.next;
    }


    // Node Comparator to be used in MinHeap
    private class NodeComparator implements Comparator<ListNode>{
        @Override
        public int compare(ListNode a, ListNode b){
            return a.val - b.val;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
