class Solution {
    
    // Merge sort O(n.logn)
    private void merge(int[] arr, int[] temp, int low, int mid, int high){
        int i=low, j=mid+1, k=low;
        
        while (i<=mid && j<=high){
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i<=mid)
            temp[k++] = arr[i++];
        while (j<=high)
            temp[k++] = arr[j++];
        for (int a=low; a<=high; a++)
            arr[a] = temp[a];
        
    }
    
    private void mergeSort(int[] arr, int[] temp, int low, int high){
        if (low < high){
            int mid = (low + high)/2;
            mergeSort(arr, temp, low, mid);            
            mergeSort(arr, temp, mid + 1, high);
            merge(arr, temp, low, mid, high);
        }
    }
    
    // Quick Sort O(n.logn)
    private int partition(int[] arr, int low, int high){
        int pivot = arr[low];
        int i = low+1;
        int j = high;
        
        while (i <= j){
            while (i < arr.length  &&  arr[i] <= pivot)
                i++;
            while (j > 0  &&  arr[j] > pivot)
                j--;
            
            if (i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[low] = arr[j];
        arr[j] = pivot;
        return j;
    }
    
    private void quickSort(int[] arr, int low, int high){
        if (low < high){
            int partition = partition(arr, low, high);
            quickSort(arr, low, partition-1);    
            quickSort(arr, partition+1, high);
        }
    }
    
    public int[] sortArray(int[] nums) {
        // this.mergeSort(nums, new int[nums.length], 0, nums.length-1);
        
        quickSort(nums, 0, nums.length-1);
        return nums;
    }
}