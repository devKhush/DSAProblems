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
   
     public ListNode merge(ListNode list1, ListNode list2){
        ListNode sorted = new ListNode(-1);
        ListNode sortedPtr = sorted;

        while (list1!=null && list2!=null){
            if (list1.val < list2.val){
                sortedPtr.next = list1;
                list1 = list1.next;
            }
            else {
                sortedPtr.next = list2;
                list2 = list2.next;
            }
            sortedPtr = sortedPtr.next;
        }

        if (list1 != null)
            sortedPtr.next = list1;
        if (list2 != null)
            sortedPtr.next = list2;
         
        return sorted.next;
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