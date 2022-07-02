class Solution {
    public int[] nextGreaterElement(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr2.length; i++){
            while (!stack.isEmpty()  &&  stack.peek() < arr2[i])
                nextGreaterMap.put(stack.pop(), arr2[i]);

            stack.push(arr2[i]);
        }

        int[] nextGreaterElement = new int[arr1.length];

        for (int i = 0; i < nextGreaterElement.length; i++)
            nextGreaterElement[i] = nextGreaterMap.getOrDefault(arr1[i], -1);
        
        return nextGreaterElement;
    }
    
    
    public int[] nextGreaterElement_BruteForce(int[] arr1, int[] arr2) {
        int[] nextGreaterElement = new int[arr1.length];

        main:
        for (int i = 0; i < arr1.length; i++) {
            int j = 0;
            while (j < arr2.length  &&  arr2[j] != arr1[i])
                j++;
            
            for (j = j + 1; j < arr2.length; j++) {
                if (arr2[j] > arr1[i]){
                    nextGreaterElement[i] = arr2[j];
                    continue main;
                }
            }
            nextGreaterElement[i] = -1;
        }
        return nextGreaterElement;
     }
    
    
}