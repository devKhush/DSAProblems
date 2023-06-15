package BinaryTree.SerializeAndDeserializeBinaryTree;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// https://youtu.be/-YbXySKJsX8
// https://takeuforward.org/data-structure/serialize-and-deserialize-a-binary-tree/

public class SerializeAndDeserializeBinaryTree_BFS {
    /************************************* Serialize ********************************************
     * Technique used: BFS Traversal
     * To recover the Tree entirely, we will also add the 'null' nodes in BFS Traversal of Tree

     * Time Complexity: O(n)                BFS takes O(n) time
     * Space Complexity: O(n/2) ~ O(n)      BFS Queue size can be n/2 in last level
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Do a BFS (a little modified BFS)
        // To recover the Tree entirely, we will also add the 'null' nodes in BFS Traversal of Tree
        // This is done to identify the Missing child nodes of Parent node, so that they can be converted
        // to Binary Tree easily
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            if (node == null)
                list.add("null");
            else{
                list.add(Integer.toString(node.val));
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return String.join(",", list);
    }


    /************************************* Deserialize ********************************************
     * Technique used: BFS Traversal
     * We will Traverse the given Level order Traversal, in the Level order Traversal itself to create the
        new Binary Tree

     * Time Complexity: O(n)                BFS takes O(n) time
     * Space Complexity: O(n/2) ~ O(n)      BFS Queue size can be n/2 in last level
     */
    // Decodes your Encoded String data to a new Binary Tree.
    public TreeNode deserialize(String data) {
        String[] bfsTraversal = data.split(",");
        if (bfsTraversal[0].equals("null"))
            return null;

        // Index to traverse in Level Order Traversal
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(bfsTraversal[0]));

        // BFS: Traverse all the Nodes one by one in Level Order Traversal array
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            // Current node will be the Left Child of node
            // Add the Left Child for current node
            index++;
            if (!bfsTraversal[index].equals("null")){
                node.left = new TreeNode(Integer.parseInt(bfsTraversal[index]));
                queue.add(node.left);
            }
            // Current node will be the Right Child of node
            // Add the Right Child for current node
            index++;
            if (!bfsTraversal[index].equals("null")){
                node.right = new TreeNode(Integer.parseInt(bfsTraversal[index]));
                queue.add(node.right);
            }
        }
        return root;
    }


    // TreeNode
    private static class TreeNode{
        int val;
        TreeNode left, right;
        public TreeNode(int val){
            this.val = val;
        }
    }
}

/***
 * Conclusion:
    * We can construct a Binary Tree using just one Traversal (be it Level order, Pre-order,
        Post-order, In-order), iff we get information about all the Nodes as well as their 'null'
        Child nodes (if any) also.
    * In this way, a Tree can be constructed without any confusion

 * Eg;, consider level order traversal "1, 2, 3, 4, 5, null, 6, null, null, null, null, null, null"
 * Using this Level Order Traversal, a Binary Tree can be constructed easily.
 */