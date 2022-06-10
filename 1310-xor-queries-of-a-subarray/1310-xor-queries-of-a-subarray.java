class Solution {
    
    // Efficient Hashing Solution **********************************************************
    public int[] xorQueries(int[] arr, int[][] queries) {
        int [] allXORs = new int[queries.length];
        
        HashMap<Integer, Integer> xorMap = new HashMap<>();
        
        int xor = 0;
        
        for (int i = 0; i < arr.length; i++){
            xor = xor ^ arr[i];    
            xorMap.put(i, xor);
        }
        
        for (int i = 0; i < queries.length; i++){
            int queryStartIndex = queries[i][0];
            int queryEndIndex = queries[i][1];
            
            int startXor = queryStartIndex - 1 >= 0 ? xorMap.get(queryStartIndex - 1) : 0;
            int endXor = xorMap.get(queryEndIndex);
            
            allXORs[i] = endXor ^ startXor;
        }
        
        return allXORs;
    }
    
    
    // Brute Force **********************************************************************
    public int[] xorQueries_BruteForce(int[] arr, int[][] queries) {
        int [] allXORs = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++){
            int queryStartIndex = queries[i][0];
            int queryEndIndex = queries[i][1];
            
            int xor = 0;
            while (queryStartIndex <= queryEndIndex)
                xor = xor ^ arr[queryStartIndex++];
            
            allXORs[i] = xor;
        }
        return allXORs;
    }
}