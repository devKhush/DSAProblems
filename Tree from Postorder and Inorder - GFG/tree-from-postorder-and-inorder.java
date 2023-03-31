//{ Driver Code Starts
import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

class InorderPostorderToTree {
    public void preOrder(Node root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        preOrder(root.left);

        preOrder(root.right);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        InorderPostorderToTree ip = new InorderPostorderToTree();
        int T = sc.nextInt();
        while (T > 0) {
            int n = sc.nextInt();
            int inorder[] = new int[n];
            int postorder[] = new int[n];
            for (int i = 0; i < n; i++) inorder[i] = sc.nextInt();
            for (int i = 0; i < n; i++) postorder[i] = sc.nextInt();
            GfG g = new GfG();
            Node root = g.buildTree(inorder, postorder, n);
            ip.preOrder(root);
            System.out.println();

            T--;
        }
    }
}

// } Driver Code Ends


/* Tree node structure
class Node
{
    int data;
    Node left;
    Node right;

        Node(int value)
    {
        data = value;
        left = null;
        right = null;
    }
}*/


class GfG{
    Node buildTree(int inorder[], int postorder[], int n) {
        // Your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(inorder[i], i);
            
        Node root = buildTree(0, n - 1, 0, n - 1, inorder, postorder, map);
        return root;
    }
    
    private static Node buildTree(int inStart, int inEnd, int postStart, int postEnd,
                                  int[] inorder, int[] postorder, HashMap<Integer, Integer> map){
        if (inStart > inEnd)
            return null;

        Node node = new Node(postorder[postEnd]);
        int i = map.get(node.data);
        node.left = buildTree(inStart, i - 1, postStart, postStart + (i - inStart) - 1,
                inorder, postorder, map);
        node.right = buildTree(i + 1, inEnd, postStart + (i - inStart), postEnd - 1,
                inorder, postorder, map);
        return node;
    }
}
