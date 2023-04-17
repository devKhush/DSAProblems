package Arrays.KidsWithTheGreatestNumberOfCandies;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/

public class KidsWithTheGreatestNumberOfCandies {
    /***********************************************************************************
     * TC -> O(n)
     * SC -> O(1)
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n = candies.length;
        List<Boolean> answer = new ArrayList<>();

        int max = 0;
        for (int candy : candies)
            max = Math.max(max, candy);

        for (int i = 0; i < n; i++){
            answer.add(candies[i] + extraCandies >= max);
        }
        return answer;
    }
}
