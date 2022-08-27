package SinglyLinkedList.AddTwoNumbers_II;
import java.util.Stack;

public class AddTwoNumbers_II {
    /************************************ Solution 1 ***********************************************
     * Using Stacks

     * 'm' & 'n' are length of two linked-lists
     * Time Complexity: O(max(m, n)) + O(max(m, n))
        * O(max(m, n)) to push all the nodes in both the Linked-list into two different Stacks
        * Another O(max(m, n)) to find the sum of two linked-list.
     * Space Complexity: O(m) + O(n) + O(max(m, n))
        * O(m) & O(n) for two stacks to hold the two linked-list in reverse order
     `  * O(max(m, n)) for stack to hold the Result of addition of Linked-list
     */
    public ListNode addTwoNumbers_Stack(ListNode head1, ListNode head2) {
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


    /************************************* Solution 2 *******************************************
     * By reversing two linked-lists

     * Time Complexity: O(m) + O(n) + O(max(m, n))
        * O(m) & O(n) time for reversing two linked-lists
        * O(max(m, n)) to find the sum of two linked-list.
     * Space Complexity: O(1)
        * No Space used
     */
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

        // Reversing the Result Linked-list (obtained by sum of two lists), while calculating the sum
        ListNode head = null;
        int carry = 0;

        while (h1 != null || h2 != null || carry != 0){
            int sum = 0;
            sum += h1 != null ? h1.val : 0;
            sum += h2 != null ? h2.val : 0;
            sum += carry;

            // Calculate the sum of two nodes, as well as Reverse the Resultant list meanwhile
            ListNode node =  new ListNode(sum % 10);
            node.next = head;
            head = node;

            carry = sum / 10;

            h1 = h1 != null ? h1.next : null;
            h2 = h2 != null ? h2.next : null;
        }
        return head;
    }


    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
