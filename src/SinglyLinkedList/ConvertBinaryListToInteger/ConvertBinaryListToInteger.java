package SinglyLinkedList.ConvertBinaryListToInteger;

class ConvertBinaryListToInteger {
    /*
     ******************************** Efficient Approach ********************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Recall how to convert a decimal string to integer "decimalNumber = 10 * decimalNumber + num"
     * Same as that approach
     */
    public int getDecimalValue(ListNode head) {
        int num = 0;
        while (head != null){
            num = 2 * num + head.val;
            head = head.next;
        }
        return num;
    }
    
    /*
    ******************************** Simple Approach ********************************
    * Time Complexity: O(n)
    * Space Complexity: O(n)
     */
    public int getDecimalValue_V1(ListNode head) {
        String num = "";
        while (head != null){
            num += head.val;
            head = head.next;
        }
        return Integer.parseInt(num, 2);
    }

     public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}