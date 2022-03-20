class Solution {
    public int trap(int[] height) {
        int low = 0;
        int high = height.length-1;
        int leftMaxWater = 0, rightMaxWater = 0;
        int water = 0;
        
        while(low <= high){
            if (height[low] <= height[high]){
                if (height[low] >= leftMaxWater)
                    leftMaxWater = height[low++];
                else
                    water += (leftMaxWater - height[low++]);
            }
            else{
                if (height[high] >= rightMaxWater)
                    rightMaxWater = height[high--];
                else
                    water += (rightMaxWater - height[high--]);
            }
        }
        return water;
    }
}