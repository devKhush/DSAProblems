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
    
    public ListNode reverseList(ListNode oldHead) {
        if (oldHead == null || oldHead.next == null)
            return oldHead;
        
        ListNode nextToHead = oldHead.next;
        oldHead.next = null;
        
        ListNode newHead = reverseList(nextToHead);
        
        nextToHead.next = oldHead;
        return newHead;
    }
    
    
    public ListNode reverseList_Iterative(ListNode head) {
        ListNode curr = head, prev = null, next;
        
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
}