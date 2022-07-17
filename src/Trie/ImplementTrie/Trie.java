package Trie.ImplementTrie;

// https://youtu.be/dBGUmUQhjaM
// https://takeuforward.org/data-structure/implement-trie-1/
// https://www.geeksforgeeks.org/trie-insert-and-search/

public class Trie {
    private final TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if (!node.containsKey(ch))
                node.put(ch, new TrieNode());

            node = node.get(ch);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if (!node.containsKey(ch))
                return false;
            node = node.get(ch);
        }
        return node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);

            if (!node.containsKey(ch))
                return false;
            node = node.get(ch);
        }
        return true;
    }
}


class TrieNode{
    private final TrieNode[] trieNodes;
    private boolean end;

    public TrieNode(){
        this.trieNodes = new TrieNode[26];
        this.end = false;
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

    public void setEnd(){
        end = true;
    }

    public boolean isEnd(){
        return end;
    }
}