package BinaryTree.SerializeAndDeserializeBinaryTree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree_DFS {
    /************************************* Serialize ********************************************
     * Technique used: Pre-Order DFS Traversal
     * To recover the Tree entirely, we will also add the 'null' nodes in Pre-order Traversal of Tree

     * Time Complexity: O(n)                   DFS takes O(n) time
     * Space Complexity: O(Tree's Height)      Recursion Stack Space
     */
    public String serialize(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();

        // Pre-order Traversal from root to convert all the Nodes to a String
        preorder(root, list);

        // Join the preorder traversal
        return String.join(",", list);
    }

    private void preorder(TreeNode root, ArrayList<String> list){
        if (root == null){
            list.add("null");  // To recover the Tree entirely, we will also add the 'null' nodes in Pre-order Traversal
            return;
        }
        list.add(Integer.toString(root.val));
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /************************************* Deserialize ********************************************
     * Technique used: Pre-Order DFS Traversal
     * We will Traverse the given Pre-order Traversal, in the Pre-order Traversal itself to create the
     new Binary Tree

     * Time Complexity: O(n)                   DFS takes O(n) time
     * Space Complexity: O(Tree's Height)      Recursion Stack Space
     */
    public TreeNode deserialize(String data) {
        // Preorder traversal
        String[] preorderTraversal = data.split(",");

        // We will Traverse the given Pre-order Traversal, to create the new Binary Tree
        return preorder(preorderTraversal, new int[]{0});
    }
    private TreeNode preorder(String[] traversal, int[] ind){
        if (traversal[ind[0]].equals("null")){
            ind[0]++;
            return null;
        }
        // Initialise the current node in the order of Pre-order traversal
        TreeNode root = new TreeNode(Integer.parseInt(traversal[ind[0]]));
        ind[0]++;

        root.left = preorder(traversal, ind);
        root.right = preorder(traversal, ind);
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
 * We can also use In-order Traversal OR Post-Order Traversal for this Question

 * Conclusion:
    * We can construct a Binary Tree using just one Traversal (be it Level order, Pre-order,
        Post-order, In-order), iff we get information about all the Nodes as well as their 'null'
        Child nodes (if any) also.
 * In this way, a Tree can be constructed without any confusion
 */
