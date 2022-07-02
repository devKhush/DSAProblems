class Solution {
    private int mod = 1000000007;

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
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
}