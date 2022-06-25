class Solution {
    public int singleNonDuplicate(int[] arr) {
        int xor = 0;
        for (int val : arr)
            xor ^= val;
        
        return xor;    
    }
}



















