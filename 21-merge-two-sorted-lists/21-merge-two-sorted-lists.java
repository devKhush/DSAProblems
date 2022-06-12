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
    
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
          // Simple Base cases
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

        while (list1 != null  && list2 != null){
            ListNode prevSmallerNode = null;

            while (list1 != null  &&  list1.val <= list2.val){
                prevSmallerNode = list1;
                list1 = list1.next;
            }

            prevSmallerNode.next = list2;

            ListNode temp = list2;
            list2 = list1;
            list1 = temp;
        }

        return head;
    }
    
    // By creating a Dummy varaible Node ************************************************************************
    public ListNode mergeTwoLists_DummyNode(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode ptr = head;

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
            // ptr.next = null;
        }

        if (list1 != null)
            ptr.next = list1;

        if (list2 != null)
            ptr.next = list2;
        return head.next;
    }
}