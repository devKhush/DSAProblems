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
        ListNode curr = head, prev = null, next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        ListNode h1 = reverse(head1);
        ListNode h2 = reverse(head2);

        ListNode head = null;
        int carry = 0;

        while (h1 != null || h2 != null || carry != 0){
            int sum = 0;
            sum += h1 != null ? h1.val : 0;
            sum += h2 != null ? h2.val : 0;
            sum += carry;

            ListNode node =  new ListNode(sum % 10);
            node.next = head;
            head = node;

            carry = sum / 10;

            h1 = h1 != null ? h1.next : null;
            h2 = h2 != null ? h2.next : null;
        }
        return head;
    }
    
    // Stack Solution **************************************************************************
    public ListNode addTwoNumbers_stacks(ListNode head1, ListNode head2) {
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