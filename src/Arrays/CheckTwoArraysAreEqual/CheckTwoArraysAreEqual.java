package Arrays.CheckTwoArraysAreEqual;

import java.util.HashMap;

class CheckTwoArraysAreEqual{
    //Function to check if two arrays are equal or not.

    public static boolean check(long A[],long B[],int N){
        HashMap<Long, Integer> map = new HashMap<>();
        
        for (int i=0; i<N; i++)
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);

        for (int i=0; i<N; i++)
            map.put(B[i], map.getOrDefault(B[i], 0) - 1);
        
        for (Long key : map.keySet()){
            if (map.get(key) != 0)
                return false;
        }
        return true;
    }
}