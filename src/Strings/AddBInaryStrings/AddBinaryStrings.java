package Strings.AddBInaryStrings;

public class AddBinaryStrings {
    /************************************ Solution 1 *********************************************
     * Intuition: Idea is same as that of "Addition of two numbers given as LinkedList"
     * We are just doing simple "Addition of Binary Numbers"
     * String concatenation is very slower.

     * Length of String a = n
       Length of String b = m
     * Time Complexity: O(max(n, m))
     * Space Complexity: O(1)
     */
    public String addBinary_Brute(String a, String b) {
        String binarySum = "";

        int n = a.length(), m = b.length();
        int i = n - 1, j = m - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1){
            int sum = 0;
            sum += carry;

            if (i >= 0)
                sum += a.charAt(i--) - '0';
            if (j >= 0)
                sum += b.charAt(j--) - '0';

            binarySum = (sum % 2) + binarySum;
            carry = sum / 2;
        }
        return binarySum;
    }


    /************************************ Solution 2 *********************************************
     * Intuition: Idea is same as that of "Addition of two numbers given as LinkedList"
     * We are just doing simple "Addition of Binary Numbers"
     * We will use a StringBuilder() here

     * Length of String a = n
       Length of String b = m
     * Time Complexity: O(max(n, m))
     * Space Complexity: O(1)
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1){
            int sum = 0;
            sum += carry;

            if (i >= 0)
                sum += a.charAt(i--) - '0';
            if (j >= 0)
                sum += b.charAt(j--) - '0';

            sb.append(sum % 2);
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }
}
