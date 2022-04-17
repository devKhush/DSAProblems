class Solution {
    public int triangleNumber(int[] arr) {
        Arrays.sort(arr);
        
        int triangleCount = 0;

        for (int i = arr.length-1; i>=0; i--){
            int left = 0;
            int right = i-1;
            
            while (left < right){
                if (arr[left] + arr[right] > arr[i]){
                    triangleCount += (right - left);
                    right--;
                }
                else
                    left++;
            }
        }
        return triangleCount;
    }
}