package GreedyAlgorithms.MaximumBagsWithFullCapacityOfRocks;

import java.util.Arrays;
import java.util.Comparator;

// PRE-REQUISITE: "FRACTIONAL KNAPSACK" in Greedy Algorithms

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

class MaximumBagsWithFullCapacityOfRocks_Greedy {
    /*
    * Time Complexity: O(n * log(n)) + O(n)  =  O(n * Log(n))
        where 'n' is the number of bags
        O(n * Log(n)) for Sorting  & O(n) for traversal to determine bags filled
    * Space Complexity: O(n)
      We are creating additional 'Bag' Objects for each given bags
     */
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        // Create bags objects to store their total capacities & current capacities
        Bag[] bags = new Bag[capacity.length];
        for (int i = 0; i < bags.length; i++)
            bags[i] = new Bag(capacity[i], rocks[i]);

        // Sort all the bags in increasing order of their "difference" of "their total capacities"
        // and "current filled state".
        Arrays.sort(bags, new BagComparator());
        
        int filledBags = 0;

        // Traverse all the bags & see if they can be filled or not
        for (int i = 0; i < bags.length; i++){
            Bag bag = bags[i];
            
            if (bag.capacity - bag.rock <= additionalRocks){
                filledBags++;
                additionalRocks -= bag.capacity - bag.rock;
            }
            else
                break;
        }
        // return filled bags
        return filledBags;
    }
    
    static class Bag{
        int capacity, rock;
        public Bag(int capacity, int rock){
            this.capacity = capacity;
            this.rock = rock;
        }
    }
    
    static class BagComparator implements Comparator<Bag> {
        @Override
        public int compare(Bag a, Bag b){
            return (a.capacity - a.rock) - (b.capacity - b.rock);
        }
    }
}