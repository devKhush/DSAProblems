package Recursions_And_BackTracking.SubsetSum;
import java.util.ArrayList;

// https://practice.geeksforgeeks.org/problems/subset-sums2234/1
// https://youtu.be/rYkfBRtMJr8
//https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/

// Pre req ==> Sub Sequence of array (both versions)

// Difference between both versions is that :

// In Pick & Non-pick version, subsequence/subset is generated at the end of recursion call
// Or when index == array.length, only then sub-sequence are generated

// In second approach, subsequence/subset is generated at every step of recursion call
// Sub-sequence generated at every index of recursion call
// Advantage of this approach is that, with the help of this approach we can generate unique subsets


public class SubsetSum {

    // ************************************ Approach 1 **************************************
    // Same as solved in SubSequenceOfArray Folder
    private ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        ArrayList<Integer> answer = new ArrayList<>();

        getSubSequencesSums(0, 0, arr, answer);
        return answer;
    }

    private void getSubSequencesSums(int i, int sum, ArrayList<Integer> arr, ArrayList<Integer> answer){
        if (i == arr.size()){
            answer.add(sum);
            return;
        }

        //  Picking the current element
        getSubSequencesSums(i+1, sum + arr.get(i), arr, answer);

        //  Not-Picking the current element
        getSubSequencesSums(i+1, sum, arr, answer);
    }


    // ************************************ Approach 2 **************************************
    // Same as solved in SubSets_or_SubSequence_OfArray_AnotherApproach Folder

    private ArrayList<Integer> subsetSum(ArrayList<Integer> arr){
        ArrayList<Integer> subsetSums = new ArrayList<>();

        getSubSetSums(0, 0, arr, subsetSums);
        return subsetSums;
    }

    private void getSubSetSums(int index, int sum,  ArrayList<Integer> arr, ArrayList<Integer> answer) {
        answer.add(sum);

        for (int i = index; i < arr.size(); i++) {

            sum += arr.get(i);
            getSubSetSums(i + 1, sum, arr, answer);
            sum -= arr.get(i);
        }
    }

}
