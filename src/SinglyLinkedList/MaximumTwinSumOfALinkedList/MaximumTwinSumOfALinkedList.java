package SinglyLinkedList.MaximumTwinSumOfALinkedList;

// https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/

import java.util.Stack;

class MaximumTwinSumOfALinkedList {
    /************************************* Solution using Stack **********************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int pairSum_Stack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        ListNode fast = head, slow = head;
        while (fast != null){
            stack.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }
        int maxTwinSum = Integer.MIN_VALUE;
        while (slow != null){
            maxTwinSum = Math.max(maxTwinSum, slow.val + stack.pop().val);
            slow = slow.next;
        }
        return maxTwinSum;
    }

    /************************************** Solution using reversing list *******************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int pairSum(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode revHalf = reverse(slow);
        slow = head;
        int maxTwinSum = Integer.MIN_VALUE;
        while (revHalf != null){
            maxTwinSum = Math.max(maxTwinSum, slow.val + revHalf.val);
            slow = slow.next;
            revHalf = revHalf.next;
        }
        return maxTwinSum;
    }
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

    static class ListNode {
        int val;
        ListNode next;
    }
}
