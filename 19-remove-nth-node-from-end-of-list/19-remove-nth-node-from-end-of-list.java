/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(-1);
        start.next = head;
        ListNode fast = start, slow = start;
        
        for (int i = 1; i <= n; i++)
            fast = fast.next;
        
        if (fast.next == null)
            return head.next;
        
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        ListNode nodeToDelete = slow.next;
        slow.next = slow.next.next;
        nodeToDelete.next = null;
        
        return head;
    }
    
    
    public ListNode removeNthFromEnd_Counting(ListNode head, int n) {
        if (head.next == null && n == 1)
            return head.next;

        ListNode ptr = head;
        int numberOfNodes = 0;
        while (ptr != null) {
            numberOfNodes++;
            ptr = ptr.next;
        }
        int indexToDelete = numberOfNodes - n;
        if (indexToDelete == 0)
            return head.next;

        int nodeCount = 1;
        ptr = head;
        while (nodeCount < indexToDelete) {
            nodeCount++;
            ptr = ptr.next;
        }
        ListNode toDelete = ptr.next;
        ptr.next = toDelete.next;
        toDelete.next = null;
        return head;
    }
}