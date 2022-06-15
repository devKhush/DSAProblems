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
        HashMap<Node, Node> originalToNewListMap = new HashMap<>();
        
        Node dummy = new Node(-1);
        Node ptr1 = head, ptr2 = dummy;
        
        while (ptr1 != null){
            ptr2.next = new Node(ptr1.val);
            ptr2 = ptr2.next;

            originalToNewListMap.put(ptr1, ptr2);            
            ptr1 = ptr1.next;
        }
        
        ptr1 = head;
        ptr2 = dummy.next;
        
        while (ptr2 != null){
            if (ptr1.random == null)
                ptr2.random = null;
            else
                ptr2.random = originalToNewListMap.get(ptr1.random);
            
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return dummy.next;
    }
}