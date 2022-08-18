package BinaryTree.CheckChildrenSumEqualParent;

// https://www.geeksforgeeks.org/check-for-children-sum-property-in-a-binary-tree/

public class CheckChildrenSumEqualParent {
    /**************************************** DFS Solution ********************************************
     * Time Complexity: O(n)
     * Space Complexity: O(Tree's Height)
     */
    public boolean isSumProperty(Node root){
        // If root is null, we consider the children sum property to be true
        if (root == null)
            return true;

        // Single node with no child also satisfies the children sum property
        if (root.left == null && root.right == null)
            return true;

        // Values of Left & Right child, initialized to 0 in case any of them is null
        int left = 0, right = 0;

        // Value of Left child
        if (root.left != null)
            left = root.left.data;

        // Value of Right child
        if (root.right != null)
            right = root.right.data;

        // Check for the Children sum property
        if (root.data != left + right)
            return false;

        // Left SubTree should also satisfy the Children sum property
        if (!isSumProperty(root.left))
            return false;

        // Right SubTree should also satisfy the Children sum property
        if (!isSumProperty(root.right))
            return false;

        // If no condition is violated, Tree satisfies the Children sum property
        return true;
    }


    static private class Node{
        int data;
        Node left,right;
        Node(int key) {
            data = key;
        }
    }
}
