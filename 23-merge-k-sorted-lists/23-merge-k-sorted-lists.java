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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new NodeComparator());

        for (ListNode head : lists)
            if (head != null)
                minHeap.add(head);

        ListNode dummy = new ListNode(0), ptr = dummy;
        
        while (!minHeap.isEmpty()){
            ListNode minValueNode = minHeap.remove();
            
            ptr.next = minValueNode;
            ptr = ptr.next;
            
            if (minValueNode.next != null){
                minHeap.add(minValueNode.next);
                minValueNode.next = null;
            }
        }
        return dummy.next;
    }

    private class NodeComparator implements Comparator<ListNode>{
        @Override
        public int compare(ListNode a, ListNode b){
            return a.val - b.val;
        }
    }
}