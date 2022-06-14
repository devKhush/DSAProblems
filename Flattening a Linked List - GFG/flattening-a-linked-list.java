// { Driver Code Starts
import java.util.Scanner;
import java.util.*;
import java.io.*;

class Node
{
	int data;
	Node next;
	Node bottom;
	
	Node(int d)
	{
		data = d;
		next = null;
		bottom = null;
	}
}


class Flatttening_A_LinkedList
{	
    Node head;
	
	void printList(Node node)
    {
        //Node temp = head;
        while (node != null)
        {
            System.out.print(node.data + " ");
            node =node.bottom;
        }
        System.out.println();
    }
	public  static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		Flatttening_A_LinkedList list = new Flatttening_A_LinkedList();
		while(t>0)
		{
			int N = sc.nextInt();
			int arr[] = new int[N];
			for(int i=0;i<N;i++)
				arr[i] = sc.nextInt();
			
			Node temp = null;
			Node tempB = null;
			Node pre = null;
			Node preB = null;	
			int flag=1;
			int flag1=1;
			for(int i=0; i<N;i++)
			{
				int m = arr[i];
				m--;
				int a1 = sc.nextInt();
				temp = new Node(a1);
				if(flag == 1)
				{
					list.head = temp;
					pre = temp;
					flag = 0;
					flag1 = 1;
				}
				else
				{
					pre.next = temp;
					pre = temp;
					flag1 = 1;
				}
				
				for(int j=0;j<m;j++)
				{
					int a = sc.nextInt();
					tempB = new Node(a);
					if(flag1 == 1)
					{
						temp.bottom = tempB;
						preB = tempB;
						flag1 = 0;
					}
					else
					{
						preB.bottom = tempB;
						preB = tempB;
					}
				}
				
			}
			//list.printList();
			GfG g = new GfG();
			Node root = g.flatten(list.head);
			list.printList(root);
		
		t--;
		}
	}	
}// } Driver Code Ends


/*Node class  used in the program
class Node
{
	int data;
	Node next;
	Node bottom;
	
	Node(int d)
	{
		data = d;
		next = null;
		bottom = null;
	}
}
*/
/*  Function which returns the  root of 
    the flattened linked list. */
class GfG{
    public Node flatten(Node head){
        Node ptr = head;

        while (ptr != null){
            Node bottomPtr = ptr;

            while (bottomPtr.bottom != null)
                bottomPtr = bottomPtr.bottom;

            bottomPtr.bottom = ptr.next;

            // Node prev = ptr;
             ptr = ptr.next;
            // prev.next = null;
        }
        return mergeSort(head);
    }

    private Node mergeSort(Node head){
        if (head == null || head.bottom == null)
            return head;

        Node fast = head, slow = head;

        while (fast.bottom != null  &&  fast.bottom.bottom != null){
            fast = fast.bottom.bottom;
            slow = slow.bottom;
        }
        Node midHalf = slow.bottom;
        slow.bottom = null;

        Node sortedHalf1 = mergeSort(head);
        Node sortedHalf2 = mergeSort(midHalf);
        return merge(sortedHalf1, sortedHalf2);
    }

    private Node merge(Node head1, Node head2){
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        Node l1 = head1, l2 = head2;
        if (l1.data > l2.data){
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }
        Node head = l1;

        while (l1 != null && l2 != null){
            Node prevSmaller = null;

            while (l1 != null && l1.data <= l2.data){
                prevSmaller = l1;
                l1 = l1.bottom;
            }

            prevSmaller.bottom = l2;

            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }
        return head;
    }
}