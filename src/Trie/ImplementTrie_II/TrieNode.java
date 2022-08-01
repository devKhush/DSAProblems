package Trie.ImplementTrie_II;

public class TrieNode {
    private final TrieNode[] trieNodes;
    private int prefixCount;
    private int countOfEndWord;

    public TrieNode() {
        this.trieNodes = new TrieNode[26];
        prefixCount = 0;
        countOfEndWord = 0;
    }

    public boolean containsKey(char ch){
        return trieNodes[ch - 'a'] != null;
    }

    public void put(char ch, TrieNode node){
        trieNodes[ch - 'a'] = node;
    }

    public TrieNode get(char ch){
        return trieNodes[ch - 'a'];
    }

    public void incrementPrefixCount(){
        prefixCount++;
    }

    public void incrementCountOfEndWord(){
        countOfEndWord++;
    }

    public void decrementPrefixCount(){
        prefixCount--;
    }

    public void decrementCountOfEndWord(){
        countOfEndWord--;
    }

    public int getPrefixCount() {
        return prefixCount;
    }

    public int getCountOfEndWord() {
        return countOfEndWord;
    }
}
