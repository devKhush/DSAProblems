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
    private int getLength(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }
    
    public ListNode rotateRight(ListNode head, int k) {
       if (head == null)
            return null;

        ListNode lastNode = head;
        int length = 1;

        while (lastNode.next != null){
            lastNode = lastNode.next;
            length++;
        }

        // Since k >= length, so we rotate the list by "k % length". After this k becomes less than 'length'
        // if k==length, that means rotated list is same as original list
        // if k==0 OR k==length, after division modulo by length k reduces to k==0, which means list doesn't need to rotated
        k = k % length;
        
        if (k == 0) return head;

        int distanceToTravel = length - k, travelled = 1;
        ListNode ptr = head;

        while (travelled < distanceToTravel){
            ptr = ptr.next;
            travelled++;
        }
        
        lastNode.next = head;
        head = ptr.next;
        ptr.next = null;
        
        return head;
    }
}