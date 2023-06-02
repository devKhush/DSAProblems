package SinglyLinkedList.FlattenALinkedList;
import java.util.ArrayList;

// https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1

public class FlattenALinkedList {
    /************************************ Solution 1 *********************************************
     * Intuition: Merge all the sorted lists one by one
     * TC -> O(m * n * n)
        * where n is the size of the list (linked via next pointer)
        * m is the average size of each list (linked via bottom pointer)
        * Time to merge first two list:      O(m + m)
        * Time to merge first three list:    O(2m + m)
        * Time to merge first four list:     O(3m + m)
        * Time to merge first all n list:    O((n-1)m + m)
        * Total time =  O(m + m  +  2m + m  + 3m + m   + .... +  (n-1)m + m)
                     =  O(nm + m + 2m + 3m + ... + (n-1)m)   =   O(m * (n + 1 + 2 + 3 + ... + (n-1))
                     =  O(m * n * (n+1)/2)  =  O(m * n * n)
        * SPace Complexity: O(1)
     *
     */
    public Node flatten(Node root) {
        Node head = root;
        Node ptr = root.next;

        while (ptr != null){
            head = mergeSortedLists(head, ptr);
            ptr = ptr.next;
        }
        return head;
    }

    private Node mergeSortedLists(Node h1, Node h2){
        Node head = new Node(-1);
        Node ptr = head;

        while (h1 != null || h2 != null){
            int val1 = h1 != null ? h1.data : Integer.MAX_VALUE;
            int val2 = h2 != null ? h2.data : Integer.MAX_VALUE;
            if (val1 <= val2){
                ptr.bottom = h1;
                h1 = h1.bottom;
            }
            else{
                ptr.bottom = h2;
                h2 = h2.bottom;
            }
            ptr = ptr.bottom;
        }
        return head.bottom;
    }


    /*************************************** Solution 2 *******************************************
     * Intuition: Same as before but with Divide and Conquer
     * TC -> O(m * m * log(n))
     * SC -> O(n)
        * To store the list
        * Another O(n) for Recursion stack space
     */
    public Node flatten_DnC(Node root) {
        ArrayList<Node> lst = new ArrayList<>();
        Node ptr = root;

        while (ptr != null){
            lst.add(ptr);
            ptr = ptr.next;
        }
        return merge_DnC(0, lst.size() - 1, lst);
    }

    private Node merge_DnC(int low, int high, ArrayList<Node> arr){
        if (low == high)
            return arr.get(low);

        int mid = low + (high - low)/2;
        Node p1 = merge_DnC(low, mid, arr);
        Node p2 = merge_DnC(mid + 1, high, arr);
        return mergeSortedLists(p1, p2);
    }
}


class Node {
    int data;
    Node next, bottom;
    public Node(int d) {
        data = d;
    }
}