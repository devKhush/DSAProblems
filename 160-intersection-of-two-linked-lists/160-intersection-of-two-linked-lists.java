/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    
    // Much Shorter Code
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ptrA = headA, ptrB = headB;
        
        while (ptrA != ptrB){
            ptrA = ptrA != null ? ptrA.next : headB;
            ptrB = ptrB != null ? ptrB.next : headA;
        }
        
        return ptrA;
    }

    //
    private int getLengthDifference(ListNode headA, ListNode headB){
        int lenA = 0, lenB = 0;
        
        while (headA != null || headB != null){
            lenA = headA == null ? lenA : lenA + 1;
            lenB = headB == null ? lenB : lenB + 1;
            
            headA = headA == null ? null : headA.next;
            headB = headB == null ? null : headB.next;
        }
        
        return lenA - lenB;
    }
    
    public ListNode getIntersectionNode_Method1(ListNode headA, ListNode headB) {
        int lengthDifference = getLengthDifference(headA, headB);
        
        ListNode ptrA = headA, ptrB = headB;
        
        if (lengthDifference > 0)
            while (lengthDifference > 0){
                ptrA = ptrA.next;
                lengthDifference--;
            }
        
        else
            while (lengthDifference < 0){
                ptrB = ptrB.next;
                lengthDifference++;
            }
        
        
        while (ptrA != null){
            if (ptrA == ptrB)
                return ptrA;
            
            ptrA = ptrA.next;
            ptrB = ptrB.next;
        }
        
        return null;
    } 
}




