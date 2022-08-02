class Solution {
    public int findMaximumXOR(int[] arr) {
        Trie trie = new Trie();
        
        for (int number : arr)
            trie.insert(number);
        
        int max_XOR = 0;
        
        for (int number : arr){
            int XOR = trie.maximumXORWithNumber(number);
            max_XOR = Math.max(max_XOR, XOR);
        }
        return max_XOR;
    }
    
    
    public int findMaximumXOR_BruteForce(int[] nums) {
        int XOR = 0;
        
        for (int a : nums)
            for (int b : nums)
                XOR = Math.max(XOR, a ^ b);
        return XOR;
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

    
    public int maximumXORWithNumber(int num){
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
