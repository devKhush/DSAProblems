/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    // DFS *****************************************************************
    public Node connect(Node root) {
        if (root == null)
            return null;
        
        connect(root.left, root.right);
        return root;
    }
    
    public void connect(Node l1, Node l2){
        if (l1 == null || l2 == null)
            return;
        
        l1.next = l2;
        connect(l1.left, l1.right);
        connect(l2.left, l2.right);
        connect(l1.right, l2.left);
    }
    
    
    // BFS *****************************************************************
    public Node connect_BFS(Node root) {
        if (root == null)
            return null;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            Node tail = null;
            
            for (int i = 0; i < size; i++) {
                Node node = queue.remove();

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);

                if (tail != null)
                    tail.next = node;
                tail = node;
            }
        }
        return root;
    }
}