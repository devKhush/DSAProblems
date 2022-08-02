class Solution {
    public int[] maximizeXor(int[] arr, int[][] queries) {
        int n = arr.length;
        int q = queries.length;

        Arrays.sort(arr);
        
        ArrayList<Integer[]> offlineQueries = new ArrayList<>();
        for (int i = 0; i < q; i++){
            int x = queries[i][0];
            int m = queries[i][1];

            offlineQueries.add(new Integer[]{x, m, i});
        }

        // offlineQueries.sort((a, b) -> a[1] - b[1]);
        offlineQueries.sort((a, b) -> a[1].compareTo(b[1]));

        Trie trie = new Trie();
        int[] XORs = new int[q];

        int index = 0;

        for (Integer[] query : offlineQueries){
            int x = query[0];
            int m = query[1];
            int i = query[2];

            while (index < n  && arr[index] <= m)
                trie.insert(arr[index++]);

            int XOR = -1;
            if (index != 0)
                XOR = trie.getMaximumXORWithNumber(x);
            
            XORs[i] = XOR;
        }
        return XORs;
    }
    
    public int[] maximizeXor_BruteForce(int[] arr, int[][] queries) {
        int q = queries.length;
        int[] XORs = new int[q];
        
        for (int i = 0; i < q; i++){
            int x = queries[i][0];
            int m = queries[i][1];
            int XOR = -1;
            
            for (int num : arr)
                if (num <= m)
                    XOR = Math.max(XOR, num ^ x);
            
            XORs[i] = XOR;
        }
        return XORs;
    }
}


class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(int num){
        TrieNode node = root;
        for (int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;

            if (!node.containsBit(bit))
                node.putBit(bit, new TrieNode());

            node = node.get(bit);
        }
    }


    public int getMaximumXORWithNumber(int num){
        TrieNode node = root;
        int XOR = 0;

        for (int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;

            if (node.containsBit(1 - bit)){
                XOR = (1 << i) | XOR;
                node = node.get(1 - bit);
            }
            else
                node = node.get(bit);
        }
        return XOR;
    }


    // *************************** TrieNode for Trie Data Structure *********************
    public static class TrieNode {
        private final TrieNode[] bitNodes;

        public TrieNode(){
            this.bitNodes = new TrieNode[2];
        }

        public boolean containsBit(int bit){
            return bitNodes[bit] != null;
        }

        public TrieNode get(int bit){
            return bitNodes[bit];
        }

        public void putBit(int bit, TrieNode node){
            bitNodes[bit] = node;
        }
    }
}
