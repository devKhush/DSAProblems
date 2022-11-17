package BinarySearchTree.RecoverBST;
import java.util.ArrayList;
import java.util.Comparator;

public class RecoverBST {
    /***************************************** Brute Force ****************************************
     * Intuition:
        * Do an Inorder traversal (or any other traversal) of BST and store it.
        * Sort the traversal, here we obtain correct inorder traversal of BST.
        * Since, the structure of BST has to be same.
        * So, the change/update those BST node which are not same as in order traversal.

     * Time complexity: O(n) + O(n) + O(n * log(n))  ~  O(n * log(n))
        * O(n) due to first inorder traversal.
        * O(n * log(n)) to sort the traversal to obtain the correct inorder traversal of BST.
        * Another O(n) to update those BST node which are not same as in order traversal

     * Space complexity: O(n) + O(Tree's Height)  ~  O(n)
        * O(n) for inorder traversal list
        * Recursion stack space
     */
    public void recoverTree(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        // Do any traversal of incorrect BST
        inorder(root, list, false, new int[]{-1});

        // Sort the traversal to obtain correct Inorder-traversal of BST
        // list.sort((a,b) -> a-b);
        list.sort(Comparator.comparingInt(a -> a));

        // update those BST node which are not same as in order traversal
        inorder(root, list, true, new int[]{0});
    }

    private void inorder(TreeNode root, ArrayList<Integer> list, boolean changeTree, int[] index){
        if (root == null)
            return;

        // Inorder left
        inorder(root.left, list, changeTree, index);

        // update those BST node which are not same as in order traversal
        if (changeTree){
            if (list.get(index[0]) != root.val)
                root.val = list.get(index[0]);
            index[0]++;
        }
        // If BST is not be change, simply store the node's value for traversal
        else
            list.add(root.val);

        // Inorder right
        inorder(root.right, list, changeTree, index);
    }

    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
