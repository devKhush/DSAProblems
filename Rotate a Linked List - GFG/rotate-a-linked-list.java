// { Driver Code Starts
import java.util.*;
class Node {
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
} 

class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            
            int a = sc.nextInt();
            Node head = new Node(a);
            Node tail = head;
            
            for (int i=0; i<n-1; i++)
            {
                a = sc.nextInt();
                tail.next = new Node(a);
                tail = tail.next;
            }
            
            int k = sc.nextInt();
            
            Solution ob = new Solution();
            head = ob.rotate(head,k);
            printList(head);
        }
    }
    
    public static void printList(Node n) {
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }
}
// } Driver Code Ends


/* node of linked list:

class Node{
    int data;
    Node next;
    Node(int d){
        data=d;
        next=null;
    }
}

*/

class Solution{
    private int getLength(Node head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }
    //Function to rotate a linked list.
    public Node rotate(Node head, int k) {
         if (head == null)
            return null;

        int length = getLength(head);
        k = k % length;

        if (k == 0)
            return head;

        int distanceToTravel = k;
        Node ptr = head;

        for (int i = 1; i < distanceToTravel; i++)
            ptr = ptr.next;

        Node newHead = ptr.next;
        ptr.next = null;

        Node lastNode = newHead;
        while (lastNode.next != null)
            lastNode = lastNode.next;

        lastNode.next = head;
        return newHead;
    }
}
