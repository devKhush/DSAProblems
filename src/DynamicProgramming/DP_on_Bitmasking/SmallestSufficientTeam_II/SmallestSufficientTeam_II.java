package DynamicProgramming.DP_on_Bitmasking.SmallestSufficientTeam_II;
import java.util.HashMap;
import java.util.List;

// A variation of below problem
// In this variation, we need to return the smallest size of set of people with all skill-set.
// https://leetcode.com/problems/smallest-sufficient-team/description/

public class SmallestSufficientTeam_II {
    /************************************ Memoization + Bit Masking ***********************************
     * Intuition:
        * Knapsack
        * Take or not take person
        * Instead of maintaining Hashset for skills for all persons, keep track of skills using bit
     * Time Complexity: O(2^m * n)
        * Two Dp states
        * n -> no. of people  &  m -> no. of required skills
     * Space Complexity: O(2^m * n)
     */
    public int smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = people.size();
        int m = req_skills.length;

        // HashMap to maintain id for each skill
        int skillRequired = (1 << m) - 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++){
            map.put(req_skills[i], i);
        }

        // Make a skill array of all people
        int[] peopleSkills = new int[n];
        for (int i = 0; i < n; i++){
            for (String skill : people.get(i))
                peopleSkills[i] |= (1 << map.get(skill));
        }

        Integer[][] dp = new Integer[n][skillRequired + 1];
        return f(n - 1, skillRequired, peopleSkills, dp);

    }

    private int f(int i, int skillRequired, int[] personSkills, Integer[][] dp){
        if (skillRequired == 0)
            return 0;
        if (i < 0)
            return (int)1e9;
        if (dp[i][skillRequired] != null)
            return dp[i][skillRequired];

        int take = 1 + f(i-1, skillRequired & ~personSkills[i], personSkills, dp);
        int notTake = f(i-1, skillRequired, personSkills, dp);
        return dp[i][skillRequired] = Math.min(take, notTake);
    }
}
