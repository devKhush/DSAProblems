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
        ListNode fast = head, slow = head;

        while (fast.next != null  && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode reversedPtr = reverse(slow.next);
        ListNode ptr = head;

        while (reversedPtr != null){
            if (reversedPtr.val != ptr.val)
                return false;
            reversedPtr = reversedPtr.next;
            ptr = ptr.next;
        }
        return true;  
    }
}