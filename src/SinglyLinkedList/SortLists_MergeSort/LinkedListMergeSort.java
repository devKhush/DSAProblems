package SinglyLinkedList.SortLists_MergeSort;

public class LinkedListMergeSort {
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int data) {
            this.val = data;
        }
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    // ********************************* Merge Sort ****************************************
    public ListNode mergeSort(ListNode head){
        if (head == null || head.next == null)
            return head;

        // Now we find middle of linked-list using Hare & Tortoise method
        ListNode fast = head, slow = head;

        while (fast.next != null  &&  fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // make the two half's of linked-lists completely separate
        ListNode midHalf = slow.next;
        slow.next = null;

        // Recall array merge sort
        ListNode firstSortedPart = mergeSort(head);
        ListNode secondSortedPart = mergeSort(midHalf);
        return merge(firstSortedPart, secondSortedPart);
    }


    // **************************** Merging Two Sorting Lists *************************************
    // Same as Merging two sorted linked-lists
    // Check out folder : MergeTwoSortedLists
    public ListNode merge(ListNode head1, ListNode head2){
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        ListNode l1 = head1, l2 = head2;
        if (l1.val > l2.val){
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode head = l1;

        while (l1 != null && l2 != null){
            ListNode prevSmaller = null;

            while (l1 != null && l1.val <= l2.val){
                prevSmaller = l1;
                l1 = l1.next;
            }

            prevSmaller.next = l2;

            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        return head;
    }

}

