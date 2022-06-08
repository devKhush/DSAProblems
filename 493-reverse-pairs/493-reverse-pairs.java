class Solution {
    
    private int mergeSortAndCountReversePair(int low, int high, int[] arr, int[] temp){
        if (low >= high)
            return 0;

        int mid = (low + high)/2;
        
        int leftReversePairs = mergeSortAndCountReversePair(low, mid, arr, temp);
        int rightReversePairs = mergeSortAndCountReversePair(mid + 1, high, arr, temp);
        int mergeReversePairs = mergeAndCountReversePair(low, mid, high, arr, temp);
            
        return leftReversePairs + rightReversePairs + mergeReversePairs;    
    }
    
    
    private int mergeAndCountReversePair(int low, int mid, int high, int[] arr, int[] temp){
        int j = mid + 1;
        int reversePairs = 0;
        
        for(int i = low; i <= mid; i++){
            while (j <= high  &&  arr[i] > 2l * arr[j])
                j++;
            
            reversePairs += (j - (mid+1));
        }
        
        
        int i = low, k = low;
        j = mid + 1;
        
        while (i <= mid  &&  j <= high){
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        
        while (i <= mid)
            temp[k++] = arr[i++];
        
        while (j <= high)
            temp[k++] = arr[j++];
        
        for (int a = low; a <= high; a++)
            arr[a] = temp[a];
        
        return reversePairs;
    }
        
    
    public int reversePairs(int[] arr) {
        int n = arr.length;
        int reversePairs = mergeSortAndCountReversePair(0, n-1, arr, new int[n]);
        
        return reversePairs;
    }   
    
    
    
    // Brute Force *********************************************************************************
    public int reversePairs_BruteForce(int[] arr) {
        int n = arr.length;
        int reversePair = 0;
        
        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
                if (arr[i] > 2l * arr[j])
                    reversePair++;
            
        return reversePair;
    }
}