package Queues.RemoveElement;
import java.util.ArrayDeque;
import java.util.Queue;

public class RemoveElement {
    /** ******************************* Solution using Queue *************************************
     * Time Complexity: O(n) + O(n) = O(n)
     * Space Complexity: O(n)
     */
    public int removeElement_Queue(int[] nums, int val) {
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


    /** ******************************* Solution using Two Pointer ***********************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int removeElement_TwoPointer(int[] nums, int val) {
        int size = 0;

        for (int i = 0; i < nums.length; i++)
            if (nums[i] != val)
                nums[size++] = nums[i];

        return size;
    }
}
