package SinglyLinkedList.SwapNodesInPairs;

// https://leetcode.com/problems/swap-nodes-in-pairs/

public class SwapNodesInPairs {
    /******************************************** Iterative Solution *********************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode swapPairs_iterative(ListNode head) {
        if (head == null)
            return null;

        // We need a dummy node for this purpose
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode ptr = head;
        while (ptr != null && ptr.next != null){
            // Swap next two nodes
            ListNode temp = ptr.next.next;
            prev.next = ptr.next;
            prev.next.next = ptr;
            ptr.next = temp;

            // Move to next set of nodes
            ptr = ptr.next;
            prev = prev.next.next;
        }
        return dummy.next;
    }


    /************************************** Recursive Solution ***************************************
     * Time Complexity: O(n)        need to reverse whole list
     * Space Complexity: O(n)       due to recursion stack space
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode newHead = head.next;
        head.next = swapPairs(head.next.next);
        newHead.next = head;
        return newHead;
    }


    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
}
