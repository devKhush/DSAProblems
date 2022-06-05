class Solution {
    public void nextPermutation(int[] arr) {
        int n = arr.length;
        
        int breakDownIndex = -1;
      
        for (int i = n-2; i >= 0; i--)
            if (arr[i] < arr[i+1]){
                breakDownIndex = i;
                break;
            }
        
        // System.out.println(breakDownIndex);
        
        if (breakDownIndex == -1){
            reverse(0, n-1, arr);
            return;
        }
        
        int justGreaterThanBreakDownIndex = -1;
        
        for (int i = n-1; i >= 0; i--)
            if (arr[i] > arr[breakDownIndex]){
                justGreaterThanBreakDownIndex = i;
                break;
            }
        
        
        this.swap(justGreaterThanBreakDownIndex, breakDownIndex, arr);
        
        this.reverse(breakDownIndex + 1, n - 1, arr);
    }
    
    
    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private void reverse(int low, int high, int[] arr){
        while (low < high){
            swap(low, high, arr);
            low++;
            high--;
        }
    }
}