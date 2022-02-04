package SinglyLinkedList.DeleteWithoutHeadPointer;

class Node {
    int data ;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}

public class DeleteWithoutHeadPointer {
    void deleteNode(Node del){
        del.data = del.next.data;
        del.next = del.next.next;
    }

    public static void main(String[] args) {
        Node head = new Node(11);
        head.next = new Node(22);
        head.next.next = new Node(33);
        head.next.next.next = new Node(44);
        head.next.next.next.next = new Node(55);
        head.next.next.next.next.next = new Node(66);

        new DeleteWithoutHeadPointer().deleteNode(head.next.next.next);
        Node ptr = head;
        while (ptr !=null){
            System.out.print(ptr.data+" ");
            ptr = ptr.next;
        }

    }
}
