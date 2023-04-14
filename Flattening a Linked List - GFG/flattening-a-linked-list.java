//{ Driver Code Starts
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
}
// } Driver Code Ends


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
    public Node flatten(Node root) {
        ArrayList<Node> lst = new ArrayList<>();
        Node ptr = root;

        while (ptr != null){
            lst.add(ptr);
            ptr = ptr.next;
        }
        return merge_DnC(0, lst.size() - 1, lst);
    }
    
    private Node merge_DnC(int low, int high, ArrayList<Node> arr){
        if (low > high)
            return null;
        if (low == high)
            return arr.get(low);
        
        int mid = low + (high - low)/2;
        Node p1 = merge_DnC(low, mid, arr);
        Node p2 = merge_DnC(mid + 1, high, arr);
        return mergeSortedLists(p1, p2);
    }

    private Node mergeSortedLists(Node h1, Node h2){
        Node head = new Node(-1);
        Node ptr = head;

        while (h1 != null || h2 != null){
            int val1 = h1 != null ? h1.data : Integer.MAX_VALUE;
            int val2 = h2 != null ? h2.data : Integer.MAX_VALUE;
            if (val1 <= val2){
                ptr.bottom = h1;
                h1 = h1.bottom;
            }
            else{
                ptr.bottom = h2;
                h2 = h2.bottom;
            }
            ptr = ptr.bottom;
        }
        return head.bottom;
    }
}















