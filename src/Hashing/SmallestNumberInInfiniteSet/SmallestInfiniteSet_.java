package Hashing.SmallestNumberInInfiniteSet;
import java.util.TreeSet;

// https://leetcode.com/problems/smallest-number-in-infinite-set/description/

public class SmallestInfiniteSet_ {
    /*********************************** TreeSet Solution *******************************************
     * Intuition: Maintain a set to store all the added values in smaller than current value
     */
    private TreeSet<Integer> set;
    private int smallestNumber;

    public SmallestInfiniteSet_() {
        set = new TreeSet<>();
        smallestNumber = 1;
    }

    // Tc -> O(log(n)) in worst case
    public int popSmallest() {
        if (!set.isEmpty()){
            return set.pollFirst();
        }
        return smallestNumber++;
    }

    // TC -> O(log(num)) in average case
    public void addBack(int num) {
        if (num < smallestNumber)
            set.add(num);
    }
}
