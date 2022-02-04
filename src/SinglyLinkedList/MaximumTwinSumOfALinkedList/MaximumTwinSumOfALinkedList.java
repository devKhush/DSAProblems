package SinglyLinkedList.MaximumTwinSumOfALinkedList;

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

class MaximumTwinSumOfALinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode next, prev = null, curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }
        return prev;
    }

    public int pairSum(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode ptr = head, reversedHalf = reverseList(slow.next);
        int sum = 0;
        while (reversedHalf != null) {
            sum = Integer.max(sum, ptr.val + reversedHalf.val);
            ptr = ptr.next;
            reversedHalf = reversedHalf.next;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode head = new ListNode();

        int n = sc.nextInt();
        ListNode ptr = head;
        for (int i = 0; i < n; i++) {
            ptr.next = new ListNode(sc.nextInt());
            ptr = ptr.next;
        }
        System.out.println(new MaximumTwinSumOfALinkedList().pairSum(head.next));
    }
}
