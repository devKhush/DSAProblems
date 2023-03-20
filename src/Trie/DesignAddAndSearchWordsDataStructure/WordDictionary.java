package Trie.DesignAddAndSearchWordsDataStructure;

// https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
/*
* DFS Traversal in Trie
 */

public class WordDictionary {
    private final Trie trie;
    public WordDictionary() {
        this.trie = new Trie();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    /** For Searching use DFS traversal in Trie **/
    public boolean search(String word) {
        return trie.dfs(word, 0, trie.getRoot());
    }
}


class Trie{
    private TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    public TrieNode getRoot(){
        return root;
    }

    /** Standard insert() function in Trie: takes O(n) time */
    public void insert(String str){
        TrieNode node = root;
        for (char ch : str.toCharArray()){
            if (node.get(ch) == null)
                node.put(ch);
            node = node.get(ch);
        }
        node.setEnd();
    }


    /** For searching we use DFS traversal in Trie */
    public boolean dfs(String str, int index, TrieNode node){
        for (int i = index; i < str.length(); i++){
            char ch = str.charAt(i);
            if (ch == '.'){
                for (char ptr = 'a'; ptr <= 'z'; ptr++){
                    if (node.get(ptr) != null){
                        if (dfs(str, i + 1, node.get(ptr)))
                            return true;
                    }
                }
                return false;
            }
            else {
                if (node.get(ch) == null)
                    return false;
                node = node.get(ch);
            }
        }
        return node.getEnd();
    }
}


class TrieNode{
    private final TrieNode[] arr;
    private boolean end;

    public TrieNode(){
        this.arr = new TrieNode[26];
        this.end = false;
    }

    public void setEnd(){
        end = true;
    }

    public boolean getEnd(){
        return end;
    }

    public void put(char ch){
        arr[ch - 'a'] = new TrieNode();
    }

    public TrieNode get(char ch){
        return arr[ch - 'a'];
    }
}
