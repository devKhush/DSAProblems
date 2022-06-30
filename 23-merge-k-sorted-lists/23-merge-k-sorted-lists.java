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
        return divideAndMerge(lists, 0, lists.length - 1);
    }
    
    private ListNode divideAndMerge(ListNode[] lists, int low, int high){
        if (low > high)
            return null;
        if (low == high)
            return lists[low];
        
        int mid = (low + high) /2;
        
        ListNode leftHalfMergedList = divideAndMerge(lists, low, mid);
        ListNode rightHalfMergedList = divideAndMerge(lists, mid + 1, high);
        return mergeTwoLists(leftHalfMergedList, rightHalfMergedList);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0), ptr = dummy;
        
        while (l1 != null || l2 != null){
            int l1_val = l1 != null ? l1.val : Integer.MAX_VALUE;
            int l2_val = l2 != null ? l2.val : Integer.MAX_VALUE;
            
            ptr.next = l1_val <= l2_val ? l1 : l2;
            ptr = ptr.next;
            
            if (l1_val <= l2_val)
                l1 = l1 != null ? l1.next : null;
            else
                l2 = l2 != null ? l2.next : null;
            
            // ptr.next = null;
        }
        return dummy.next;
    }
        
    
    // MIN HEAP *******************************************************************************
    public ListNode mergeKLists_MinHeap(ListNode[] lists) {
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