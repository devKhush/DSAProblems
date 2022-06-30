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
    public ListNode mergeKLists(ListNode[] kLists) {
        if (kLists.length == 0)
            return null;
        ListNode head = kLists[0];

        for (int i = 1; i < kLists.length; i++)
            head = mergeTwoSortedList(head, kLists[i]);

        return head;
    }

    private ListNode mergeTwoSortedList(ListNode head1, ListNode head2){
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        ListNode list1 = head1, list2 = head2;

        if (list1.val > list2.val){
            ListNode temp = list2;
            list2 = list1;
            list1 = temp;
        }
        ListNode head = list1;

        while (list1 != null  &&  list2 != null){
            ListNode previous = null;

            while (list1 != null && list1.val <= list2.val){
                previous = list1;
                list1 = list1.next;
            }
            previous.next = list2;
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }
        return head;
    }

}