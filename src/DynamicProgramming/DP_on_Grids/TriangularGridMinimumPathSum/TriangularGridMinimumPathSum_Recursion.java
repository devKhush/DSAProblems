package DynamicProgramming.DP_on_Grids.TriangularGridMinimumPathSum;

// https://youtu.be/SrP-PiLSYC0
// https://takeuforward.org/data-structure/minimum-path-sum-in-triangular-grid-dp-11/

import java.util.List;

// Pre-requisite Grid Minimum Path Sum

public class TriangularGridMinimumPathSum_Recursion {

    // **************************** Method of travelling/ Walking with indices ************************
    // We can either move from (i,j) to below (i+1,j) and to diagonally right (i+1,j+1)
    // This will be equivalent to moving from (i,j) to straight up (i-1,j) & diagonally left (i-1,j-1)
    // ************************************************************************************************


    // *********************************** Simple Recursion ***********************************
    // There are two calls for every element ( total no. of elements are n*(n+1)/2, AP series )
    // T.C ==> O(2^(n*(n+1)/2)) = O(2^(n*n))   (including for loop, two calls for every element)

    // S.C is due to recursion stack space
    // S.C ==> O(Path length) = O(n) + O(root(2) * n) = O(N)   (Path length can be either side or Hypotenuse length)

    public int minimumTotal_V1(List<List<Integer>> triangle) {

        // Triangle is Isosceles Right angle triangle with equal base and height (both of n)
        int n = triangle.size();
        int minPathSum = Integer.MAX_VALUE;

        // Since the minimum path sum can be form (0,0) to any element in the last row of triangle
        // So, we need to find the minimum path sum from (0,0) to every element (index) in the last row
        // and take minimum of that
        for (int j = 0; j < triangle.get(n-1).size(); j++){
            int pathSumBySelecting_ith_ElementInLastRow = recursionSolution_V1(triangle, n-1, j);
            minPathSum = Math.min(minPathSum, pathSumBySelecting_ith_ElementInLastRow);
        }

        return minPathSum;
    }

    // (i,j) --> (ith Row, jth column)
    public int recursionSolution_V1(List<List<Integer>> arr, int i, int j){
        // Base case when at (0,0) (only element in 0th row), consider it in the current path
        if (i==0 && j==0)
            return arr.get(i).get(j);

        // Last condition can happen when we move from straight up in same column at last index
        // for example from (1,1) to (0,1)
        // in case of arrays, last condition can be simplified to 'j > i' as triangle is isosceles right triangle
        if (i<0 || j<0 || j >= arr.get(i).size())
            return Integer.MAX_VALUE/2;

        // Now we move straight up in the triangular grid and consider the current path cost into consideration
        int minPathByMovingUp = recursionSolution_V1(arr, i-1, j) + arr.get(i).get(j);

        // Now we move diagonally (up & left) in the triangular grid and consider the current path cost into consideration
        int minPathByMovingUpLeft = recursionSolution_V1(arr, i-1, j-1) + arr.get(i).get(j);

        return Math.min(minPathByMovingUp, minPathByMovingUpLeft);
    }



    // **************************** Another easier Recursive approach ********************************
    // In this we tend to move from (i,j) to down (i+1,j) & rightest diagonal (i+1, j+1) (right & bottom)
    // Using this approach we will never get out of boundary on the hypotenuse of isosceles right triangle
    // unlike to that of we were also reaching out of boundary in isosceles right triangle in Previous Solution
    // Think about this logically & visually

    // There are two calls for every element ( total no. of elements are n*(n+1)/2, AP series )
    // T.C ==> O(2^(n*(n+1)/2)) = O(2^(n*n))
    // S.C is due to recursion stack space
    // S.C ==> O(Path length) = O(n) + O(root(2) * n) = O(N)   (Path length can be either side or Hypotenuse length)

    public int minimumTotal_V2(List<List<Integer>> triangle) {
        int n = triangle.size();

        return recursionSolution_V2(triangle, 0, 0, n);
    }

    public int recursionSolution_V2(List<List<Integer>> triangle, int i, int j, int n) {
        if (i == n-1)
            return triangle.get(i).get(j);

        int minPathByMovingDown = recursionSolution_V2(triangle, i+1, j, n) + triangle.get(i).get(j);
        int minPathByMovingDownBottom = recursionSolution_V2(triangle, i+1, j+1, n) + triangle.get(i).get(j);

        return Math.min(minPathByMovingDownBottom, minPathByMovingDown);
    }
}


