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
    
    public ListNode addTwoNumbers(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = head1, ptr2 = head2, ptr = dummy;
        int carry = 0;

        while (ptr1 != null || ptr2 != null){
            int sum = 0;
            sum += carry;

            if (ptr1 != null){
                sum += ptr1.val;
                ptr1 = ptr1.next;
            }
            if (ptr2 != null){
                sum += ptr2.val;
                ptr2 = ptr2.next;
            }

            ptr.next = new ListNode(sum % 10);
            ptr = ptr.next;

            carry = sum / 10;
        }
        if (carry == 1)
            ptr.next = new ListNode(carry);
        
        return dummy.next;    
    }
    
    
    public ListNode addTwoNumbers_V1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = l1, ptr2 = l2, ptr = dummy;
        
        int carry = 0;
        
        while (ptr1 != null && ptr2 != null){
            int sum = ptr1.val + ptr2.val + carry;
            carry = sum/10;
            
            ptr.next = new ListNode(sum % 10);
            
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            ptr = ptr.next;
        }
        
        while (ptr1 != null){
            int sum = ptr1.val + carry;
            carry = sum/10;
            
            ptr.next = new ListNode(sum % 10);
            
            ptr = ptr.next;
            ptr1 = ptr1.next;
        }
        
        while (ptr2 != null){
            int sum = ptr2.val + carry;
            carry = sum/10;
            
            ptr.next = new ListNode(sum % 10);
            
            ptr = ptr.next;
            ptr2 = ptr2.next;
        }
        
        if (carry == 1)
            ptr.next = new ListNode(carry);
        
        return dummy.next;
    }
}







