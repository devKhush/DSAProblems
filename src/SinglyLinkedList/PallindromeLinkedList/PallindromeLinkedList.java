package SinglyLinkedList.PallindromeLinkedList;

import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class PallindromeLinkedList {

    public ListNode reverseLL(ListNode head) {
        ListNode curr = head, prev = null, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode halfReversed = reverseLL(slow.next);
        ListNode ptr = head;
        while (halfReversed != null) {
            if (ptr.val != halfReversed.val)
                return false;
            halfReversed = halfReversed.next;
            ptr = ptr.next;
        }
        return true;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode ptr = head;
        for (int i = 0; i < n; i++) {
            ptr.next = new ListNode(sc.nextInt());
            ptr = ptr.next;
        }
        System.out.println(new PallindromeLinkedList().isPalindrome(head.next));
        sc.close();

    }
}

