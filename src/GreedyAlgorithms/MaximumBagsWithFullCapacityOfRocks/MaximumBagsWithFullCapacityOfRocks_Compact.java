package GreedyAlgorithms.MaximumBagsWithFullCapacityOfRocks;
import java.util.Arrays;

/************************************** INTUITION ****************************************
 * Ask Yourself how many maximum bags you can fill according to the given condition?
 * You will say that, I will fill those bags first which are nearly filled, i.e, the difference of
 "their capacities" and "current filled state" is "minimum".
 * So, simply sort all the bags in increasing order of their "difference" of "their capacities" and
 "current filled state".
 * Traverse through all the bags one by one, and see if we have enough rocks to fill the current bags.
 * If yes, then fill that box.
 * If it is not possible to fill the current bag, just STOP because
 we will not be able to fill other bags too because they are sort in increasing order of the
 difference.
 */

public class MaximumBagsWithFullCapacityOfRocks_Compact {
    /* ********************************** Compact Code 1 ****************************************
    * We actually don't need to create any Objects for bags, we can just create a new array to do
      store the "remaining rocks" required to fill each bags.
    * Then we sort it, and find the total bags filled in same way as before
    *
    * Time Complexity: O(n) + O(n * log(n)) + O(n)  =  O(n * Log(n))
        where 'n' is the number of bags
        First O(n) to determine remaining rocks required for each bags
        O(n * Log(n)) for Sorting  & O(n) for traversal to determine bags filled
    * Space Complexity: O(n)
      We are creating additional array for to the store "remaining rocks" required to fill each bags
     */
    public int maximumBags_CompactSpace1(int[] capacity, int[] rocks, int additionalRocks) {
        // This array stores the "remaining rocks" required to fill each bags
        int[] rocksNeededToFillBags = new int[capacity.length];

        // remaining rocks required to fill each bags will be difference of their
        // total capacities & their current rock capacity
        for (int i = 0; i < rocksNeededToFillBags.length; i++)
            rocksNeededToFillBags[i] = capacity[i] - rocks[i];

        // Sort all "remaining rocks" required to fill each bags
        Arrays.sort(rocksNeededToFillBags);

        // Traverse all the bags & see if they can be filled or not
        int bagsFilled = 0;
        for (int i = 0; i < rocksNeededToFillBags.length; i++){
            if (rocksNeededToFillBags[i] <= additionalRocks){
                bagsFilled++;
                additionalRocks -= rocksNeededToFillBags[i];
            }
            else
                break;
        }
        return bagsFilled;
    }




    /* ********************************** Compact Code 2 ****************************************
    * We can just modify the capacity itself to do that, there is no need of even new array
      to store the "remaining rocks" required to fill each bags
    * Then we sort it, and find the total bags filled in same way as before
    *
    * Time Complexity: O(n) + O(n * log(n)) + O(n)  =  O(n * Log(n))
        where 'n' is the number of bags
        First O(n) to determine remaining rocks required for each bags
        O(n * Log(n)) for Sorting  & O(n) for traversal to determine bags filled
    * Space Complexity: O(1)
      We aren't using any additional space
     */
    public int maximumBags_CompactSpace2(int[] capacity, int[] rocks, int additionalRocks) {
        // We modify the 'capacity' array to store the "remaining rocks" required to fill each bags
        // remaining rocks required to fill each bags will be difference of their
        // total capacities & their current rock capacity
        for (int i = 0; i < capacity.length; i++)
            capacity[i] = capacity[i] - rocks[i];

        // Sort all "remaining rocks" required to fill each bags
        Arrays.sort(capacity);

        // Traverse all the bags & see if they can be filled or not
        int bagsFilled = 0;
        for (int i = 0; i < capacity.length; i++){
            if (capacity[i] <= additionalRocks){
                bagsFilled++;
                additionalRocks -= capacity[i];
            }
            else
                break;
        }
        return bagsFilled;
    }

}
