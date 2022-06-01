package Arrays.MissingNumberInArray;

class MissingNumberInArray {

    int MissingNumber(int[] array, int n) {
        
        // Let's calculate the sum from 1 to N using AP series
        int sumFromOneToN = n*(n+1)/2;
        int arraySum = 0;
        
        // Actual array Sum that has a missing number in range [1, N]
        for (int element : array) 
            arraySum+= element;
            
        // That element will not contribute in arraySum
        // so difference is the answer
        return sumFromOneToN - arraySum;
    }
}