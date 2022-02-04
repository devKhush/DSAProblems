package SinglyLinkedList.RemoveNthNodeFromEnd;
import java.util.Scanner;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }
}

class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null && n == 1)
            return null;
        // Count number of nodes
        ListNode ptr = head;
        int numberOfNodes = 0;
        while (ptr != null) {
            numberOfNodes++;
            ptr = ptr.next;
        }

        //index to delete from front
        int indexToDelete = numberOfNodes - n + 1;
        if (indexToDelete == 1)
            return head.next;
        int nodeCount = 1;
        ptr = head;
        while (nodeCount < indexToDelete - 1) {
            nodeCount++;
            ptr = ptr.next;
        }
        ListNode toDelete = ptr.next;
        ptr.next = toDelete.next;
        toDelete.next = null;
        return head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode head = new ListNode();

        int n = sc.nextInt();
        int j = sc.nextInt();

        ListNode ptr = head;
        for (int i = 0; i < n; i++) {
            ptr.next = new ListNode(sc.nextInt());
            ptr = ptr.next;
        }

        head = new RemoveNthNodeFromEnd().removeNthFromEnd(head.next, j);
        ptr = head;
        while (ptr != null) {
            System.out.print(ptr.val + " ");
            ptr = ptr.next;
        }
        System.out.println();
        sc.close();
    }
}
