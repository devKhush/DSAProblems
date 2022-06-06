public class Solution {
    // you need to treat n as an unsigned value
    
    public int hammingWeight(int n) {
        String binaryStr = Integer.toBinaryString(n);
        
        int oneCount = 0;
        
        for (int i = 0; i < binaryStr.length(); i++)
            if (binaryStr.charAt(i) == '1')
                oneCount++;
        
        return oneCount;
    }
    
    public int hammingWeight_(int n) {
        int num = n;
        String str = "";
        
        while (num > 0){
            int rem = num % 2;
            str += rem;
            num /= 2;
        }
        
        int oneCount = 0;
        for (int i=0; i<str.length(); i++){
            if (str.charAt(i) == '1')
                oneCount++;
        }
                
        return oneCount;
    }
}