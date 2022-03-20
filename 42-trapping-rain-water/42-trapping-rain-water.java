class Solution {
    public int trap(int[] height) {
        int[] leftMaxWater = new int[height.length];
        int[] rightMaxWater = new int[height.length];
        
        for (int i=0; i<height.length; i++){
            if (i==0)
                leftMaxWater[i] = height[i];
            else
                leftMaxWater[i] = Math.max(height[i], leftMaxWater[i-1]);
        }
        for (int i=height.length-1; i>=0; i--){
            if (i==height.length-1)
                rightMaxWater[i] = height[i];
            else
                rightMaxWater[i] = Math.max(height[i], rightMaxWater[i+1]);
        }
        
        int water = 0;
        
        for (int i=0; i<height.length-1; i++){
            int minBoundary = Math.min(leftMaxWater[i] , rightMaxWater[i]);
            if (minBoundary-height[i] >0)
                water += (minBoundary - height[i]);
        }
        return water;
    }
}