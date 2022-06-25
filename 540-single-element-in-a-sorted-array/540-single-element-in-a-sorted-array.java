class Solution {
    public int singleNonDuplicate(int[] arr) {
       int low = 0, high = arr.length - 2;

        while (low <= high){
            int mid = (low + high)/2;
            
           if (mid % 2 == 0){
               if (arr[mid] == arr[mid + 1])
                   low = mid + 1;
               else 
                   high = mid - 1;
           }
           else if (mid % 2 == 1){
              if (arr[mid] != arr[mid + 1]) 
                  low = mid + 1;
              else 
                  high = mid - 1;
           }
        }
        return arr[low];
    }
}



















