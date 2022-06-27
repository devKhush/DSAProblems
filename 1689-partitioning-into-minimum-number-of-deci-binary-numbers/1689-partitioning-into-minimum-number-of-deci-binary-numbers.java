class Solution {
    public int minPartitions_Efficient(String n) {
        int minNumberOfDeciBinaryNumber = 0;
        char[] number = n.toCharArray();
        
        for (char digit : number)
            minNumberOfDeciBinaryNumber = Math.max(minNumberOfDeciBinaryNumber, digit - '0');
        return minNumberOfDeciBinaryNumber;
    }
    
     public int minPartitions(String n) {
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