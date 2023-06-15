package BinarySearchTree.BuildBSTFromTraversal.BuildBSTFromPreorder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

// https://youtu.be/UmJT3j26t1I

public class BSTFromPreOrderTraversal {
    /************************ Brute Force: Using Normal BST insert function **************************
     * Intuition: Add all to the BST using the insert node function of BST.

     * Time Complexity: O(n * n)
        * All the 'n' nodes needs to be inserted.
        * For each node to be inserted, tree can be skewed. So, insertion can take O(n) time.
        * Hence, total time complexity is O(n^2)
     * Space Complexity: O(n) or O(1)
        * Depending on type of insert function used for BST_insert(). (iterative or recursive).
     */
    private TreeNode insertBST(TreeNode root, int value){
        if (root == null)
            return new TreeNode(value);

        if (value < root.val)
            root.left = insertBST(root.left, value);
        if (root.val < value)
            root.right = insertBST(root.right, value);
        return root;
    }
    public TreeNode constructBST_1(int[] preorder){
        TreeNode root = null;
        for (int node : preorder)
            root = insertBST(root, node);

        return root;
    }


    /************************* Construct Tree From Preorder & Inorder Traversal *********************
     * Intuition: Inorder traversal of BST is always sorted.
        * Sort the preorder array to obtain inorder traversal of BST.
        * Now, construct Tree from Preorder & Inorder traversal

     * Time Complexity: O(n * log(n)) + O(n)
        * O(n * log(n)) to sort the preorder array to obtain inorder array
        * O(n) to construct the Tree with 'n' nodes by Divide n Conquer
     * Space Complexity: O(n) + O(n) + O(n)
        * O(n) for Inorder traversal array.
        * O(n) for HasMap to store the indices of nodes in InOrder traversal
        * Another O(n) for Recursion stack space, in worst case of Skewed Trees
     */
    public TreeNode constructBST_preorder(int[] preorder){
        int[] inorder = new int[preorder.length];
        System.arraycopy(preorder, 0, inorder, 0, preorder.length);
        Arrays.sort(inorder);

        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);

        return constructTree(preorder, 0, preorder.length-1,
                              inorder,  0,  inorder.length-1,  inorderMap);
    }
    private TreeNode constructTree(int[] preorder, int preStart, int preEnd,
                                   int[] inorder, int inStart, int inEnd,
                                   HashMap<Integer, Integer> inorderMap){
        if (preEnd < preStart)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inorderMap.get(preorder[preStart]);

        root.left = constructTree(preorder, preStart + 1, preStart + (rootIndex - inStart),
                                  inorder, inStart, rootIndex -1, inorderMap);
        root.right = constructTree(preorder, preStart + (rootIndex - inStart) + 1, preEnd,
                                   inorder, rootIndex + 1, inEnd, inorderMap);
        return root;
    }


    /************************************ Efficient Solution 1 ************************************
     * Intuition:
        * Preorder traversal -> root left right
        * At each node of preorder traversal, there can exist a Left subtree and Right subtree.
        * We don't know the exact no. of nodes in Left & Right subtree. But we can identify whether
            that node falls under left subtree or right subtree based on BST property.
        * We set an upper and lower bound for each node, though lower bound is not needed due to
            BST property.
        * All the left subtrees nodes should be lesser than the upper bound value.
            Though the upper bound is current node value.
        * All the right subtrees nodes also should be lesser than the upper bound value.
            Though the upper bound is the upper bound of current node value.

     * Time complexity: O(3n)  ~  O(n)
        * Each node will be max visited 3 times.
     * Space complexity: O(n)
        * Recursion stack space in worst case.
     */
    public TreeNode bstFromPreorder(int[] preorder){
        return constructBST(preorder, Integer.MAX_VALUE, new int[]{0});
    }
    private TreeNode constructBST(int[] preorder, int upperBound, int[] index){
        if (index[0] == preorder.length  ||  preorder[index[0]] >= upperBound)
            return null;

        TreeNode root = new TreeNode(preorder[index[0]++]);

        root.left = constructBST(preorder, root.val, index);
        root.right = constructBST(preorder, upperBound, index);
        return root;
    }

    /************************************ Efficient Solution 2 ************************************
     * Intuition:
     * Preorder traversal -> root left right
     * At each node of preorder traversal, there can exist a Left subtree and Right subtree.
     * We don't know the exact no. of nodes in Left & Right subtree. But we can identify whether
        that node falls under left subtree or right subtree based on BST property.
     * Since after a node, comes left subtree (all less values than node) and  right subtree (all
        greater values than node)
     * So, we can use Next Greater Element concept to find the index where
        left subtree and right subtree starts

     * Time complexity: O(3n)  ~  O(n)
        * NGE takes O(2n) time
        * Building tree takes O(n) time
     * Space complexity: O(n)
        * O(n) for NGE Array
        * O(n) recursion stack space in worst case.
     */
    public TreeNode bstFromPreorder_v2(int[] preorder) {
        int n = preorder.length;

        // Construct the Next Greater Element (NGE) array to find the range of left and right subtree
        int[] nextGreater = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i >= 0; i--){
            while (!stack.isEmpty()  &&  preorder[i] >= preorder[stack.peek()])
                stack.pop();
            nextGreater[i] = !stack.isEmpty() ? stack.peek() : n;
            stack.push(i);
        }
        // Build Tree
        return dfs(0, n-1, preorder, nextGreater);
    }

    private TreeNode dfs(int low, int high, int[] preorder, int[] nextGreater){
        if (low > high)
            return null;
        TreeNode root = new TreeNode(preorder[low]);

        // nextGreater[low] is the position where Right Subtree starts, (bcoz greater value than node)
        root.left = dfs(low + 1, nextGreater[low] - 1, preorder, nextGreater);
        root.right = dfs(nextGreater[low], high, preorder, nextGreater);
        return root;
    }


    private static class TreeNode{
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
