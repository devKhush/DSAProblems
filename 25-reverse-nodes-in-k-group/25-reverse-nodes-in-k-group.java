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
    
    private int getLengthOfList(ListNode head){
        int length = 0;
        while (head != null){
            head = head.next;
            length++;
        }
        return length;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy, curr, next;
        
        int length = getLengthOfList(head);
        
        while (length >= k){
            curr = prev.next;
            next = curr.next;
            
            for (int i = 1; i < k; i++){
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }
            prev = curr;
            
            length -= k;
        }
        
        return dummy.next;
        
    }
    
    
    // *********** Approach 1 Dividing list into k groups **********************
    private ListNode reverse(ListNode head){
        ListNode curr = head, prev = null, next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    
    public ListNode reverseKGroup_SimpleApproach(ListNode head, int k) {
        ArrayList<ListNode> kthDividedNodes = new ArrayList<>();
        
        ListNode ptr = head, prevNode = null;
        
        while (ptr != null){
            ListNode kthDividedHead = ptr;
            int count = 0;
            
            while (count < k  &&  ptr != null){
                prevNode = ptr;
                ptr = ptr.next;
                count++;
            }
            prevNode.next = null;
            
            if (count == k)
                kthDividedNodes.add(reverse(kthDividedHead));
            else
                kthDividedNodes.add(kthDividedHead);
        }
        
        for (int i = 0; i < kthDividedNodes.size() - 1; i++){
            ptr = kthDividedNodes.get(i);
            
            while (ptr.next != null)
                ptr = ptr.next;
            
            ptr.next = kthDividedNodes.get(i + 1);
        }
        
        ListNode newHead = kthDividedNodes.get(0);
        return newHead;
    }
}



