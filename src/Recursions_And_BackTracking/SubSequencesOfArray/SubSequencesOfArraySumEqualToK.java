package Recursions_And_BackTracking.SubSequencesOfArray;

import java.util.ArrayList;

// https://youtu.be/eQCS_v3bw0Q

public class SubSequencesOfArraySumEqualToK {

    private ArrayList<ArrayList<Integer>> answer;

    private void subSequencesSumEqualToK(int index, ArrayList<Integer> list, int[] arr, int k, int sum){
        if (index == arr.length){
            if (sum == k) {
                System.out.println(list);
                answer.add(new ArrayList<>(list));
            }
            return;
        }

        list.add(arr[index]);
        sum += arr[index];
        subSequencesSumEqualToK(index+1, list, arr, k, sum);
        sum -= arr[index];
        list.remove((Integer) arr[index]);

        subSequencesSumEqualToK(index+1, list, arr, k, sum);
    }

    public void getSubSequencesOfArraySumEqualToK(int[] arr, int k){
        answer = new ArrayList<>();
        subSequencesSumEqualToK(0, new ArrayList<>(), arr, k, 0);

        System.out.println();
        System.out.println(answer);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2};
        new SubSequencesOfArraySumEqualToK().getSubSequencesOfArraySumEqualToK(arr, 3);
    }
}
