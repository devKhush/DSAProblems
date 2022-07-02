class Solution {
    private int mod = 1000000007;
    
    // Main Solution ***********************************************************
    public int maxArea_V1(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        int hCuts = horizontalCuts.length;
        int vCuts = verticalCuts.length;
        
        long maxPieceHeight = Math.max(horizontalCuts[0], h - horizontalCuts[hCuts - 1]);
        long maxPieceWidth = Math.max(verticalCuts[0], w - verticalCuts[vCuts - 1]);
        
        for (int i = 0; i < hCuts - 1; i++)
            maxPieceHeight = Math.max(horizontalCuts[i + 1] - horizontalCuts[i], maxPieceHeight);
        
        for (int i = 0; i < vCuts - 1; i++)
            maxPieceWidth = Math.max(verticalCuts[i + 1] - verticalCuts[i], maxPieceWidth);
        
        // return (int) (((maxPieceWidth % mod) * (maxPieceHeight % mod)) % mod);
        return (int) ((maxPieceWidth * maxPieceHeight) % mod);
    }
    
    
    // Compact Code
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long maxCakePieceHeight = getMaxLengthOfInterval(h, horizontalCuts);
        long maxCakePieceWidth = getMaxLengthOfInterval(w, verticalCuts);
        
        return (int) ((maxCakePieceHeight * maxCakePieceWidth) % mod);
    }
    
    private int getMaxLengthOfInterval(int totalLength, int[] intervals){
        Arrays.sort(intervals);
        
        int maxIntervalLength = Math.max(intervals[0], totalLength - intervals[intervals.length - 1]);
        
        for (int i = 0; i < intervals.length - 1; i++)
            maxIntervalLength = Math.max(intervals[i + 1] - intervals[i], maxIntervalLength);
        
        return maxIntervalLength;
    }
    
}