class Trie {
    private final TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }

    //Inserts a word into the trie
    public void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                node.put(ch, new TrieNode());

            node = node.get(ch);
        }
        node.setWordEnd();
    }

    //Returns if the word is in the trie
    public boolean search(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (!node.containsKey(ch))
                return false;
            
            node = node.get(ch);
        }
        return node.isWordEnd();
    }

    //Returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char ch : prefix.toCharArray()){
            if (!node.containsKey(ch))
                return false;

            node = node.get(ch);
        }
        return true;
    }
}


class TrieNode{
    private final TrieNode[] trieNodes;
    private boolean wordEnd;

    public TrieNode() {
        this.trieNodes = new TrieNode[26];
        this.wordEnd = false;
    }

    public boolean containsKey(char ch){
        return trieNodes[ch - 'a'] != null;
    }

    public void put(char ch, TrieNode node){
        trieNodes[ch - 'a'] = node;
    }

    public TrieNode get(char ch){
        return trieNodes[ch -'a'];
    }

    public boolean isWordEnd() {
        return wordEnd;
    }

    public void setWordEnd() {
        this.wordEnd = true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */