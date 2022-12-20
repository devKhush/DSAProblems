package Recursions_And_BackTracking.SubSequencesOfArray;
import java.util.ArrayList;

public class PowerSet {
    public void powerSet(int[] arr){
        int n = arr.length;

        for (int i = 0; i < 1<<n; i++){
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = n-1; j >= 0; j--){
                if (((i >> j) & 1) == 1)
                    list.add(arr[n-1 - j]);
            }
            System.out.println(list);
        }
    }

    public static void main(String[] args){
        int[] arr = {3,1,2};

        PowerSet ps = new PowerSet();
        ps.powerSet(arr);
    }
}
