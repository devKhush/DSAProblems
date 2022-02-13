package DoublyLinkedList.CopyListwithRandomPointer;

// https://leetcode.com/problems/copy-list-with-random-pointer/

import java.util.HashMap;

public class CopyListWithRandomPointer {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList_DP(Node head) {
        Node copy = new Node(-1);
        Node headPtr = head;
        Node copyPtr = copy;

        HashMap<Node, Node> originalToCopy = new HashMap<>();

        while (headPtr!=null){
            copyPtr.next = new Node(headPtr.val);
            copyPtr = copyPtr.next;
            originalToCopy.put(headPtr, copyPtr);
            headPtr = headPtr.next;
        }

        headPtr = head;
        copyPtr = copy.next;
        while (headPtr!=null){
            copyPtr.random = originalToCopy.get(headPtr.random);
            copyPtr = copyPtr.next;
            headPtr = headPtr.next;
        }
        return copy.next;
    }



    public Node copyRandomList(Node head) {
        if (head==null)
            return null;
        Node headPtr = head;
        Node tempPtr;
        Node copy, copyPtr;

        //1. create nodes in one link, eg.original: A-B-C, add copy A-A'-B-B'-C-C'
        while (headPtr!=null){
            tempPtr = headPtr.next;
            headPtr.next = new Node(headPtr.val);
            headPtr.next.next = tempPtr;
            headPtr = headPtr.next.next;
        }

        //2. add random for each node
        headPtr = head;
        while(headPtr!=null){
            headPtr.next.random = headPtr.random==null? null : headPtr.random.next;
            headPtr = headPtr.next.next;
        }

        //3.unlink all nodes and relink as required again
        headPtr = head;
        copy = head.next;
        copyPtr = copy;
        while (headPtr!=null){
            headPtr.next = headPtr.next.next;
            headPtr = headPtr.next;

            copyPtr.next = copyPtr.next!=null ? headPtr.next : null;
            copyPtr = copyPtr.next;
        }

        return copy;
    }

}