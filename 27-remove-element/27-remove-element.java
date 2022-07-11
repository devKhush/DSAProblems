class Solution {
    public int removeElement(int[] nums, int val) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        for (int value : nums)
            if (value != val)
                queue.add(value);
        
        int k = queue.size();
        int i = 0;
        
        while (!queue.isEmpty())
            nums[i++] = queue.remove();
        
        return k;
    }
}