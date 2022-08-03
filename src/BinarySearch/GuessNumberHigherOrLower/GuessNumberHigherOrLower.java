package BinarySearch.GuessNumberHigherOrLower;
import java.util.Random;

// ******************************** Simple Binary Search Solution *************************************
// Time Complexity  : O(log(n))
// Space Complexity : O(1)

public class GuessNumberHigherOrLower {
    public int guessNumber(int n) {
        int low = 1, high = n;
        
        while (low <= high){
            int mid = low + (high - low)/2;
            int guess = guess(mid);
            
            if (guess == 0)
                return mid;
            else if (guess == 1)
                low = mid + 1;
            else if (guess == -1)
                high = mid - 1;
        }
        return -1;
    }

    /**
     * Forward declaration of guess API.
     * @param num  your guess
     * @return 	     -1 if num is higher than the picked number
     *			      1 if num is lower than the picked number
     *               otherwise return 0
     * int guess(int num);
     */
    private int guess(int num) {
        Random random = new Random();
        int guess =  random.nextInt(1, 1000);

        return Integer.compare(guess, num);
    }
}