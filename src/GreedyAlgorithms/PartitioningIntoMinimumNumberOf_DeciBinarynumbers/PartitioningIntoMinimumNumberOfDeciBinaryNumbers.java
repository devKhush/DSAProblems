package GreedyAlgorithms.PartitioningIntoMinimumNumberOf_DeciBinarynumbers;

/*
************************* Approach/Intuition/Hint ****************************************
* Think about if the input was only one digit. Then you need to add up as many ones
  as the value of this digit.
* If the input has multiple digits, then you can solve for each digit independently,
  and merge the answers to form numbers that add up to that input.
* Thus, the answer is equal to the max digit.
 */

class PartitioningIntoMinimumNumberOfDeciBinaryNumbers {
    /*
    ********************************* Efficient Solution *****************************************
    * Time Complexity: O(n)
    * Space Complexity: O(n)
    * If we used string for O(1) Space, then Time Complexity will be O(n^2)
     */
    public int minPartitions(String n) {
        int minNumberOfDeciBinaryNumber = 0;
        char[] number = n.toCharArray();
        
        for (char digit : number)
            minNumberOfDeciBinaryNumber = Math.max(minNumberOfDeciBinaryNumber, digit - '0');
        return minNumberOfDeciBinaryNumber;
    }

    /*
     ********************************* Efficient Solution *****************************************
     * Time Complexity: O(Max_digit_in_String * n)
       Reason: While loop will run until there is a non-zero digit in the number array
       In each while loop iteration we traverse the entire array
     * Space Complexity: O(n)
     */
     public int minPartitions_BruteForce(String n) {
        int minNumberOfDeciBinaryNumber = 0;
        char[] number = n.toCharArray();
        
        while (true){
            boolean hasAtleastOneNonZeroDigit = false;
            
            for (int i = 0; i < number.length; i++){
                if (number[i] != '0'){
                    number[i]--;
                    
                    if (!hasAtleastOneNonZeroDigit)
                        hasAtleastOneNonZeroDigit = number[i] != '0';
                }
            }
            minNumberOfDeciBinaryNumber++;
            if (!hasAtleastOneNonZeroDigit)
                break;
        }
         return minNumberOfDeciBinaryNumber;
    }
}