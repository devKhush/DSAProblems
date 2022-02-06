package SinglyLinkedList.MiddleOfLinkedList;
import java.util.Scanner;

// https://leetcode.com/problems/middle-of-the-linked-list/

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

class MiddleOfLinkedList{
    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast.next==null ? slow : slow.next;
    }


    public ListNode middleNode_Alter(ListNode head){        // Based on counting nodes
        ListNode mid = head;
        int count = 1;

        while(head.next!=null){
            if (count%2 == 1)
                mid = mid.next;
            count++;
            head = head.next;
        }
        return mid;
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

        ListNode midHead = new MiddleOfLinkedList().middleNode(head.next);
        ptr = midHead;
        while (ptr != null) {
            System.out.print(ptr.val + " ");
            ptr = ptr.next;
        }
        System.out.println();
        sc.close();
    }
}