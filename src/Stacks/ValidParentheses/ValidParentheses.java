package Stacks.ValidParentheses;

// https://leetcode.com/problems/valid-parentheses/

public class ValidParentheses {
    public boolean isValid(String s) {
        MyStack stack= new MyStack();

        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if (ch == ')' && !stack.isEmpty()){
                if (stack.peek()=='(')
                    stack.pop();
                else
                    return false;
            }
            else if (ch == ']' && !stack.isEmpty()){
                if (stack.peek()=='[')
                    stack.pop();
                else
                    return false;
            }
            else if (ch == '}' && !stack.isEmpty()){
                if (stack.peek()=='{')
                    stack.pop();
                else
                    return false;
            }
            else
                stack.push(ch);
        }
        return stack.isEmpty();
    }
}


class MyStack{

    public Node top;

    public MyStack(){
        this.top = null;
    }

    public boolean isEmpty(){
        return top==null;
    }

    public char peek(){
        return top.ch;
    }

    public void pop(){
        if (top.next == null){
            top = null;
            return;
        }
        Node temp = top;
        top = top.next;
        temp.next = null;
    }

    public void push(char ch){
        if (top==null)
            top = new Node(ch);
        else{
            Node temp = new Node(ch);
            temp.next = top;
            top = temp;
        }
    }
}


class Node{
    public char ch;
    public Node next;

    public Node(char ch){
        this.ch = ch;
    }
}
