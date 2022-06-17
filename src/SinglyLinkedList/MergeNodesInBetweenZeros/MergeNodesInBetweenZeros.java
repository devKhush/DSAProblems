package SinglyLinkedList.MergeNodesInBetweenZeros;

class MergeNodesInBetweenZeros {

    /*
    ******************************** Efficient Solution *********************************************
    * TC -> O(n)
    * SC -> O(1)
    *
    * Idea here is to UPDATE the Nodes with value as 0 (occurring at start, between & end) with the
    * sum of values of non-zeros elements between them.
    * Updating process of Nodes with 0 value starts from the head (which is itself node with 0 value)
    * and continues in nodes with zeros values occurring in between the linked-list. But the last node
    * in the list with 0 value is skipped as we don't require it.
     */

    public ListNode mergeNodes(ListNode head) {
        if (head == null) return null;

        // We start iterating from next element of head (as first element is 0)
        // 'ptr' stores current node
        ListNode ptr = head.next;

        // 'previousZeroNode' stores the address of previous node encountered with value 0
        //'previous' stores the previous node to 'ptr' Node
        ListNode previousZeroNode = head, previous = null;

        // this 'sumBetweenZeros' stores sum of values between two zeros
        int sumBetweenZeros = 0;


        while (ptr != null){
            // if current node's value is not zero, simply sum the value & update the 'previous' node
            if (ptr.val != 0){
                sumBetweenZeros += ptr.val;
                previous = ptr;
            }

            // if current node's value is zero, mark the 'previousZeroNode' node value with sum of
            // non-zeros values between two zeros AND point the next node of 'previousZeroNode' with the
            // current zero node (which is 'ptr' now) iff the current node ('ptr') is not the last
            // 'zero node' of the linked-list   (We need to return non-zero nodes)
            else{
                previousZeroNode.val = sumBetweenZeros;

                if (ptr.next == null)
                    previousZeroNode.next = null;
                else
                    previousZeroNode.next = ptr;

                previousZeroNode = previousZeroNode.next;

                // make the previous node's next to null as previous node will be having non-zeros values
                previous.next = null;

                // Reinitialize the sum to 0 as now we will again make the pairs of elements between two zeros
                sumBetweenZeros = 0;
            }
            ptr = ptr.next;
        }

        // Head will now store the non-zero value as we have updated it
        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
