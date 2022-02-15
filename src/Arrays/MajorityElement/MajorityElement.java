package Arrays.MajorityElement;

import Sorting.MergeSort.MergeSort;

public class MajorityElement {

    public int majorityElement(int[] arr) {
        new MergeSort().sortArray(arr);

        int majorElement = arr[0];
        int count = 1;

        int currCount = 1;
        for (int i=1; i<arr.length; i++){
            if (arr[i]==arr[i-1]){
                currCount++;
                if (currCount>count){
                    majorElement = arr[i];
                    count = currCount;
                }
            }
            else
                currCount = 1;
        }
        return majorElement;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,4,6,5,4,3,4,5,3,2,3,4,5,2};
        System.out.println(new MajorityElement().majorityElement(arr));
    }
}
