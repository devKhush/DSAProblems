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
    
    public void sortColors(int[] nums) {
        mergeSort(nums, new int[nums.length],0, nums.length-1);
    }
}