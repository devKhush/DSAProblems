class Solution {
    public int singleNonDuplicate(int[] arr) {
        int low = 0,  high = arr.length-1;
        
        if (low == high)
            return arr[low];
        if (arr[low] != arr[low+1])
            return arr[low];
        if (arr[high] != arr[high-1])
            return arr[high];
        
        while (low <= high){
            int mid = (low + high)/2;
            
            if (arr[mid-1]!=arr[mid] && arr[mid]!=arr[mid+1])
                return arr[mid];
            
            if (mid % 2 == 0){
                if (arr[mid] == arr[mid+1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            else{
                if (arr[mid] == arr[mid-1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        
        return -1;
    }
}