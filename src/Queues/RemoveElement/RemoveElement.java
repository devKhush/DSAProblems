package Queues.RemoveElement;
import java.util.LinkedList;
import java.util.Queue;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int n = nums.length;

        Queue<Integer> queue = new LinkedList<Integer>();

        for (int value : nums){
            if (value != val)
                queue.add(value);
        }

        int k = queue.size();
        int i = 0;

        while (!queue.isEmpty()){
            nums[i] = queue.remove();
            i++;
        }

        return k;
    }
}
