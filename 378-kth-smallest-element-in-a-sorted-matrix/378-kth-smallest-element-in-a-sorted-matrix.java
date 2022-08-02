class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        int count = 0;
        
        for (int[] row : matrix){
            for (int num : row){
                if (count < k){
                    maxHeap.add(num);
                    count++;
                }
                else if (num < maxHeap.peek()){
                    maxHeap.remove();
                    maxHeap.add(num);
                }
            }
        }
                
        return maxHeap.peek();
    }
    
    public int kthSmallest_MinHeap(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        
        for (int[] row : matrix)
            for (int num : row)
                minHeap.add(num);
        
        for (int i = 1; i < k; i++)
            minHeap.remove();
        
        return minHeap.remove();
    }
}

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/2366981/C%2B%2B-JAVA-or-Full-Explanation-or-Using-6-Method