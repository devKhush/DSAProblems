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
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new NodeComparator());

        for (ListNode heads : kLists){
            ListNode ptr = heads;
            ListNode prev = null;
            
            while (ptr != null){                
                prev = ptr;
                minHeap.add(ptr);
                ptr = ptr.next;
                prev.next = null;

            }
        }
        
        ListNode head = minHeap.poll();
        ListNode ptr = head;
        
        while (minHeap.size() != 0){
            ptr.next = minHeap.remove();
            ptr = ptr.next;
        }
        return head;
    }
    
    class NodeComparator implements Comparator<ListNode>{
        @Override 
        public int compare(ListNode a, ListNode b){
            return a.val - b.val;
        }
    }
}