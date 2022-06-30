package SinglyLinkedList.MergeKSortedLists;

// MUST-WATCH FOR INTUITION:    🔥🔥🔥🔥🔥
// https://youtu.be/ptYUCjfNhJY
// https://youtu.be/kpCesr9VXDA
// https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/
// https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
// https://leetcode.com/problems/merge-k-sorted-lists/solution/
// PRE-REQUISITE: "Merge K Sorted Arrays" using Min-Heap (under Binary Heaps)

/* ************************************* Intuition & Algorithm *************************************

* This approach walks alongside the one above but is improved a lot. We don't need to traverse
 most nodes many times repeatedly
* Pair up 'k' lists (into two linked-lists) and merge each pair.
* After the first pairing, 'k' lists are merged into 'k/2' lists, then k/4, k/8 and so on.
* Repeat this procedure until we get the final sorted linked list.
* Thus, we'll traverse almost N nodes per pairing and merging, and repeat this procedure
   about log2(k) times.
* ILLUSTRATION: https://leetcode.com/problems/merge-k-sorted-lists/Figures/23/23_divide_and_conquer_new.png
 */

public class MergeKSortedLists_DivideAndConquer {
    /*
    * Time complexity : O(N * log(k))     where k is the number of linked lists.
       * We can merge two sorted linked list in O(n) time, where n is the total number of nodes
         in two lists.
       * Sum up the merge process, and we can get  O(∑ N) from i = 1 to i = log2(k) =  O(N * log(k))
    * Space complexity : O(log2(k))
       * Recursion stack space of Divide & Conquer will be log2(k).
       * We can merge two sorted linked lists in O(1) space.
     */

    public ListNode mergeKLists(ListNode[] lists) {
        return divideAndMerge(lists, 0, lists.length - 1);
    }

    // Technique For Divide & Conquer
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

    // Simple Code for Merging two Linked-List
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

            ptr.next = null;
        }
        return dummy.next;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
