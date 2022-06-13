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
    public ListNode reverse(ListNode head){
        ListNode prev = null, curr=head, next;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    public boolean isPalindrome(ListNode head) {
        ListNode fast=head, slow=head, mid, ptr1, ptr2;
        
        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        mid = slow.next;
        
        ptr1 = head;
        ptr2 = reverse(mid);
        
        while(ptr1!=null && ptr2!=null){
            if (ptr1.val!=ptr2.val)
                return false;
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return true;    
    }
}