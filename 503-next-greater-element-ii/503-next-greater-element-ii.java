class Solution {
    public int[] nextGreaterElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] nextGreaterElement = new int[n];

        for (int i = 2*n -1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr[i % n] >= stack.peek())
                stack.pop();

            nextGreaterElement[i % n] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(arr[i % n]);
        }

        return nextGreaterElement;
    }
    
    
    public int[] nextGreaterElements_Solution1(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] nge = new int[n];
        
        
        for (int i = 0; i < 2*n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek()] < arr[i % n])
                nge[stack.pop()] = arr[i % n];
            
            if (i < n)
                stack.push(i);
        }
        
        while (!stack.isEmpty())
            nge[stack.pop()] = -1;
        
        return nge;
    }
}