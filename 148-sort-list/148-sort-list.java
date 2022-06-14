/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
   
     public ListNode merge(ListNode head1, ListNode head2){
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        ListNode l1 = head1, l2 = head2;
        if (l1.val > l2.val){
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode head = l1;

        while (l1 != null && l2 != null){
            ListNode prevSmaller = null;

            while (l1 != null && l1.val <= l2.val){
                prevSmaller = l1;
                l1 = l1.next;
            }

            prevSmaller.next = l2;

            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        return head;
    }

    public ListNode mergeSort(ListNode head){
        if (head == null || head.next == null)
            return head;

        ListNode fast = head, slow = head;

        while (fast.next != null  &&  fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode midHalf = slow.next;
        slow.next = null;

        ListNode firstSortedPart = mergeSort(head);
        ListNode secondSortedPart = mergeSort(midHalf);

        return merge(firstSortedPart, secondSortedPart);
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

}