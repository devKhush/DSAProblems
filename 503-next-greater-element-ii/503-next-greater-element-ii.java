class Solution {
    public int[] nextGreaterElements(int[] arr) {
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