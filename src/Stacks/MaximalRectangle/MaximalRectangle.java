package Stacks.MaximalRectangle;
import java.util.Stack;

// https://youtu.be/oaN9ibZKMpA
// https://youtu.be/dAVF2NpC3j4
// GREAT READING:
// https://www.codingninjas.com/codestudio/library/maximum-size-of-rectangle-in-a-binary-matrix
// PRE_REQUISITE: "MAXIMUM/LARGEST AREA OF RECTANGLE IN A HISTOGRAM"

class MaximalRectangle {
    /** *********************************** INTUITION ***********************************************
     * This problem can be solved using "LARGEST AREA OF RECTANGLE IN HISTOGRAM".
     * If any cell in the matrix is '1', it means "Rectangle can be extended To the current cell in matrix"
     * These, rectangle that are formed can be visualized as bars/heights.
     * Thus, we solve it using Maximum area in a Histogram.
     * So, we can convert the matrix into "Histograms/Rectangles" row-wise & find the maximum area
       of the rectangle in each of the Histogram.
     *
     * Whenever, cell value is '1' it means current rectangle can be extended in below direction. So,
       increment the height of that rectangle/bar.
     * Whenever, cell value is '0' it means current rectangle can't be extended in below direction. So,
       we make the height of that rectangle/bar to be 0.


     * If m -> columns  &  n -> rows
     *  Time Complexity :  O(m * (n + n))  =  O(m * n)
     * Space Complexity:  O(n) + O(n) + O(n)  =  O(n)
     */
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[] histogram = new int[columns];
        int maxRectangleArea = 0;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (matrix[i][j] == '1')
                    histogram[j]++;
                else
                    histogram[j] = 0;
            }
            maxRectangleArea = Math.max(maximumAreaInHistogram_V1(histogram, columns), maxRectangleArea);
        }
        return maxRectangleArea;
    }

    // ***************************** Same as Maximum area in the Histogram *****************************
    public int maximumAreaInHistogram_V1(int[] arr, int n){
        int[] PSE = new int[n];
        int[] NSE = new int[n];

        PSE[0] = -1;
        NSE[n - 1] = n;

        for (int i = 1; i < n; i++){
            int low = i - 1;
            while (low >= 0  &&  arr[low] >= arr[i])
                low = PSE[low];
            PSE[i] = low;
        }

        for (int i = n - 1; i >= 0; i--){
            int high = i + 1;
            while (high < n  &&  arr[i] <= arr[high])
                high = NSE[high];
            NSE[i] = high;
        }

        int maxAreaInHistogram = 0;

        for (int i = 0; i < n; i++){
            int width = NSE[i] - PSE[i] - 1;
            int height = arr[i];
            maxAreaInHistogram = Math.max(height * width, maxAreaInHistogram);
        }
        return maxAreaInHistogram;
    }

    // ***************************** Same as Maximum area in the Histogram *****************************
    public int maximumAreaInHistogram(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int maximumAreaInHistogram = 0;

        for (int i = 0; i <= n; i++){
            while (!stack.isEmpty()  &&  (i == n || arr[stack.peek()] >= arr[i])){
                int height = arr[stack.pop()];
                int width;
                if (!stack.isEmpty())
                    width = i - stack.peek() - 1;
                else
                    width = i;

                maximumAreaInHistogram = Math.max(height * width, maximumAreaInHistogram);
            }
            stack.push(i);
        }
        return maximumAreaInHistogram;
    }
}
