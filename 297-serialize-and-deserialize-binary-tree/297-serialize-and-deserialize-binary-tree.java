/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Serialize **************************************************************************
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();

        preorder(root, sb);

        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null){
            sb.append("null,");
            return;
        }
        sb.append(node.val + ",");
        preorder(node.left, sb);                // Recursion for Left Subtree
        preorder(node.right, sb);               // Recursion for Right Subtree
    }


    // Deserialize **************************************************************************
    public TreeNode deserialize(String data) {
        if (data.equals(""))        // Edge case
            return null;
        String[] preOrder = data.split(",");

        Queue<Integer> queue = new LinkedList<>();
        for (String node : preOrder){
            if (node.equals("null"))
                queue.add(null);
            else
                queue.add(Integer.parseInt(node));
        }

        TreeNode root = preOrder(queue);
        return root;
    }

    private TreeNode preOrder(Queue<Integer> preOrderQueue){
        Integer value = preOrderQueue.remove();
        if (value == null)
            return null;

        TreeNode node = new TreeNode(value);

        node.left = preOrder(preOrderQueue);
        node.right = preOrder(preOrderQueue);
        return node;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));