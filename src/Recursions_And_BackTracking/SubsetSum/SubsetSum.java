package Recursions_And_BackTracking.SubsetSum;
import java.util.ArrayList;

// https://practice.geeksforgeeks.org/problems/subset-sums2234/1#
// Pre req ==> Sub Sequence of array

public class SubsetSum {

    public ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        ArrayList<Integer> answer = new ArrayList<>();

        getSubSequences(0, 0, arr, answer);

        return answer;
    }

    public void getSubSequences(int i, int sum, ArrayList<Integer> arr, ArrayList<Integer> answer){
        if (i == arr.size()){
            answer.add(sum);
            return;
        }

        getSubSequences(i+1, sum + arr.get(i), arr, answer);

        getSubSequences(i+1, sum, arr, answer);
    }
}
