package NumberTheory.PowerOfFour;

public class PowerOfFour {
    /************************************ Simple Recursive Solution **************************************
     * Time Complexity: O( log4(n) )
        * Logarithm of n to the base 4
        * In each recursive call, we divide n by 4
     * Space Complexity: O( log4(n) )
        * Recursion Stack space will be log4(n), as in each recursive call, we divide n by 4
     */
    public boolean isPowerOfFour(int n) {
        if (n == 0)
            return false;

        if (n == 1)
            return true;

        if (n % 4 != 0)
            return false;

        return isPowerOfFour(n/4);
    }


    /************************************ Simple Iterative Solution **************************************
     * Time Complexity: O( log4(n) )
        * Logarithm of n to the base 4
        * In each turn, we divide n by 4
     * Space Complexity: O(1)
     */
    public boolean isPowerOfFour_Iterative(int n) {
        if (n == 0)
            return false;

        while (n % 4 == 0){
            n /= 4;
        }
        return n == 1;
    }
}
