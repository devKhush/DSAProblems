package Trie.ImplementTrie_I;

class TrieNode{
    // Each TrieNode in array represents a possible alphabetical character
    private final TrieNode[] trieNodes;

    // "wordEnd" is true if the node represents end of a word
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
