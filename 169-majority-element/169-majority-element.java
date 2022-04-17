class Solution {
    public void merge(int[] arr, int[] temp, int low, int mid, int high){
        int i = low, j = mid+1;
        int k = low;

        while(i<=mid && j<=high){
            if (arr[i]<=arr[j])
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

    public void mergeSort(int[] arr, int[] temp, int low, int high){
        if (low<high){
            int mid = (low+high)/2;
            mergeSort(arr, temp, low, mid);
            mergeSort(arr, temp, mid+1, high);
            merge(arr, temp, low, mid, high);
        }
    }
    
    public int majorityElement_BySorting(int[] arr) {
        this.mergeSort(arr, new int[arr.length], 0, arr.length-1);
        
        int majorElement = arr[0];
        int count = 1;
        
        int currCount = 1;
        for (int i=1; i<arr.length; i++){
            
            if (arr[i]==arr[i-1]){
                currCount++;
                if (currCount>count){
                    majorElement = arr[i];
                    count = currCount;
                }
                
            }
            else{
                currCount = 1;   
            }
        }
        return majorElement;
    }
    
    public int majorityElement_ByHashMap(int[] arr) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for (int value : arr)
            count.put(value, count.getOrDefault(value, 0) + 1);
        
        int MajorElement = arr[0];
        int MajorCount = count.get(arr[0]);
        
        for (int value : count.keySet()){
            if (count.get(value) > MajorCount){
                MajorElement = value;
                MajorCount = count.get(value);
            }
        }
        
        return MajorElement;
    }
    
    public int majorityElement(int[] arr) {
        this.mergeSort(arr, new int[arr.length], 0, arr.length-1);
        return arr[arr.length/2];
    }
}