// { Driver Code Starts
import java.util.*;
class QueueNode
{
	int data;
	QueueNode next;
	QueueNode(int a)
	{
	    data = a;
	    next = null;
	}
}

class GfG {
	public static void main(String args[])
	{
		 Scanner sc = new Scanner(System.in);
		 int t=sc.nextInt();
		 while(t>0)
		 {
			MyQueue obj = new MyQueue();
			int Q = sc.nextInt();
			while(Q>0)
			{
				int QueryType = 0;
				QueryType = sc.nextInt();
				if(QueryType == 1)
				{
					int a = sc.nextInt();
					
					obj.push(a);
					
				}else if(QueryType == 2)
				{
				System.out.print(obj.pop()+" ");
				}
				Q--;
			}
			System.out.println("");
			t--;
		 }
	}
}




// } Driver Code Ends


/*The structure of the node of the queue is
class QueueNode
{
	int data;
	QueueNode next;
	QueueNode(int a)
	{
	    data = a;
	    next = null;
	}
}*/

class MyQueue{
    public QueueNode front, rear;
    
    //Function to push an element into the queue.
	void push(int a){
        // Your code here
        if (front==null){
            this.front = new QueueNode(a);
            this.rear = front;
            return;
        }
        this.rear.next = new QueueNode(a);
        this.rear = this.rear.next;
	}
	
    //Function to pop front element from the queue.
	int pop(){
        // Your code here
        if (this.front ==null)
            return -1;
        QueueNode top = front;
        front = front.next;
        top.next = null;
        return top.data;
	}
}




