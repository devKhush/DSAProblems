class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length){
            int[] temp = A;
            A = B;
            B = temp;
        }

        int m = A.length, n = B.length;
        int halfElements = (m + n + 1)/2;

        int low = 0, high = A.length;

        while (low <= high){
            int midA = low + (high - low)/2;
            int midB = halfElements - midA;

            int ALeft = midA -1 >= 0 ? A[midA - 1] : Integer.MIN_VALUE;
            int ARight = midA < m ? A[midA] : Integer.MAX_VALUE;
            int BLeft = midB -1 >= 0 ? B[midB - 1] : Integer.MIN_VALUE;
            int BRight = midB < n ? B[midB] : Integer.MAX_VALUE;

            if (ALeft <= BRight  &&  BLeft <= ARight){
                if ((m + n) % 2 == 1)
                    return Math.max(ALeft, BLeft);
                else
                    return (Math.max(ALeft, BLeft) + Math.min(ARight, BRight)) / 2.0;
            }
            else if (ALeft > BRight)
                high = midA - 1;
            else if (BLeft > ARight)
                low = midA + 1;
        }
        return 0.0;
    }
}