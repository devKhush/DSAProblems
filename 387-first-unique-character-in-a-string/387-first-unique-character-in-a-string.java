class Solution {
    public int firstUniqChar(String s) {
        int j;
        for (int i=0; i<s.length(); i++){
            for (j=0; j<s.length(); j++){
                if (i!=j && s.charAt(i)==s.charAt(j))
                    break;
            }
            if (j == s.length())
                return i;
        }
        
        return -1;
    }
}