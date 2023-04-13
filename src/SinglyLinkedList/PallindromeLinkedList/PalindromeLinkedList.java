package SinglyLinkedList.PallindromeLinkedList;

// https://youtu.be/-DtNInqFUXs
// https://takeuforward.org/data-structure/check-if-given-linked-list-is-plaindrome/

import java.util.Stack;

public class PalindromeLinkedList {
    // **************************** Reversing half of list ********************************
    // Time complexity:
    // O(n/2) for fast & slow traversal
    // O(n/2) for reversal of half list
    // O(n/2) for checking palindrome
    // TC -> 3*O(n/2)  ~ O(n) + O(n/2)
    // SC -> O(1)
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

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode half = fast == null ? slow : slow.next;
        ListNode revPtr = reverse(half);
        ListNode ptr = head;

        while (revPtr != null) {
            if (revPtr.val != ptr.val)
                return false;
            ptr = ptr.next;
            revPtr = revPtr.next;
        }
        return true;
    }

    /*********************************** Efficient Solution using stack *****************************
     * TC -> O(n/2)
        * Single pass algorithm
     * SC -> O(n)
     */
    public boolean isPalindrome_Stack(ListNode head) {
        ListNode fast = head, slow = head;
        Stack<ListNode> stack = new Stack<>();

        while (fast != null && fast.next != null){
            stack.push(slow);
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode a = fast == null ? slow : slow.next;
        while (a != null  &&  !stack.isEmpty()){
            if (a.val != stack.peek().val)
                return false;
            a = a.next;
            stack.pop();
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
