class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        
        for (int[] row : matrix)
            for (int num : row)
                minHeap.add(num);
        
        for (int i = 1; i < k; i++)
            minHeap.remove();
        
        return minHeap.remove();
    }
}