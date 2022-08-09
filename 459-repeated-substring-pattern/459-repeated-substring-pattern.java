class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        
        for (int i = 1; i <= n/2; i++){
            if (n % i == 0){
                String subString = s.substring(0, i);
                int repeatTimes = n / i;
                
                StringBuilder sb = new StringBuilder();
                
                for (int j = 0; j < repeatTimes; j++)
                    sb.append(subString);
                
                if (sb.toString().equals(s))
                    return true;
            }
        }
        return false;
    }
}