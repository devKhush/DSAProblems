package Recursions_And_BackTracking.SubSequencesOfArray;

import java.util.ArrayList;

// https://youtu.be/eQCS_v3bw0Q

public class AnyOneSubSequencesOfArraySumEqualToK {

    private boolean anyOneSubSequencesOfArraySumEqualToK(int[] arr, ArrayList<Integer> list,  int index, int k, int sum){

        // LOGIC is that when we got one required subsequence, we print it and returns the true
        // We avoid future recursion calls, if we got anyone subsequence
        if (index == arr.length){
            if (k==sum){
                System.out.println(list);
                return true;
            }
            else return false;
        }

        // if by selecting that element (below func call), we got that subsequence we return true
        list.add(arr[index]);
        sum += arr[index];
        if (anyOneSubSequencesOfArraySumEqualToK(arr, list, index+1, k, sum))
            return true;
        sum -= arr[index];
        list.remove((Integer) arr[index]);

        // if by not selecting that element (below func call), we return whether we got that
        // subsequence or not
        return anyOneSubSequencesOfArraySumEqualToK(arr, list, index+1, k, sum);
    }

    private void getAnyOneSubSequencesOfArraySumEqualToK(int[] arr, int k){
        anyOneSubSequencesOfArraySumEqualToK(arr, new ArrayList<Integer>(), 0, k, 0);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2};

        new AnyOneSubSequencesOfArraySumEqualToK().getAnyOneSubSequencesOfArraySumEqualToK(arr, 5);
    }
}
