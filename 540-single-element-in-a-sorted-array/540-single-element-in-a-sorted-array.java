class Solution {
    public int singleNonDuplicate(int[] arr) {
       int n = arr.length;
        int low = 0, high = n - 1;

        if (n == 1)
            return arr[0];
        if (arr[low] != arr[low + 1])
            return arr[low];
        if (arr[high] != arr[high - 1])
            return arr[high];


        while (low <= high){
            int mid = (low + high)/2;

            if (arr[mid] != arr[mid - 1]  && arr[mid] != arr[mid + 1])
                return arr[mid];

            if (mid % 2 == 0){
                if (arr[mid] == arr[mid + 1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            else{
                if (arr[mid] == arr[mid - 1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -999;
    }
}



















