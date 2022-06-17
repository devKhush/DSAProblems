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
    public ListNode mergeNodes(ListNode head) {
        if (head == null) return null;
        
        ListNode ptr = head.next;
        ListNode previousZeroNode = head, previousOfCurrentZeroNode = null;
        
        int sumBetweenZeros = 0;
    
        while (ptr != null){
            if (ptr.val != 0){
                sumBetweenZeros += ptr.val;
                previousOfCurrentZeroNode = ptr;
            }
            
            else{
                previousZeroNode.val = sumBetweenZeros;
                
                
                if (ptr.next == null)
                    previousZeroNode.next = null;
                else
                    previousZeroNode.next = ptr;
                
                previousZeroNode = ptr;
                
                previousOfCurrentZeroNode.next = null;
                
                sumBetweenZeros = 0;
            }
            ptr = ptr.next;
        }        
        return head;
    }
}