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
    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        Stack<ListNode> num1 = new Stack<>();
        Stack<ListNode> num2 = new Stack<>();
        
        ListNode l1 = head1, l2 = head2;
        while (l1 != null || l2 != null){
            if (l1 != null){
                num1.push(l1);
                l1 = l1.next;
            }
            if (l2 != null){
                num2.push(l2);
                l2 = l2.next;
            }
        }
        
        int carry = 0;
        Stack<ListNode> result = new Stack<>();
        
        while (!num1.isEmpty() || !num2.isEmpty() || carry != 0){
            int sum = 0;
            sum += !num1.isEmpty() ? num1.pop().val : 0;
            sum += !num2.isEmpty() ? num2.pop().val : 0;
            sum += carry;
            
            result.push(new ListNode(sum % 10));
            carry = sum / 10;
        }
        
        ListNode dummy = new ListNode(0), ptr = dummy;
        
        while (!result.isEmpty()){
            ptr.next = result.pop();
            ptr = ptr.next;
        }
        return dummy.next;
    }
}