/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        
        Node originalPtr = head, copyPtr;

        //1. create nodes in one link, eg.original: A-B-C, add copy A-A'-B-B'-C-C'
        while (originalPtr != null){
            Node next = originalPtr.next;
            originalPtr.next = new Node(originalPtr.val);
            originalPtr.next.next = next;
            originalPtr = originalPtr.next.next;
        }

        //2. add random for each node
        originalPtr = head;
        while (originalPtr != null){
            copyPtr = originalPtr.next;
            copyPtr.random = originalPtr.random == null ? null : originalPtr.random.next;

            originalPtr = originalPtr.next.next;
        }


        originalPtr = head;
        Node copiedList = originalPtr.next;

        while (originalPtr != null){
            copyPtr = originalPtr.next;

            originalPtr.next = originalPtr.next.next;
            originalPtr = originalPtr.next;

            copyPtr.next = originalPtr == null ? null : originalPtr.next;
        }

        return copiedList;
    }
}