class Solution {
    public int[] nextGreaterElements(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] nge = new int[n];
        
        Arrays.fill(nge, -1);
        
        for (int i = 0; i < 2*n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek() % n] < arr[i % n])
                nge[stack.pop() % n] = arr[i % n];
            
            stack.push(i);
        }
        
        return nge;
    }
}