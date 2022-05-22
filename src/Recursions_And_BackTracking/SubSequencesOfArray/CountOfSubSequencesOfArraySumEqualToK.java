package Recursions_And_BackTracking.SubSequencesOfArray;

// https://youtu.be/eQCS_v3bw0Q

public class CountOfSubSequencesOfArraySumEqualToK {

    // One approach can be keeping a global variable and incrementing it whenever required
    // subsequence is found
    // No need of ArrayList here

    // T.C --> O(2^n)
    // S.C --> O(n)

    private int countOfSubSequencesOfArraySumEqualToK(int[] arr, int index, int k, int sum){
        if (index == arr.length){
            if (sum == k) return 1;
            else return 0;
        }

        sum += arr[index];
        int leftCount = countOfSubSequencesOfArraySumEqualToK(arr, index+1, k, sum);
        sum -= arr[index];

        int rightCount =  countOfSubSequencesOfArraySumEqualToK(arr, index+1, k, sum);

        return leftCount + rightCount;
    }

    private void getCountOfSubSequencesOfArraySumEqualToK(int[] arr, int k){
        int count  = countOfSubSequencesOfArraySumEqualToK(arr, 0, k, 0);
        System.out.println(count);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};

        new CountOfSubSequencesOfArraySumEqualToK().getCountOfSubSequencesOfArraySumEqualToK(arr, 2);
    }
}
