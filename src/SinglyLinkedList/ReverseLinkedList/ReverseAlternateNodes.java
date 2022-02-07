package SinglyLinkedList.ReverseLinkedList;

public class ReverseAlternateNodes {
    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null)
            return head;

        ListNode node1 = head, node2, safeNode;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        while (node1!=null && node1.next!=null){
            node2 = node1.next;
            safeNode = node1.next.next;

            node1.next = safeNode;
            node2.next = node1;
            prev.next = node2;

            prev = node1;
            node1 = node1.next;
        }
        return dummy.next;
    }

    public void display(ListNode head){
        System.out.print("[");
        while (head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ReverseAlternateNodes solution = new ReverseAlternateNodes();
        solution.display(head);
        head = solution.swapPairs(head);
        solution.display(head);
    }
}
