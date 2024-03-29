package BinaryTree.MinimumTimeTakenToBurnBinaryTreeFromaNode;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

// PRE_REQUISITE: "Print All The Nodes At Distance Of K"
// Same as BFS Solution, just Marking the Parent nodes via DFS
// https://youtu.be/2r5wLmQfD6g
// https://www.codingninjas.com/codestudio/problems/time-to-burn-tree_630563

public class MinimumTimeToBurnBinaryTree_DFS {
    /**************************************** DFS Solution ***********************************************
     * Intuition:
        * We need to brun all the nodes starting from the given node, where burning starts.
        * Adjacent nodes that are to be burned are also present above the burned Node, while some nodes will
            be present below the burned node.
        * But in Tree, we can't travel from Child to Parent, but only from Parent to child
        * So, we will convert our Tree to an Undirected Graph, due to this from a Node we can travel
            to its Child Node (below the Tree) as well as to its Parent Node (above the Tree)
        * After this we can do a Simple BFS to Traverse/Burn all the nodes in TREE, starting
            from Burned start node.

     * Steps/Approach:
        * Convert the Tree to an Undirected Graph
        * This can be done by storing the Address of Parent Node of each Child Node (in some Data Structure)
        * A HashMap can be used to point a Node to its Parent Node
        * Now each node will have Three Neighbours: Left child, Right child, Parent Node
        * Observe that in a binary tree a node can be connected to maximum 3 other nodes i.e.
            left child, right child, and the parent.
        * We have left and right pointers, and we have also stored the parent node.
        * Now, we from a Node we can travel to its Child node (via Tree left & right nodes) and Parent Node
            as well (via Parent HashMap)
        * Then do a Simple BFS Traversal starting from the 'Burned' Node, and reach all the Tree Nodes (breadth wise).
        * Think this step as the simple Graph Traversal

     * Time Complexity : O(n) + O(n) = O(n)
        * We use BFS Traversal to mark the Parent node of all the Nodes in the Tree, this takes O(n) time
        * We use BFS Traversal to Burn all the node starting from the given node
     * Space Complexity: O(n) + O(n) + O(n) = O(n)
        * O(n) Space required for HashMap, to store the Parent node of every node
        * O(n) Space required for HashSet, to account for the visited nodes in BFS to avoid cycles
        * O(n) Space required for BFS Queue
     */
    public int timeToBurnTree(TreeNode root, int start){
        if (root == null)
            return 0;

        // Create a map to store the parent of each node in the tree.
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();

        // We need to store the Reference of Burned Start node. So, we use an array of size 1 to do this
        TreeNode[] burningStart = {null};

        // Mark/Find the Parents of each node in the Tree, also figure out the Reference of the node
        // from where Burning starts based on its value
        markParents(root, null, parents, burningStart, start);

        // Time taken to Burn the whole Tree. Tree is burning is Breadth wise Fashion, So, we do BFS
        int timeToBurnTree = 0;

        // Visited Array/Set to keep track of Visited Nodes, (as used in Undirected graph to avoid cycles)
        HashSet<TreeNode> visited = new HashSet<>();

        // Similar to BFS of Undirected Graph
        // Start a BFS Traversal from the Burned Start Node, till we traversed/burned the whole Tree
        // While Traversing, we also mark it visited in the Set
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(burningStart[0]);
        visited.add(burningStart[0]);

        while (!queue.isEmpty()){
            int size = queue.size();

            while (size-- > 0){
                // For the current node
                TreeNode node = queue.remove();

                // Traverse its Left Child Neighbour
                if (node.left != null  &&  !visited.contains(node.left)){
                    queue.add(node.left);
                    visited.add(node.left);
                }
                // Traverse its Right Child Neighbour
                if (node.right != null  &&  !visited.contains(node.right)){
                    queue.add(node.right);
                    visited.add(node.right);
                }
                // Traverse its Parent Node Neighbour
                if (parents.get(node) != null  &&  !visited.contains(parents.get(node))){
                    queue.add(parents.get(node));
                    visited.add(parents.get(node));
                }
            }
            // If Queue is not empty, it means current level nodes, which were burned at the same time;
            // actually did burn some other adjacent nodes. So, time taken to burn the adjacent nodes will
            // increase by 1.
            if (!queue.isEmpty())
                timeToBurnTree++;
        }
        return timeToBurnTree;
    }


    // Find the Parent node of all the Nodes in Tree, and put the Parent to the HashMap (each node will
    // have only Parent node). Traverse the tree (via Depth-first search)
    // Also figure out the Node from where Burning starts, by reference and return it.
    private void markParents(TreeNode node, TreeNode parent, HashMap<TreeNode, TreeNode> parents,
                             TreeNode[] burnedStartNode, int burnedStartNodeValue){
        if (node == null)
            return;

        // If current node is the node from where burning starts, store its reference
        if (node.data == burnedStartNodeValue)
            burnedStartNode[0] = node;

        // We will pass both the current node and its parent node
        // Mark/Put the Parent of current node in the HashMap
        parents.put(node, parent);

        // Call DFS for Left Child with current node as Parent
        markParents(node.left, node, parents, burnedStartNode, burnedStartNodeValue);

        // Call DFS for Right Child with current node as Parent
        markParents(node.right, node, parents, burnedStartNode, burnedStartNodeValue);
    }


    // Tree Node
    private static class TreeNode {
        int data;
        TreeNode left, right;
        public TreeNode(int data) {
            this.data = data;
        }
    }
}