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