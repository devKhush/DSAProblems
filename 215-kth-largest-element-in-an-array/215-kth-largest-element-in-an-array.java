class Solution {
    public void merge(int[] arr, int[] temp, int low, int mid, int high){
        int i=low, j=mid+1, k=low;
        
        while(i<=mid && j<=high){
            if (arr[i]<arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while(i<=mid)
            temp[k++] = arr[i++];
        while(j<=high)
            temp[k++] = arr[j++];
        for (int a=low; a<=high; a++)
            arr[a] = temp[a];
    }
    
    public void mergeSort(int[] arr, int[] temp, int low, int high){
        if (low<high){
            int mid = (low+high)/2;
            mergeSort(arr,temp,low,mid);
            mergeSort(arr,temp,mid+1,high);
            merge(arr,temp,low,mid,high);
        }
    }
    
    public void sort(int[] arr){
        mergeSort(arr, new int[arr.length], 0, arr.length-1);
    }
    
    public int findKthLargest(int[] arr, int k) {
        sort(arr);
        return arr[arr.length-k];
    }
}