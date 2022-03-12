package Stacks.NextGreaterElement_I;

import java.util.HashMap;
import java.util.Stack;

// https://www.youtube.com/watch?v=8BDKB2yuGyg

public class NextGreaterElement_i {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nextGreaterPair = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<nums2.length; i++){
            while (!stack.isEmpty() && nums2[i]>stack.peek())
                nextGreaterPair.put(stack.pop(), nums2[i]);
            
            stack.push(nums2[i]);
        }
        
        for (int j=0; j<nums1.length; j++)
            nums1[j] = nextGreaterPair.getOrDefault(nums1[j],-1);
        
        return nums1;
    }
}