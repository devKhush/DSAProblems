class Solution {
    
    public int findKthLargest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < k; i++)
            minHeap.add(arr[i]);
        
        for (int i = k; i < arr.length; i++){
            if (minHeap.peek() < arr[i]){
                minHeap.remove();
                minHeap.add(arr[i]);
            }
        }
        
        return minHeap.peek();
    }
    
    public int findKthLargest_ByMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i : arr)
            maxHeap.add(i);
        
        int i = 1;
        while (i < k){
            maxHeap.remove();
            i++;
        }
        
        return maxHeap.poll();
    }
    

    
    public int findKthLargest_BySorting(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[arr.length-k];
    }
}