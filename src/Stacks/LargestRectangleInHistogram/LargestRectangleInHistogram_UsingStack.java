package Stacks.LargestRectangleInHistogram;
import java.util.Stack;
import java.util.Arrays;

// https://www.youtube.com/watch?v=vhUxKxiconE          // Solution in this this
// https://www.youtube.com/watch?v=0do2734xhnU          // Intuitive idea in this (first 10 min)
// Based on Previous smaller element & next smaller element,
// similar to Next smaller element problem in stack

public class LargestRectangleInHistogram_UsingStack {
    public int largestRectangleArea(int[] heights) {
        int[] nextSmallerElement = nextSmallerElementIndices(heights, heights.length);
        int[] prevSmallerElement = previousSmallerElementIndices(heights, heights.length);
        
        int maxArea = 0;
        
        for (int i=0; i<heights.length; i++){
            int area = (nextSmallerElement[i] - prevSmallerElement[i] -1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }

    // Similar to the problem of Next Greater element, this is the code for problem of
    // Next Smaller Element using stack
    public int[] nextSmallerElementIndices(int[] arr, int n){
        int[] nextSmallerElement = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<n; i++){
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()])
                nextSmallerElement[stack.pop()] = i;
            stack.push(i);
        }
        
        while(!stack.isEmpty())
            nextSmallerElement[stack.pop()] = n;

        return nextSmallerElement;
    }

    // Similar to the problem of Next Greater element, this is the code for problem of
    // Previous Smaller Element using stack
    public int[] previousSmallerElementIndices(int[] arr, int n){
        int[] prevSmallerElement = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()])
                prevSmallerElement[stack.pop()] = i;
            stack.push(i);
        }
        
        while(!stack.isEmpty())
            prevSmallerElement[stack.pop()] = -1;

        return prevSmallerElement;
    }
}