package Arrays.RemoveElement;

public class RemoveElement {

    // Simple approach
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int k = 0;

        // Two pointers approach (one pointer is for each loop)
        int i = 0;

        for (int value : nums){
            if (value != val){
                nums[i] = value;
                i++;
                k++;
            }
        }

        return k;
    }
}
