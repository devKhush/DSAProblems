class Solution {
    public int[] nextGreaterElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[arr.length];
        int n = arr.length;
        
        Arrays.fill(nge, -1);
        
        for (int i = 0; i < 2*n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek() % n] < arr[i % n])
                nge[stack.pop() % n] = arr[i % n];
            
            stack.push(i);
        }
        
        // while (!stack.isEmpty())
        //     nge[stack.pop() % n] = -1;
        
        return nge;
    }
}