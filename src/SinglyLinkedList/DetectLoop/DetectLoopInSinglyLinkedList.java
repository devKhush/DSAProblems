package SinglyLinkedList.DetectLoop;

// https://practice.geeksforgeeks.org/problems/detect-loop-in-linked-list/1

public class DetectLoopInSinglyLinkedList {
    static class Node{
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
        }
    }


    public static boolean detectLoop(Node head){
        Node slow = head, fast = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast==slow)
                return true;
        }
        return false;
    }

    public static boolean detectLoop_another(Node head){
        Node ptr = head;
        while (ptr!=null){
            if (ptr.data==Integer.MIN_VALUE)
                return true;
            else
                ptr.data = Integer.MIN_VALUE;
            ptr = ptr.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        //head.next.next.next.next.next = head.next.next;

        System.out.println(detectLoop_another(head));
    }


}
