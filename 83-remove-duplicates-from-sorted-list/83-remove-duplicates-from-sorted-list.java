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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        ListNode ptr = head, prev = null;
        
        while (ptr != null){
            if (prev != null){
                if (prev.val == ptr.val){
                    prev.next = ptr.next;
                    ptr.next = null;
                    ptr = prev;
                }
                else
                    prev = ptr;
            }
            else
                prev = ptr;
            ptr = ptr.next;
        }
        
        return head;
    }
}