package SinglyLinkedList.AddTwoNumbersLinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ptr1 = l1, ptr2 = l2, ptr;
        ListNode head = new ListNode(0);
        ptr = head;
        Integer sum = 0, carry = 0, i, j;

        while (ptr1 != null && ptr2 != null) {
            i = ptr1.val;
            j = ptr2.val;
            sum = i + j + carry;
            if (sum.toString().length() == 1) {
                ptr.next = new ListNode(sum);
                carry = 0;
            } else if (sum.toString().length() == 2) {
                carry = Integer.parseInt(((Character) sum.toString().charAt(0)).toString());
                ptr.next = new ListNode(Integer.parseInt(((Character) sum.toString().charAt(1)).toString()));
            }
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            ptr = ptr.next;
        }

        while (ptr1 != null) {
            i = ptr1.val;
            sum = i + carry;
            if (sum.toString().length() == 1) {
                ptr.next = new ListNode(sum);
                carry = 0;
            } else if (sum.toString().length() == 2) {
                carry = Integer.parseInt(((Character) sum.toString().charAt(0)).toString());
                ptr.next = new ListNode(Integer.parseInt(((Character) sum.toString().charAt(1)).toString()));
            }
            ptr1 = ptr1.next;
            ptr = ptr.next;
        }

        while (ptr2 != null) {
            j = ptr2.val;
            sum = j + carry;
            if (sum.toString().length() == 1) {
                ptr.next = new ListNode(sum);
                carry = 0;
            } else if (sum.toString().length() == 2) {
                carry = Integer.parseInt(((Character) sum.toString().charAt(0)).toString());
                ptr.next = new ListNode(Integer.parseInt(((Character) sum.toString().charAt(1)).toString()));
            }
            ptr2 = ptr2.next;
            ptr = ptr.next;
        }
        if (carry != 0) {
            ptr.next = new ListNode(carry);
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(9);
        // ListNode n1 = new ListNode(4);
        // ListNode n2 = new ListNode(3);
        // head1.next = n1;
        // n1.next = n2;
        ListNode ptr = head1;
        for (int i = 1; i <= 6; i++) {
            ptr.next = new ListNode(9);
            ptr = ptr.next;
        }

        ListNode head2 = new ListNode(9);
        // ListNode p1 = new ListNode(6);
        // ListNode p2 = new ListNode(4);
        // head2.next = p1;
        // p1.next = p2;
        ptr = head2;
        for (int i = 1; i <= 3; i++) {
            ptr.next = new ListNode(9);
            ptr = ptr.next;
        }

        ListNode head = new AddTwoNumbers().addTwoNumbers(head1, head2);
        ListNode ptr1 = head;
        while (ptr1 != null) {
            System.out.print(ptr1.val + " ");
            ptr1 = ptr1.next;
        }

    }

}
