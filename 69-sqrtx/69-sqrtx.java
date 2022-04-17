class Solution {
    public int mySqrt(int x) {
        
        int low = 1, high = x, sqrt = x;
        
        while (low <= high){
            int mid = (low + high)/2;
            
            if (mid <= x/mid){
                sqrt = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return sqrt;
    }
}