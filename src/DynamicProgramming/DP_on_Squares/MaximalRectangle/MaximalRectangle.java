package DynamicProgramming.DP_on_Squares.MaximalRectangle;
import java.util.Stack;

// https://youtu.be/tOylVCugy9k
// https://takeuforward.org/data-structure/maximum-rectangle-area-with-all-1s-dp-on-rectangles-dp-55/
// https://leetcode.com/problems/maximal-rectangle/description/

public class MaximalRectangle {
    /**************************************** Stack Solution *****************************************
     * Intuition:
     * Used the concepts of "Maximum Area of rectangle in histogram", "Next greater element" and
        "Next smaller element".
     * See Stack solution of "Maximum Rectangle" (in stack solution)

     * Time Complexity: O(m*(n + n))
        * m-> rows
        * n -> columns
        * One n to build histogram and another one to find max area
     * Space Complexity: O(n) + O(n)
        * Stack and DP-array
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Height array is DP Array, we remember the previous rows heights to compute new heights
        int[] heights = new int[n];
        int maxRectArea = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '0')
                    heights[j] = 0;
                else if (matrix[i][j] == '1')
                    heights[j]++;
            }
            maxRectArea = Math.max(maxRectArea, maxRectangleInHistogram(heights));
        }
        return maxRectArea;
    }
    private int maxRectangleInHistogram(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        for (int i = 0; i <= n; i++){
            while (!stack.isEmpty() && (i == n || arr[i] < arr[stack.peek()])){
                int height = arr[stack.pop()];
                int start = !stack.isEmpty() ? stack.peek() : -1;
                int end = i;
                maxArea = Math.max(maxArea, height * (end - start - 1));
            }
            stack.push(i);
        }
        return maxArea;
    }
}
