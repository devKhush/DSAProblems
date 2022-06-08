package Arrays.RemoveElement;

public class RemoveElement {

    // Simple approach
    public int removeElement(int[] nums, int val) {
        int n = nums.length;

        // Two pointers approach (one pointer is 'for each' loop)
        int length = 0;

        for (int value : nums){
            if (value != val){
                nums[length] = value;
                length++;
            }
        }

        return length;
    }
}
