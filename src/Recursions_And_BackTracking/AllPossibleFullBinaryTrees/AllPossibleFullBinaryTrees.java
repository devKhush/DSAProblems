package Recursions_And_BackTracking.AllPossibleFullBinaryTrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://www.youtube.com/watch?v=VYczyMiMTqA
// https://www.youtube.com/watch?v=nZtrZPTTCAo

class AllPossibleFullBinaryTrees {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public List<TreeNode> allPossibleFBT_RecursionOnly(int n) {
        ArrayList<TreeNode> answer = new ArrayList<>();
        
        if (n==1)
            answer.add(new TreeNode());
        
        else{
            for (int i=1; i<n; i+=2){
                List<TreeNode> leftTrees = allPossibleFBT_RecursionWithDP(i);
                List<TreeNode> rightTrees = allPossibleFBT_RecursionWithDP(n - i -1);
    
                for (TreeNode leftTree : leftTrees){
                    for (TreeNode rightTree : rightTrees){
                        TreeNode root = new TreeNode(0, leftTree, rightTree); 
                        answer.add(root);
                    }
                }
            }
        }
        return answer;
    }
    


    private HashMap<Integer, List<TreeNode>> allFullBinaryTrees = new HashMap<>();

    public List<TreeNode> allPossibleFBT_RecursionWithDP(int n) {
        ArrayList<TreeNode> answer = new ArrayList<>();
        
        if (allFullBinaryTrees.containsKey(n))
            return allFullBinaryTrees.get(n);
        
        else if (n==1)
            answer.add(new TreeNode());
        
        else{
            for (int i=1; i<n; i+=2){
                List<TreeNode> leftTrees = allPossibleFBT_RecursionWithDP(i);
                List<TreeNode> rightTrees = allPossibleFBT_RecursionWithDP(n - i -1);
    
                for (TreeNode leftTree : leftTrees){
                    for (TreeNode rightTree : rightTrees){
                        TreeNode root = new TreeNode(0, leftTree, rightTree); 
                        answer.add(root);
                    }
                }
            }
        }
        
        allFullBinaryTrees.put(n, answer);
        
        return answer;
    }
}