package DynamicProgramming.MaximumSumOfNonAdjacentElements.HouseRobber_II;

// https://youtu.be/3WaxQMELSkw
// https://takeuforward.org/data-structure/dynamic-programming-house-robber-dp-6/

public class HouseRobber_II {

    // *********************************** Constant space solution ***********************************
    // with two loops calculate
    private  static long houseRobber(int[] arr){
        int n = arr.length;
        if (n == 1)
            return arr[0];

        long maxSumWithoutConsideringLastElement;
        long maxSumWithConsideringLastElement;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // finding the max-sum of non-adjacent elements, by considering indices [0, n-2]
        long maxSum_NminusTwo = 0;
        long maxSum_NminusOne = arr[0];
        // for loop will start from 1, as we consider 0th index and run till n-2
        for (int i = 1; i<n-1; i++){
            long maxSumByTaking = arr[i] + maxSum_NminusTwo;
            long maxSumByNotTaking = 0 + maxSum_NminusOne;

            long maxSumAt_IthIndex = Math.max(maxSumByNotTaking, maxSumByTaking);

            maxSum_NminusTwo = maxSum_NminusOne;
            maxSum_NminusOne = maxSumAt_IthIndex;
        }
        maxSumWithoutConsideringLastElement = maxSum_NminusOne;


        ////////////////////////////////////////////////////////////////////////////////////////////
        // finding the max-sum of non-adjacent elements, by considering indices [1, n-1]
        maxSum_NminusOne = arr[1];
        maxSum_NminusTwo = 0;
        // for loop will start from 2, as we don't consider 0th index and run till n-1
        for (int i = 2; i < n; i++) {
            long maxSumByTaking = arr[i] + maxSum_NminusTwo;
            long maxSumByNotTaking = 0 + maxSum_NminusOne;

            long maxSumAt_IthIndex = Math.max(maxSumByNotTaking, maxSumByTaking);

            maxSum_NminusTwo = maxSum_NminusOne;
            maxSum_NminusOne = maxSumAt_IthIndex;
        }
        maxSumWithConsideringLastElement = maxSum_NminusOne;

        return Math.max(maxSumWithConsideringLastElement, maxSumWithoutConsideringLastElement);
    }



    //  ******************************** using for loop ********************************

    private static long findMaxSumOfNonAdjacentElements(int[] arr, int n, int offset){
        long maxSum_NminusTwo = 0;
        long maxSum_NminusOne = arr[0 + offset];

        for (int i = 1 + offset; i < n - 1 + offset; i++){
            long maxSumByTaking = arr[i] + maxSum_NminusTwo;
            long maxSumByNotTaking = 0 + maxSum_NminusOne;

            long maxSumAt_IthIndex = Math.max(maxSumByNotTaking, maxSumByTaking);

            maxSum_NminusTwo = maxSum_NminusOne;
            maxSum_NminusOne = maxSumAt_IthIndex;
        }
        return maxSum_NminusOne;
    }

    private  static long houseRobber(int[] arr, int n) {
        n = arr.length;
        if (n == 1)
            return arr[0];

        // Logic is we can add a offset for each case
        // For [0, n-2], offset will be 0
        // For [1, n-1], offset will be 1

        // finding the max-sum of non-adjacent elements, by considering indices [0, n-2]
        long maxSumWithoutConsideringLastElement = findMaxSumOfNonAdjacentElements(arr, n, 0);

        // finding the max-sum of non-adjacent elements, by considering indices [1, n-1]
        long maxSumWithConsideringLastElement = findMaxSumOfNonAdjacentElements(arr,n, 1);

        // answer will be max of these two
        return Math.max(maxSumWithConsideringLastElement, maxSumWithoutConsideringLastElement);
    }


    // ********************************************************************************************
    //          ONE MORE WAY IS THERE TO SOLVE THIS (BY CREATING TWO ARRAYLIST)  (next .java file)
    // ********************************************************************************************

}
