package SinglyLinkedList.RotateALinkedList;

// https://practice.geeksforgeeks.org/problems/rotate-a-linked-list/1/?company

class Node{
    int data;
    Node next;
    Node(int d){
        data=d;
        next=null;
    }
}


public class RotateALinkedList {
    public Node rotate(Node head, int k) {
        Node ptr = head;
        int count = 1;
        while (count<k){            // count till k
            ptr = ptr.next;
            count++;
        }
        if (ptr.next == null)       // when K == no. of nodes, list remains as it is
            return head;

        Node second = ptr.next;     // store other half
        ptr.next = null;
        ptr = second;
        while (ptr.next!=null){
            ptr = ptr.next;
        }
        ptr.next = head;
        return second;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        Node ptr = new RotateALinkedList().rotate(head,6);
        while (ptr !=null){
            System.out.print(ptr.data+" ");
            ptr = ptr.next;
        }
    }
}
