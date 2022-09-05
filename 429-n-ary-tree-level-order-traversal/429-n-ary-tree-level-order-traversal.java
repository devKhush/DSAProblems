/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null)
            return levels;
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            
            ArrayList<Integer> level = new ArrayList<>();
            levels.add(level);
            
            for (int i = 0; i < size; i++){
                Node node = queue.remove();
                level.add(node.val);
                
                for (Node child : node.children)
                    queue.add(child);
            }
        }
        return levels;
    }
}