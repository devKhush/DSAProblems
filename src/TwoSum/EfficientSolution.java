package TwoSum;

import java.util.HashMap;

public class EfficientSolution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer,Integer> sumPairs = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (sumPairs.containsKey(target-nums[i])){
                result[0] = sumPairs.get(target-nums[i]);
                result[1] = i;
                return result;
            }
            sumPairs.put(nums[i],i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4};
        int target = 6;
        int[] index = new SolutionUsingArray().twoSum(arr,target);
        System.out.println(index[0]+" "+index[1]);
    }
}
