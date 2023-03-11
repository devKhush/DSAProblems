package SinglyLinkedList.ConvertSortedListToBinarySearchTree;
import java.util.ArrayList;

// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/

public class ConvertSortedListToBinarySearchTree {
    /******************************* Solution 1: Divide and Conquer ************************************
     * Time Complexity: O(n * log(n))
        * DnC time complexity
     * Space Complexity: O(log(n))
        * Due to Recursion stack space of DnC
     */
    public TreeNode sortedListToBST(ListNode head){
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);

        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(slow.next);
        return node;
    }


    /******************************* Solution 2: Divide and Conquer ************************************
     * Time Complexity: O(n) + O(n * log(n))  ~ O(n * log(n))
        * DnC time complexity
     * Space Complexity: O(n) + O(log(n)) ~ O(n)
         * Due to Recursion stack space of DnC
     */
    private TreeNode convertLinkedListToBalancedBST(ListNode head) {
        ArrayList<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        return buildTree(values, 0, values.size() - 1);
    }
    private TreeNode buildTree(ArrayList<Integer> values, int low, int high) {
        if (low > high)
            return null;

        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(values.get(mid));
        root.left = buildTree(values, low, mid - 1);
        root.right = buildTree(values, mid + 1, high);
        return root;
    }



    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
