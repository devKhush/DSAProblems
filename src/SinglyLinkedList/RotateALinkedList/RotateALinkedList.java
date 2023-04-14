package SinglyLinkedList.RotateALinkedList;

// https://youtu.be/9VPm6nEbVPA
// https://takeuforward.org/data-structure/rotate-a-linked-list/

public class RotateALinkedList {
    /*
     ****************************** Solution *****************************************
     * Main idea is to find the length of linked-list & iterate till "length - k"
     * After this we separate two parts head & node after which rotation is to be done
     *
     * O(length of the list) for calculating the length of the list.
     * O(length of the list – (length of list%k)) for breaking link.
     * Let, length of list = n
     * TC -> O(n) + O(n – (k % n)) = O(n)
     * SC -> O(1)
     */
    public ListNode rotateRight_Approach2(ListNode head, int k) {
        if (head == null)
            return null;

        int length = 1;
        ListNode lastNode = head;
        while (lastNode.next != null){
            length++;
            lastNode = lastNode.next;
        }

        k = k % length;
        ListNode ptr = head;
        int cnt = length - k;
        while (cnt > 1){
            ptr = ptr.next;
            cnt--;
        }
        lastNode.next = head;
        ListNode newHead = ptr.next;
        ptr.next = null;
        return newHead;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
