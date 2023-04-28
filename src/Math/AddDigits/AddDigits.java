package Math.AddDigits;

// https://leetcode.com/problems/add-digits/description/
// https://leetcode.com/problems/add-digits/solutions/1754049/easy-o-1-explanation-with-example/

public class AddDigits {
    // ************************************ Solution 1 ***************************************
    public int addDigits(int n) {
        while (n / 10 != 0) {
            int sum = 0;
            while (n != 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        return n;
    }

    // ************************************ Solution 2 ***************************************
    // https://leetcode.com/problems/add-digits/solutions/1754049/easy-o-1-explanation-with-example/
    public int addDigits_(int n) {
        if (n == 0)
            return 0;
        return n % 9 == 0 ? 9 : n % 9;
    }
}
