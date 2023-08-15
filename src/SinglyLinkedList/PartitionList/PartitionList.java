package SinglyLinkedList.PartitionList;
import java.util.*;

// https://leetcode.com/problems/partition-list/description/

public class PartitionList {
    /******************************** Two Pointer Efficient ****************************************
     * Time Complexity: -> O(n)
     * Space Complexity: O(1)
     */
    public ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(), after = new ListNode();
        ListNode beforePtr = before, afterPtr = after;
        ListNode p = head;
        while (p != null){
            if (p.val < x){
                beforePtr.next = p;
                beforePtr = beforePtr.next;
            }
            else{
                afterPtr.next = p;
                afterPtr = afterPtr.next;
            }
            p = p.next;
        }
        beforePtr.next = after.next;
        after.next = null;
        afterPtr.next = null;
        return before.next;
    }


    /******************************** Brute Force ****************************************
     * Intuition: Separate two lists
     * Time Complexity: -> O(n)
     * Space Complexity: O(1)
     */
    public ListNode partition_brute(ListNode head, int x) {
        Queue<ListNode> lesser = new LinkedList<>();
        Queue<ListNode> larger = new LinkedList<>();
        ListNode p = head;
        while (p != null){
            if (p.val < x)
                lesser.add(p);
            else
                larger.add(p);
            p = p.next;
        }
        ListNode dummyHead = new ListNode();
        ListNode ptr = dummyHead;
        while (!lesser.isEmpty()){
            ptr.next = lesser.remove();
            ptr = ptr.next;
        }
        while (!larger.isEmpty()){
            ptr.next = larger.remove();
            ptr = ptr.next;
        }
        ptr.next = null;
        return dummyHead.next;
    }


    static class ListNode{
        int val;
        ListNode next;
    }
}
