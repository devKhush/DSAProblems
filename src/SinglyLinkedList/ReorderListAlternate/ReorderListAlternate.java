package SinglyLinkedList.ReorderListAlternate;

import java.util.Scanner;

// https://leetcode.com/problems/reorder-list/

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

class ReorderListAlternate {
    public ListNode reverse(ListNode head) {
        ListNode curr = head, prev = null, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public void reorderList(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode part1 = head;
        ListNode part2 = slow.next;
        slow.next = null;
        ListNode part2Reversed = reverse(part2);

        ListNode newHead = new ListNode(-1);

        while (part1 != null && part2Reversed != null) {
            newHead.next = part1;
            newHead = newHead.next;
            part1 = part1.next;

            newHead.next = part2Reversed;
            newHead = newHead.next;
            part2Reversed = part2Reversed.next;

        }
        if (part1 != null)
            newHead.next = part1;
        // part2Reversed will always be null

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode head = new ListNode(-1);

        int n = sc.nextInt();
        ListNode ptr = head;
        for (int i = 0; i < n; i++) {
            ptr.next = new ListNode(sc.nextInt());
            ptr = ptr.next;
        }

        new ReorderListAlternate().reorderList(head.next);
        ptr = head.next;
        while (ptr != null) {
            System.out.print(ptr.val + " ");
            ptr = ptr.next;
        }
        System.out.println();
        sc.close();
    }
}

