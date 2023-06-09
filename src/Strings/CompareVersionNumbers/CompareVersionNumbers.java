package Strings.CompareVersionNumbers;

public class CompareVersionNumbers {
    /****************************** Efficient Solution 2 : Two Pointers **********************************
     * Time Complexity: O(max(v1, v2))
        * "v1" & "v2" are the lengths of two input strings 'version1' & 'version2'
     * Space Complexity: O(1)
     */
    public int compareVersion(String version1, String version2) {
        int n1 = version1.length(), n2 = version2.length();
        int i = 0, j = 0;

        while (i < n1 || j < n2){
            int num1 = 0, num2 = 0;

            while (i < n1  &&  version1.charAt(i) != '.'){
                num1 = num1*10 + (version1.charAt(i) - '0');
                i++;
            }
            while (j < n2  &&  version2.charAt(j) != '.'){
                num2 = num2*10 + (version2.charAt(j) - '0');
                j++;
            }
            i++; j++;
            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }
        return 0;
    }
}
