class Solution {
    public void merge(int[] arr, int[] temp, int l, int m, int h){
        int i=l, j=m+1, k = l;
        while (i<=m && j<=h){
            if (arr[i]>arr[j]){
                temp[k] = arr[j];
                j++; k++;
            }
            else{
                temp[k] = arr[i];
                k++; i++;
            }
        }
        while (i<=m){
            temp[k] = arr[i];
            k++; i++;
        }
        while (j<=h){
            temp[k] = arr[j];
            j++; k++;
        }
        for (int a=l; a<=h; a++) {
            arr[a] = temp[a];
        }
    }

    public void mergeSort(int[] arr, int[] temp, int l, int h){
        if (l<h){
            int mid = (l+h)/2;
            mergeSort(arr,temp,l,mid);
            mergeSort(arr,temp,mid+1,h);
            merge(arr, temp, l, mid, h);
        }
    }
    
    public void sortColors_(int[] nums) {
        mergeSort(nums, new int[nums.length],0, nums.length-1);
    }
    
    
    
    

    
    // Sorting 0s, 1s, & 2s
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0;
        
        for (int ele: nums){
            if (ele == 0) count0++;
            else if (ele == 1) count1++;
        }
        
        int i = 0;
        while (i < count0)
            nums[i++] = 0;
        while (i < count0 + count1)
            nums[i++] = 1;
        while (i < nums.length)
            nums[i++] = 2;
        
    }
}