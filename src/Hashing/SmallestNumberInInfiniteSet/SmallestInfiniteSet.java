package Hashing.SmallestNumberInInfiniteSet;
import java.util.HashSet;

// https://leetcode.com/problems/smallest-number-in-infinite-set/description/

public class SmallestInfiniteSet {
    /*********************************** HashSet Solution *******************************************
     * Intuition: Maintain a set to store all the removed values
     */
    private HashSet<Integer> removedSet;
    private int smallestNumber;

    public SmallestInfiniteSet() {
        this.removedSet = new HashSet<>();
        this.smallestNumber = 1;
    }

    // Tc -> O(n) in worst case
    public int popSmallest() {
        while (removedSet.contains(smallestNumber)){
            smallestNumber++;
        }
        removedSet.add(smallestNumber);
        return smallestNumber++;
    }

    // TC -> O(1) in average case
    public void addBack(int num) {
        removedSet.remove(num);
        smallestNumber = Math.min(smallestNumber, num);
    }
}
