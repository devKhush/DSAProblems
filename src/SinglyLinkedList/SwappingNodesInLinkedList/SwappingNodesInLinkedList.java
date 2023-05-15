package SinglyLinkedList.SwappingNodesInLinkedList;

// https://leetcode.com/problems/swapping-nodes-in-a-linked-list/description/
// Similar Question: Remove Nth Node From End of List

public class SwappingNodesInLinkedList {
    /*********************************** Efficient Solution *************************************
     * One pass Algorithm

     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null)
            return null;

        // Move pointer 1 to kth nodes
        ListNode p1 = head, p2 = head;
        int steps = 1;
        while (steps < k){
            p1 = p1.next;
            steps++;
        }

        // Move pointer 2 to (n-k)th node
        ListNode ptr = p1.next;
        while (ptr != null){
            ptr = ptr.next;
            p2 = p2.next;
        }

        // Swap the values
        int temp = p1.val;
        p1.val = p2.val;
        p2.val = temp;
        return head;
    }


    static class ListNode{
        int val;
        ListNode next;
    }
}
