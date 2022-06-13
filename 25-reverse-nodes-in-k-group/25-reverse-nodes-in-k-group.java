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
    private ListNode reverse(ListNode head){
        if (head == null || head.next == null)
           return head;
        
        ListNode nextToHead = head.next;
        head.next = null;
        
        ListNode reversedHead = reverse(nextToHead);
        
        nextToHead.next = head;
        return reversedHead;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
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
            
            if (ptr != null  || count == k)
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



