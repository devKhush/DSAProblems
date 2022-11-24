package BinarySearchTree.RecoverBST;
import java.util.ArrayList;
import java.util.Comparator;

// https://youtu.be/ZWGW7FminDM

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
    public void recoverTree_BruteForce(TreeNode root) {
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


    /************************************* Efficient Solution *************************************
     * Intuition:
        * Single Inorder traversal is enough.
        * Since only two nodes are swapped, there will only be two discontinuities in the sorted values
            of inorder traversal.
        * So, our idea is to locate those two discontinuities in the inorder traversal, and swap them.

     * Time complexity: O(n)
        * O(n) due to inorder traversal.
     * Space Complexity: O(1) or O(Tree's Height)
        * O(H) for Recursion stack space OR O(1) for morris traversal
     */
    public void inorder(TreeNode root, TreeNode[] prev, TreeNode[] swapped){
        if (root == null)
            return;

        // Inorder left
        inorder(root.left, prev, swapped);

        // If there is a discontinuity in sorted values, store the discontinuities nodes. Since,
        // they are the swapped nodes. We will swap their values later.
        if (prev[0] != null && prev[0].val > root.val){
            if (swapped[0] == null){
                swapped[0] = prev[0];
                swapped[1] = root;
            }
            else
                swapped[1] = root;
        }

        // Inorder right
        prev[0] = root;     // Keep track of previous node
        inorder(root.right, prev, swapped);
    }

    public void recoverTree(TreeNode root) {
        // Array to store two swapped nodes
        TreeNode[] swapped = new TreeNode[]{null, null};

        // Call inorder
        inorder(root, new TreeNode[]{null}, swapped);

        // Re-swapped the two swapped nodes
        int temp = swapped[0].val;
        swapped[0].val = swapped[1].val;
        swapped[1].val = temp;
    }


    // Tree Node class
    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
