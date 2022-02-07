package SinglyLinkedList.RemoveDuplicates;

class Node{
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
    }
}

public class RemoveDuplicatesInSortedList {

    Node removeDuplicates(Node head) {
        Node curr = head, prev = head, temp;
        while (curr.next!=null){
            curr = curr.next;
            if (curr.data==prev.data){
                temp = curr.next;
                prev.next = temp;
                curr.next = null;
                curr = prev;
            }
            else
                prev = prev.next;
        }
        return head;
    }


    public void display(Node head){
        while (head!=null){
            System.out.print(head.data+" ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(5);

        RemoveDuplicatesInSortedList solution = new RemoveDuplicatesInSortedList();
        solution.display(head);
        head = solution.removeDuplicates(head);
        solution.display(head);

    }
}
