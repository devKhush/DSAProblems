// { Driver Code Starts
import java.util.Scanner;
import java.util.Stack;
class SortedStack{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
			Stack<Integer> s=new Stack<>();
			int n=sc.nextInt();
			while(n-->0)
			s.push(sc.nextInt());
			GfG g=new GfG();
			Stack<Integer> a=g.sort(s);
			while(!a.empty()){
				System.out.print(a.peek()+" ");
				a.pop();
			}
			System.out.println();
		}
	}
}// } Driver Code Ends


/*Complete the function below*/
class GfG{
	public Stack<Integer> sort(Stack<Integer> stack){
		if (stack.isEmpty())
            return stack;
        int top = stack.pop();

        sort(stack);

        insertInTopOfStack(stack, top);
        return stack;
    }

    private void insertInTopOfStack(Stack<Integer> stack, int currentElement){
        if (stack.isEmpty() || currentElement >= stack.peek()){
            stack.push(currentElement);
            return;
        }
        int top = stack.pop();

        insertInTopOfStack(stack, currentElement);

        stack.push(top);
    }
}

