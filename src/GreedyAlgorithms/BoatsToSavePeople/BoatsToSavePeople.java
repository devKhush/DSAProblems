package GreedyAlgorithms.BoatsToSavePeople;
import java.util.Arrays;

// https://leetcode.com/problems/boats-to-save-people/description/

public class BoatsToSavePeople {
    /*********************************** Greedy + Sorting Solution *********************************8
     * Time Complexity: O(n * log(n))
     * Space Complexity: O(1)
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int boats = 0;
        int left = 0, right = people.length - 1;

        while (left <= right){
            if (people[left] + people[right] <= limit){
                left++;
                right--;
                boats++;
            }
            else{
                right--;
                boats++;
            }
        }
        return boats;
    }
}
