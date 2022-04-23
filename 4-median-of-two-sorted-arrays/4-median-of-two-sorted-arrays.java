class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length){
            int[] temp = A;
            A = B;
            B = temp;
        }
        
        int totalElement = A.length + B.length;
        int halfElement = (totalElement + 1)/2;
        
        int low = 0,  high = A.length;
        
        while (low <= high){
            int midA = (low + high)/2;
            int midB = halfElement - midA;
            
            int ALeft = midA-1 >= 0 ? A[midA-1] : Integer.MIN_VALUE;            
            int BLeft = midB-1 >= 0 ? B[midB-1] : Integer.MIN_VALUE;
            int ARight = midA < A.length ? A[midA] : Integer.MAX_VALUE;
            int BRight = midB < B.length ? B[midB] : Integer.MAX_VALUE;

            if (ALeft <= BRight  && BLeft <= ARight){
                if (totalElement%2 == 0)
                    return (Math.max(ALeft, BLeft) + Math.min(ARight, BRight)) / 2.0;
                else
                    return Math.max(ALeft, BLeft);
            }
            
            else if (ALeft > BRight)
                high = midA - 1;
            
            else if (BLeft > ARight)
                low = midA + 1;            
        }
        
        return 0.0;
    }
}