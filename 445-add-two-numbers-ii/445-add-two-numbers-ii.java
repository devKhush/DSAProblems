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
          Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();

        ListNode l1 = head1, l2 = head2;

        while (l1 !=  null || l2 != null){
            if (l1 != null){
                s1.push(l1);
                l1 = l1.next;
            }
            if (l2 != null){
                s2.push(l2);
                l2 = l2.next;
            }
        }

        Stack<ListNode> result = new Stack<>();
        result.push(null);
        int carry = 0;

        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0){
            int sum = 0;
            sum += !s1.isEmpty() ? s1.pop().val : 0;
            sum += !s2.isEmpty() ? s2.pop().val : 0;
            sum += carry;

            ListNode node = new ListNode(sum % 10);
            node.next = result.peek();
            
            result.push(node);
            carry = sum / 10;
        }
        return result.peek();
    }
}