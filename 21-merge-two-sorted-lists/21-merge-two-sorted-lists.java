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
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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