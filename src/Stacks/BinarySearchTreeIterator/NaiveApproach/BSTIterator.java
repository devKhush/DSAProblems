package Stacks.BinarySearchTreeIterator.NaiveApproach;
import Stacks.BinarySearchTreeIterator.TreeNode;
import java.util.ArrayList;

/*
* Time Complexity:
    * O(n) for Constructor   Due to in-order traversal of entire tree
    * O(1) for next()        ArrayList.get() takes O(1) time
    * O(1) for HasNext       ArrayList.size() takes O(1) time

 * Space Complexity: O(n)    We are maintaining in-order traversal of tree in arraylist
 */

public class BSTIterator {
    private ArrayList<Integer> traversal = new ArrayList<>();
    private int iterator;
    
    public void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            traversal.add(root.val);
            inOrder(root.right);
        }
    }

    public BSTIterator(TreeNode root) {
        inOrder(root);
        iterator = 0;
    }
    
    public int next() {
        return traversal.get(iterator++);
    }
    
    public boolean hasNext() {
        return iterator != traversal.size();
    }
}
