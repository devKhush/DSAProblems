// { Driver Code Starts
import java.util.*;



class Get_Min_From_Stack
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while(T>0)
		{
			int q = sc.nextInt();
			GfG g = new GfG();
			while(q>0)
			{
				int qt = sc.nextInt();
				
				if(qt == 1)
				{
					int att = sc.nextInt();
					g.push(att);
					//System.out.println(att);
				}
				else if(qt == 2)
				{
					System.out.print(g.pop()+" ");
				}
				else if(qt == 3)
				{
					System.out.print(g.getMin()+" ");
				}
			
			q--;
			}
			System.out.println();
		T--;
		}
		
	}
}


// } Driver Code Ends


class GfG{
    int minEle;
    Stack<Node> s = new Stack<>();

    public int getMin(){
        if (s.isEmpty()) return -1;
        return s.peek().min;
    }
    
    /*returns poped element from stack*/
    public int pop(){
        if (s.isEmpty()) return -1;
        return s.pop().data;
    }

    /*push element x into the stack*/
    public void push(int x){
        if (s.isEmpty())
            s.push(new Node(x, x));
        else{
            int min = Math.min(x, s.peek().min);
            s.push(new Node(x, min));
        }
    }	
    
    class Node{
        int data, min;
        Node(int data, int min){
            this.data = data;
            this.min = min;
        }
    }
}
















