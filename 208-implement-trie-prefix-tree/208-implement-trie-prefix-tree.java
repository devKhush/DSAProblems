class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.children[c - 'a'] == null) {
                temp.children[c - 'a'] = new Node();
            }
            temp = temp.children[c - 'a'];
        }
        temp.isEnd = true;
    }
    
    public boolean search(String word) {
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.children[c - 'a'] == null) {
                return false;
            }
            temp = temp.children[c - 'a'];
        }
        return temp.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        Node temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (temp.children[c - 'a'] == null) {
                return false;
            }
            temp = temp.children[c - 'a'];
        }
        return true;
    }
    
    class Node {
        Node[] children;
        boolean isEnd;
        
        public Node() {
            children = new Node[26];
        }
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */