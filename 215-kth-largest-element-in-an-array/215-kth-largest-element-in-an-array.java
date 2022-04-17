class Solution {
    
    public int findKthLargest_ByMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i=0; i<k; i++)
            pq.add(arr[i]);
        
        for (int i=k; i<arr.length; i++){
            if (arr[i] > pq.peek()){
                pq.remove();
                pq.add(arr[i]);
            }
        }
        
        return pq.peek();
    }
    
    public int findKthLargest(int[] arr, int k) {
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