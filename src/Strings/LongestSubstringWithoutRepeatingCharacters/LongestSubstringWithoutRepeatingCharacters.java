package Strings.LongestSubstringWithoutRepeatingCharacters;

public class LongestSubstringWithoutRepeatingCharacters {
    /*
    public int lengthOfLongestSubstring(String s) {
        int left =0;
        int result=0;
        String subString = "";

        for (int i=0; i<s.length(); i++){
            if (subString.indexOf(s.charAt(i))==-1){
                subString+=s.charAt(i);
                result = Integer.max(result, subString.length());
            }
            else{
                subString = "";
                i=left++;
            }
        }
        return result;
    }*/


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

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcbacbacab"));
    }
}