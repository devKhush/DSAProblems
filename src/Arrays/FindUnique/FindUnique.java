package Arrays.FindUnique;
import java.util.HashMap;
import java.util.Arrays;

//https://www.codingninjas.com/codestudio/problems/find-unique_625159?utm_source=sendgrid&utm_medium=email&utm_campaign=website&leftPanelTab=0

public class FindUnique {

    // Simple Logic SOlution
    // TC -> O(n)
    // SC -> O(n)
    public static int findUnique_HashMap(int[] arr){
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int val : arr)
            count.put(val, count.getOrDefault(val, 0) + 1);

        for (int val : count.keySet())
            if (count.get(val) == 1)
                return val;
        return -1;
    }


    // Based on Single Element in Sorted Array Question (See sorting)
    // TC -> O(n * log(n)) + O(log(n))
    // SC -> O(1)  Assume Inbuilt sort
    public static int findUnique_BinarySearch(int[] arr){
        Arrays.sort(arr);

        int low = 0, high = arr.length -1;

        if (high==0) return arr[0];
        if (arr[low] != arr[low + 1])
            return arr[0];
        if (arr[high] != arr[high - 1])
            return arr[high];

        while (low <= high){
            int mid = (low + high) / 2;

            if (arr[mid] != arr[mid -1]  && arr[mid] != arr[mid  + 1])
                return arr[mid];

            if (arr[mid] == arr[mid + 1]){
                if (mid % 2 == 0)
                    low = mid  + 1;
                else
                    high = mid - 1;
            }

            else if (arr[mid] == arr[mid -1]){
                if (mid %2 ==1)
                    low = mid + 1;
                else
                    high = mid -1;
            }
        }
        return -1;
    }
}
