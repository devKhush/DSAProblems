package DoublyLinkedList.FlatteningOfLinkedList;

// https://youtu.be/ysytSSXpAI0
// https://takeuforward.org/data-structure/flattening-a-linked-list/
// https://www.geeksforgeeks.org/flattening-a-linked-list/

public class FlatteningOfLinkedList_Best {

    // ***************************** Efficient Approach 1 ***********************************
    /*
    m -> total no. of bottom nodes (connected via bottom pointer)
    n -> total no. of next nodes (connected via next pointer)
    TC -> O(total nodes in list) = O(m + n)
    SC -> O(1)
    Algo:
        1)point l1 =head and l2=l1.next
        2)pass l1 and l2 as parameters to merge these two sublinked list into sorted list
        3) update l1= mergerTwoSortedList(l1,l2);
            update l2= l2.next;
        4) repeat 3rd step until l2!=null
     */
    public Node flatten_Recursive(Node head){
        Node l1 = head;
        Node l2 = head.next;

        while (l2 != null){
            l1 = mergeTwoSortedList(l1, l2);
            l2 = l2.next;
        }
        return l1;
    }


    // ****************************** Efficient Approach 2 **********************************
    /*
     Using Recursion
     Idea is to first merge two sorted lists from the extreme ends
     (as bottom linked-list of every node in the main linked-list are always sorted)

     m -> total no. of bottom nodes (connected via bottom pointer)
     n -> total no. of next nodes (connected via next pointer)
     TC -> O(m + n)
     SC -> O(n)       no. of recursion calls equal to no. of nodes connected via next pointers
     */

    public Node flatten(Node head){
        if (head == null || head.next == null)
            return head;

        // first flatten the 'next' pointer of current head
        head.next = flatten(head.next);

        // now merge sorted list head & head.next
        head = mergeTwoSortedList(head, head.next);

        //return the head
        return head;
    }


    // *********************** Merging two sorted lists ***********************************
    private Node mergeTwoSortedList(Node list1, Node list2){
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        Node l1 = list1, l2 = list2;
        if (l1.data > l2.data){
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }
        Node head = l1;

        while (l1 != null  && l2 != null){
            Node prev = null;

            while (l1 != null  && l1.data <= l2.data){
                prev = l1;
                l1 = l1.bottom;
            }

            prev.bottom = l2;
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }
        return head;
    }

    static class Node {
        int data;
        Node next;
        Node bottom;
        Node(int d) {
            data = d;
            next = null;
            bottom = null;
        }
    }
}
