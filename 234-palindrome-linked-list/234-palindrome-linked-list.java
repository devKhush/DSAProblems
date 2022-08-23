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
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head, next;
        
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode half = slow.next;
        slow.next = null;
        
        ListNode reversedPtr = reverse(half);
        ListNode ptr = head;
        
        while (ptr != null && reversedPtr != null){
            if (ptr.val != reversedPtr.val)
                return false;
            
            ptr = ptr.next;
            reversedPtr = reversedPtr.next;
        }
        return true;
    }
}