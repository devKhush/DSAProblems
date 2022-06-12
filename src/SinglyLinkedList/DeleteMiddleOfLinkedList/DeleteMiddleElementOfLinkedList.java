package SinglyLinkedList.DeleteMiddleOfLinkedList;

import java.util.Scanner;

// https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/

public class DeleteMiddleElementOfLinkedList {

    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null)
            return null;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode midNode = fast.next == null ? slow : slow.next;

        ListNode ptr = head;
        while (ptr.next != null && ptr.next != midNode)
            ptr = ptr.next;

        ptr.next = midNode.next;
        midNode.next = null;
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode head = new ListNode(1);

        int n = sc.nextInt();
        ListNode ptr = head;
        for (int i = 0; i < n; i++) {
            ptr.next = new ListNode(sc.nextInt());
            ptr = ptr.next;
        }

        head = new DeleteMiddleElementOfLinkedList().deleteMiddle(head.next);
        ptr = head;
        while (ptr != null) {
            System.out.print(ptr.val + " ");
            ptr = ptr.next;
        }
        System.out.println();
        sc.close();
    }
}

