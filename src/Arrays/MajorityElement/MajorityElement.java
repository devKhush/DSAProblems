package Arrays.MajorityElement;

import Sorting.MergeSort.MergeSort;

import java.util.HashMap;

public class MajorityElement {

    public int majorityElement_BySorting(int[] arr) {
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

    public int majorityElement_ByHashMap(int[] arr) {
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int value : arr)
            count.put(value, count.getOrDefault(value, 0) + 1);

        int MajorElement = arr[0];
        int MajorCount = count.get(arr[0]);

        for (int value : count.keySet()){
            if (count.get(value) > MajorCount){
                MajorElement = value;
                MajorCount = count.get(value);
            }
        }

        return MajorElement;
    }

    public int majorityElement(int[] arr) {
        new MergeSort().sortArray(arr);
        return arr[arr.length/2];           // Think & Observe the logic behind this
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,4,6,5,4,3,4,5,3,2,3,4,5,2};
        System.out.println(new MajorityElement().majorityElement_BySorting(arr));
    }
}
