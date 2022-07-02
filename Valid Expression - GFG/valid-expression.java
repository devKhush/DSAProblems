// { Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*; 
class GFG{
    public static void main(String args[]) throws IOException { 
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while(t-- > 0){
            String S = sc.nextLine().trim();
            Solution ob = new Solution();
            if(ob.valid(S))
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}// } Driver Code Ends


//User function Template for Java
class Solution {
    boolean valid(String s)    { 
        Stack<Character> stack = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);

            switch (ch){
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(')
                        stack.pop();
                    else return false;
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{')
                        stack.pop();
                    else return false;
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[')
                        stack.pop();
                    else return false;
                    break;
                default:
                    stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
} 