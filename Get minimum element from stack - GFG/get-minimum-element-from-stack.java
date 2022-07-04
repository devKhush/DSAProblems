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
    int min;
    Stack<Integer> s = new Stack<>();

    public int getMin(){
        if (s.isEmpty()) return -1;
        return min;
    }
    
    /*returns poped element from stack*/
    public int pop(){
        if (s.isEmpty()) return -1;
        int top = s.pop();
        if (min > top){
            int topOfStack = min;
            min = 2*min - top;
            return topOfStack;
        }
        return top;
    }

    /*push element x into the stack*/
    public void push(int x){
        if (s.isEmpty()){
            s.push(x);
            min = x;
        }
        else{
            if (min > x){
                s.push(2*x - min);
                min = x;
            }
            else
                s.push(x);
        }
    }	
}
















