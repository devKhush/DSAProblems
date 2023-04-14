package SinglyLinkedList.ReverseNodesinGroupOfK;
import java.util.ArrayList;

// MUST WATCH VIDEO:
// https://youtu.be/Of0HPkk3JgI
// https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/

public class ReverseNodesInGroupOfK {
    /********************************** Brute Force **********************************
     *  Simple Approach is to traverse the linked-list, and simultaneously divide it
        in groups of K, then reverse the every divided kth group & store it into a ArrayList
     *  And later on, traverse the ArrayList to link all the reversed k group & return it

     *  Time complexity:  O(n) + O(k * n/k) + O(n)   =  O(3*n)  ~  O(n)
        *  We are travelling all the nodes using a pointer, O(n)
        *  Also, we are reversing all the kth divided groups (n/k in number), each such group have k nodes,
        *  O(k) for reversing every group
        *  Finally, we're linking all the 'k' reversed nodes this takes another O(n)
     *  TC -> O(n) + O(n/k) + O(n)
     *  SC -> O(n/k)  for storing all the n/k groups
     */
    public ListNode reverseKGroup_SimpleApproach(ListNode head, int k) {
        ArrayList<ListNode> kSizeList = new ArrayList<>();
        ListNode p = head;

        while (p != null){
            ListNode start = p;
            int count = 1;
            while (count < k  &&  p.next != null){
                p = p.next;
                count++;
            }
            ListNode temp = p.next;
            p.next = null;
            if (count == k)
                kSizeList.add(reverse(start));
            else
                kSizeList.add(start);
            p = temp;
        }

        for (int i = 0; i < kSizeList.size() - 1; i++){
            p = kSizeList.get(i);
            while (p.next != null)
                p = p.next;
            p.next = kSizeList.get(i + 1);
        }
        return kSizeList.get(0);
    }

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

    /******************************* Efficient Recursion Solution **********************************
     * Using recursion to reverse the nodes in group of 'k'
     * Intuition: In each recursion call, travel 'k' nodes and call function for remaining nodes

     * Time Complexity: O(n/k * (k + k))  = O(2*n)  ~  O(n)
        * Recursion will create n/k list groups each of size k.
        * We each group we find endpoint (k time) and reverse it (another k time)
     * Space Complexity:  O(n/k)
        * Recursion depth will be n/k
        * Coz list is divided into n/k group
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode ptr = head;
        for (int i = 1; i < k; i++){
            ptr = ptr.next;
            if (ptr == null)
                return head;
        }
        ListNode next = ptr.next;
        ptr.next = null;
        ListNode newHead = reverse(head);
        head.next = reverseKGroup(next, k);
        return newHead;
    }


    // ************************* Efficient Approach *************************************
    // Understand the process, Watch Video
    // We first find length of the linked-list. this takes O(n) time
    // We traverse only by length, and reduce the length of list to 'length -k', O(K) for this
    // In each group of size n/k, we are reversing all nodes in that group O(n/k) for this
    // TC -> O(n) + O(k) * O(n/k) = O(n) + O(k * n/k) = O(n) + O(n) = O(2n)
    // SC -> O(1)
    // See DRY RUN : https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/

    private int getLengthOfList(ListNode head){
        int length = 0;
        while (head != null){
            head = head.next;
            length++;
        }
        return length;
    }

    public ListNode reverseKGroup_(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        /* 'curr' always points at the 1st node of each unreversed group of size 'k'.
        * At the beginning, 'next' always points at the 2nd node of each unreversed group
        * of size 'k'. And we move it to next node after reversing it with previous node.
        * 'prev' always points to the last node of previous reversed group of size 'k' (in case of
        *  first 'kth' group it points to dummy node) */
        // Main Intuition is that we need to keep the 'prev' and 'curr' at a fixed position for the
        // current kth group. So, that current previous group ending point and current group ending point
        // is maintained. Only 'next' pointer keeps on moving for the current group of size n/k.
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
        // Dummy.next contains the new head of reversed list
        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
