package DoublyLinkedList.CopyListwithRandomPointer;
import java.util.HashMap;

// https://youtu.be/VNf6VynfpdM
// https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/

public class CopyListWithRandomPointer {
     /*
     ************************************* Approach 1: Hashing ******************************************
     * Iterate through the entire list.
     * For each node, create a deep copy of each node and hash it with the original node. Store it in the hashmap.
     * Now, again iterate through the given list.
       For each node, link the next & random pointer of deep copied node present as the hash value
       of the original node as per original node.
     * The head of the deep copy list will be the head of hashed value of original node. Return it
      * TC -> O(2n) = O(n)      Two iterations are being done of both original list & copied list
      * SC -> O(n)              for Hashmap, storing all original_Nodes -> copied_Nodes
     */
    public Node copyRandomList_Hashing(Node head) {
        HashMap<Node, Node> originalToNew = new HashMap<>();

        // first iteration for inserting deep nodes of every node in the hashmap.
        Node ptr = head;
        while (ptr != null){
            originalToNew.put(ptr, new Node(ptr.val));
            ptr = ptr.next;
        }

        //second iteration for linking next and random pointer of each copied node
        ptr = head;
        while (ptr != null){
            originalToNew.get(ptr).next = originalToNew.get(ptr.next);
            originalToNew.get(ptr).random = originalToNew.get(ptr.random);
            ptr = ptr.next;
        }
        return originalToNew.get(head);
    }


    /*
    ***************************** Approach 2:  Efficient Solution ************************************
    * TC -> O(n) + O(n) + O(n) = O(3n) = O(n)
    * SC -> O(1)
    * https://youtu.be/VNf6VynfpdM      Video Link
    * https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/
     */

    public Node copyRandomList_Efficient(Node head) {
        if (head == null)
            return null;

        //1. create nodes in one link, eg.original: A-B-C, add copy A-A'-B-B'-C-C'
        Node ptr = head;
        while (ptr != null){
            Node right = ptr.next;
            ptr.next = new Node(ptr.val);
            ptr.next.next = right;
            ptr = ptr.next.next;
        }

        //2. add random for each node
        ptr = head;
        while (ptr != null){
            ptr.next.random = ptr.random != null ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        //3.unlink & arrange all original nodes & copied nodes and relink as required again
        ptr = head;
        Node newHead = ptr.next;
        while (ptr != null){
            Node temp = ptr.next;
            ptr.next = ptr.next.next;

            if (ptr.next != null)
                temp.next = ptr.next.next;
            ptr = ptr.next;
        }
        return newHead;
    }


    static class Node {
        int val;
        Node next, random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
