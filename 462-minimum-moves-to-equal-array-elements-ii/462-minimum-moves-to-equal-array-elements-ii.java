class Solution {
    public int minMoves2(int[] arr) {
        Arrays.sort(arr);
        
        int mid = arr.length / 2;
        int low = 0, high = arr.length - 1;
        int moves = 0;
        
        while (low < mid)
            moves += arr[mid] - arr[low++];
        while(mid < high)
            moves += arr[high--] - arr[mid];
        return moves;
    }
}