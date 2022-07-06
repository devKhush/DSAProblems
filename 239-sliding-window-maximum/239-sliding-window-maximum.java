class Solution {
    public int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;

        Deque<Integer> deque = new ArrayDeque<>();
        int[] maxSlidingWindow = new int[n - k + 1];
        int index = 0;

        for (int i = 0; i < n; i++){
            if (!deque.isEmpty()  &&  deque.peekFirst() == i - k)
                deque.removeFirst();

            while (!deque.isEmpty()  &&  arr[i] >= arr[deque.peekLast()])
                deque.removeLast();

            deque.addLast(i);

            if (i >= k - 1)
                maxSlidingWindow[index++] = arr[deque.peekFirst()];
        }
        return maxSlidingWindow;
    }
    
    
    // BRUTE FORCE *****************************************************************************
    public int[] maxSlidingWindow_BruteForce(int[] arr, int k) {
        int n = arr.length;
        int[] maxSlidingWindow = new int[n - k + 1];
        
        for (int i = 0; i <= n - k; i++){
            int max = Integer.MIN_VALUE;
            
            for (int j = i; j < i + k; j++)
                max = Math.max(arr[j], max);
            
            maxSlidingWindow[i] = max;
        }
        return maxSlidingWindow;
    }
}