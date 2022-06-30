package SinglyLinkedList.MergeKSortedLists;

/*
*************************** Approach 1: EXTREME Brute Force ***************************
*  Intuition & Algorithm
    * Traverse each list one by one in the given linked-list array to link all the linked-list
      in just one single list.
    * Point the "last node of each linked-list" to the "head of next linked-list"
    * Now, Sort that single linked-list using Merge-Sort Algorithm & return the head

* Complexity Analysis
    * Time complexity : O(N * log(N)) + O(N)    where N is the total number of nodes.
        Linking all the nodes costs O(N) time.
        A Merge Sort algorithm takes O(N * logN) time.
    * Space complexity : O(log(N))
      Recursion Stack space of Merge Sort is O(log(n)). (We don't a temporary array here)
*/

public class MergeKSortedLists_MergeSort {
    /*
    ****************************** Approach 2: Brute Force ****************************************
    * We will use Merge Operation in Merge Sort to merge two sorted list at a time.
    * Take two lists at a time, & merge them. and return the new head/

    * Time Complexity:  O(k * N)    where 'N' is the total number of nodes in the LinkedList array
      Since merging of two sorted list can be done in O(a + b) ~ O(n) time, assuming 'n'
      is the average no. of elements in each list.
      To merge 1st & 2nd sorted list we need:  O(n)  + O(n) = O(2n)  Time.
      To merge 2nd & 3rd sorted list we need:  O(2n) + O(n) = O(3n)  Time.
      To merge 3rd & 4th sorted list we need:  O(3n) + O(n) = O(4n)  Time.
      To merge 4th & 5th sorted list we need:  O(4n) + O(n) = O(5n)  Time.
      To merge (k-2)th & (k-1)th sorted list we need:  O((k-2)*n) + O(n) = O(n * (k-1))  Time.
      To merge (k-1)th & k-th sorted list we need:  O((k-1)*n) + O(n) = O(n * k)  Time.
      * Since we are doing it inside a 'for loop' of k iterations, we require O(k * n * k) Time Complexity

    * Space Complexity:  O(1)
        We aren't using any External space
     */
    public ListNode mergeKLists(ListNode[] kLists) {
        if (kLists.length == 0)
            return null;
        ListNode head = kLists[0];

        for (int i = 1; i < kLists.length; i++)
            head = mergeTwoSortedList(head, kLists[i]);

        return head;
    }


    private ListNode mergeTwoSortedList(ListNode head1, ListNode head2){
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        ListNode list1 = head1, list2 = head2;

        if (list1.val > list2.val){
            ListNode temp = list2;
            list2 = list1;
            list1 = temp;
        }
        ListNode head = list1;

        while (list1 != null  &&  list2 != null){
            ListNode previous = null;

            while (list1 != null && list1.val <= list2.val){
                previous = list1;
                list1 = list1.next;
            }
            previous.next = list2;
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
