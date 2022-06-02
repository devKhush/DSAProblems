package BinarySearch.SearchInsertPosition;

class SearchInsertPosition {
    public int searchInsert(int[] arr, int target) {
        int low = 0, high = arr.length-1;
        
        while (low <= high){
            int mid = (low + high)/2;
            
            if (arr[mid] == target)
                return mid;
            
            else if (arr[mid] > target)
                high = mid - 1;
            
            else
                low  = mid + 1;
        }
        
        return low; 
        
    }
}