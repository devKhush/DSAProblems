package Hashing.TwoSum;
import java.util.HashMap;

// https://www.geeksforgeeks.org/given-an-array-a-and-a-number-x-check-for-pair-in-a-with-sum-as-x/
// https://youtu.be/dRUpbt8vHpo
// https://takeuforward.org/data-structure/two-sum-check-if-a-pair-with-given-sum-exists-in-array/
// https://leetcode.com/problems/two-sum/
// Question of Striver SDE Sheet

public class TwoSum {

    // **************************************** Brute Force ****************************************
    // TC -> O(n * n)
    // SC -> O(1)

    public int[] twoSum_BruteForce(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++)
            for (int j = i+1; j < arr.length; j++)
                if (arr[i] + arr[j] == target){
                    int[] answer = new int[]{i, j};
                    return answer;
                }

        return arr;
    }



    // **************************** Efficient Solution using HashMap ****************************
    // TC -> O(n)
    // SC -> O(n)

    public int[] twoSum_HashMapSolution(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] answer = new int[2];

        for (int i=0; i<nums.length; i++){
            if (map.containsKey(target - nums[i])){
                answer[0] = map.get(target - nums[i]);
                answer[1] = i;
                return answer;
            }
            map.put(nums[i], i);
        }
        return nums;
    }
}