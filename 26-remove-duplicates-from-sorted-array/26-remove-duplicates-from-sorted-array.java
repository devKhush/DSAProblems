class Solution {
    public int removeDuplicates(int[] arr) {
        int length = 1;
        
        for (int i = 1; i < arr.length; i++)
            if (arr[length - 1] != arr[i])
                arr[length++] = arr[i];
        
        
        return length;
    }
}