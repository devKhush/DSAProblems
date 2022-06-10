package Hashing.XORQueriesOfSubarray;
import java.util.HashMap;

// Pre-requisite: Prefix Sum array & Hashing
// Some Hints ?
// 1) What is the result of x ^ y ^ x ?
// 2) Compute the prefix sum for XOR
// 3) Process the queries with the prefix sum values

class XORQueriesOfSubarray {

    // *********************************** Brute Force ***********************************
    // Simple logic as seen by looking into the solution
    // TC -> O(q * average_each_query_length_in_array)        where q = queries length
    // SC -> O(1)                                             (Ignoring output array size)

    public int[] xorQueries_BruteForce(int[] arr, int[][] queries) {
        int [] allXORs = new int[queries.length];

        for (int i = 0; i < queries.length; i++){
            int queryStartIndex = queries[i][0];
            int queryEndIndex = queries[i][1];

            // Taken XOR as 0 bcoz X^0 = 1^X + 0^(~X) = X
            int xor = 0;
            while (queryStartIndex <= queryEndIndex)
                xor = xor ^ arr[queryStartIndex++];

            allXORs[i] = xor;
        }
        return allXORs;
    }



    // *********************************** Efficient Hashing Solution **********************************
    // TC -> O(n + q)   q = queries length
    // SC -> O(n)       n = array length

    public int[] xorQueries_HashMap(int[] arr, int[][] queries) {

        int [] allXORs = new int[queries.length];
        HashMap<Integer, Integer> prefixXORMap = new HashMap<>();

        // Taken XOR as 0 bcoz X^0 = 1^X + 0^(~X) = X
        int xor = 0;

        // Compute the prefix sum for XOR  with key as "index" & values as "Prefix XOR"
        for (int i = 0; i < arr.length; i++){
            xor = xor ^ arr[i];
            prefixXORMap.put(i, xor);
        }

        // Process the queries with the prefix sum values
        for (int i = 0; i < queries.length; i++){
            int queryStartIndex = queries[i][0];
            int queryEndIndex = queries[i][1];

            int startXor = queryStartIndex - 1 >= 0 ? prefixXORMap.get(queryStartIndex - 1) : 0;
            int endXor = prefixXORMap.get(queryEndIndex);

            // We can do this because the result of "x ^ y ^ x"  is "y"
            //  "x ^ y ^ x"  =  "x ^ x ^ y"  =  "0 ^ y"  =  "y"
            allXORs[i] = endXor ^ startXor;
        }

        return allXORs;
    }



    // ******************************* Efficient Array Hashing Solution ******************************
    // TC -> O(n + q)   q = queries length
    // SC -> O(n)       n = array length

    public int[] xorQueries_Prefix_XOR_Array(int[] arr, int[][] queries) {
        int[] allXORs = new int[queries.length];

        // As in this ques we are using "key" as the "indices", so instead we can use Array
        // here for Prefix XOR to get "prefix XOR value"
        int[] prefixXOR = new int[arr.length];

        // Taken XOR as 0 bcoz: X^0 = 1^X + 0^(~X) = X
        int xor = 0;

        // Compute the prefix sum for XOR  with key as "index" & values as "Prefix XOR"
        for (int i = 0; i < arr.length; i++){
            xor = xor ^ arr[i];    
            prefixXOR[i] = xor;
        }

        // Process the queries with the prefix sum values
        for (int i = 0; i < queries.length; i++){
            int queryStartIndex = queries[i][0];
            int queryEndIndex = queries[i][1];
            
            int startXor = queryStartIndex - 1 >= 0 ? prefixXOR[queryStartIndex - 1] : 0;
            int endXor = prefixXOR[queryEndIndex];

            // We can do this because the result of "x ^ y ^ x"  is "y"
            //  "x ^ y ^ x"  =  "x ^ x ^ y"  =  "0 ^ y"  =  "y"
            allXORs[i] = endXor ^ startXor;
        }
        
        return allXORs;
    }

}