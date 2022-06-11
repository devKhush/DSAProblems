class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l=0, r=0;
        int maxSubStringLength = 0;
        String subString = "";
        
        while(r<s.length()){
            if (subString.indexOf(s.charAt(r))==-1){
                subString += s.charAt(r);
                maxSubStringLength = Integer.max(maxSubStringLength,subString.length());
                r++;
            }
            else{
                subString = "";
                r = ++l;
            }
        }
        return maxSubStringLength;
    }
}