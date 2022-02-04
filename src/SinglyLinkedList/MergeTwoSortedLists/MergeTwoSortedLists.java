package SinglyLinkedList.MergeTwoSortedLists;

// https://leetcode.com/problems/merge-two-sorted-lists/

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
}

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode ptr1 = list1, ptr2 = list2, ptr = head;

        while (list1!=null && list2!=null){
            if (list2.val > list1.val){
                ptr.next = list1;
                list1 = list1.next;
            }
            else {
                ptr.next = list2;
                list2 = list2.next;

            }
            ptr = ptr.next;
            ptr.next = null;
        }

        while( list1!=null){
            ptr.next = list1;
            list1 = list1.next;
            ptr = ptr.next;
            ptr.next = null;
        }
        while( list2!=null){
            ptr.next = list2;
            list2 = list2.next;
            ptr = ptr.next;
            ptr.next = null;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(6);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode head = new MergeTwoSortedLists().mergeTwoLists(l1, l2);
        while (head!=null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
