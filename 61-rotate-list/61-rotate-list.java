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
    private int getLength(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }
    
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return head;
        
        int length = getLength(head);
        k = k % length;
        
        if (k == 0)
            return head;
        
        int distanceToTravel = length - k;
        ListNode ptr = head;
        
        for (int i = 1; i < distanceToTravel; i++)
            ptr = ptr.next;
        
        ListNode newHead = ptr.next;
        ptr.next = null;
        
        ListNode lastNode = newHead;
        while (lastNode.next != null)
            lastNode = lastNode.next;
        
        lastNode.next = head;
        return newHead;
    }
}