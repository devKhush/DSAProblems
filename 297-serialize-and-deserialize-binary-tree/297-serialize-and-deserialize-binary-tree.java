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
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            if (node != null){
                sb.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else
                sb.append(null + ",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] treeLevelOrder = data.split(",");

        if (treeLevelOrder[0].equals("null"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(treeLevelOrder[0]));

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            if (!treeLevelOrder[i].equals("null")){
                TreeNode leftChild = new TreeNode(Integer.parseInt(treeLevelOrder[i]));
                node.left = leftChild;
                queue.add(leftChild);
            }
            i++;
            
            if (!treeLevelOrder[i].equals("null")){
                TreeNode rightChild = new TreeNode(Integer.parseInt(treeLevelOrder[i]));
                node.right = rightChild;
                queue.add(rightChild);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));