package BinaryHeaps.MergeKSortedArrays;
import java.util.ArrayList;

public class MergeKSortedArrays_DivideAndConquer {
    /***************************************** Divide and Conquer Solution *******************************
     * Time Complexity: O(m * log(n))
        * n -> length of k_Arrays
        * m -> Average size of each arrays
     * Space Complexity: O(m) + O(log(n))
        * O(m) for temporary arrays
     *
     */
    public static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> kArrays, int k){
        return dnc(0, kArrays.size()-1, kArrays);
    }
    private static ArrayList<Integer> dnc(int low, int high, ArrayList<ArrayList<Integer>> kArrays){
        if (low == high)
            return kArrays.get(low);

        int mid = (low + high)/2;
        return merge(dnc(low, mid, kArrays), dnc(mid+1, high, kArrays));
    }
    public static ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b){
        int m = a.size(), n = b.size();
        ArrayList<Integer> arr = new ArrayList<>();
        int i = 0, j = 0;

        while (i < m || j < n){
            int val1 = i < m ? a.get(i) : Integer.MAX_VALUE;
            int val2 = j < n ? b.get(j) : Integer.MAX_VALUE;
            if (val1 <= val2){
                arr.add(val1);
                i++;
            }
            else{
                arr.add(val2);
                j++;
            }
        }
        return arr;
    }
}
