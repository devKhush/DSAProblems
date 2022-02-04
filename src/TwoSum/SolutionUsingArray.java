package TwoSum;

public class SolutionUsingArray {

    public int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        for (int i=0; i< nums.length; i++){
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target){
                    index[0] =i;
                    index[1] =j;
                    break;
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {21,7,2,15};
        int target = 9;
        int[] index = new SolutionUsingArray().twoSum(arr,target);
        System.out.println(index[0]+" "+index[1]);
    }
}
