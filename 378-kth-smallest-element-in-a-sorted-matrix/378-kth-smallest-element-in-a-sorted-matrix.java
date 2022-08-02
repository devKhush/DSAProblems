class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];
        int kthSmallestElement = -1;
        
        while (low <= high){
            int mid = low + (high - low)/2;
            
            int countLessThanOrEqual = 0;
            for (int i = 0; i < n; i++)
                countLessThanOrEqual += countLessThanOrEqualToMid(matrix[i], n, mid);
            
            if (countLessThanOrEqual < k)
                low = mid + 1;
            else if (countLessThanOrEqual >= k){
                kthSmallestElement = mid;
                high = mid - 1;
            }
        }
        return kthSmallestElement;
    }
    
    public int countLessThanOrEqualToMid(int[] arr, int n, int value){
        int countLessThanOrEqual = 0;
        int low = 0, high = n - 1;
        
        while (low <= high){
            int mid = low + (high - low)/2;
            
            if (arr[mid] <= value){
                countLessThanOrEqual = mid + 1;
                low = mid + 1;
            }
            else if (arr[mid] > value)
                high = mid - 1;
        }
        return countLessThanOrEqual;
    }
    
    public int kthSmallest_maxHeap(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        int count = 0;
        
        for (int[] row : matrix){
            for (int num : row){
                if (count < k){
                    maxHeap.add(num);
                    count++;
                }
                else if (count >= k  &&  num < maxHeap.peek()){
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