package DynamicProgramming.DP_on_Bitmasking.SmallestSufficientTeam;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// DP + Bit-Masking
// https://leetcode.com/problems/smallest-sufficient-team/description/
// https://leetcode.com/problems/smallest-sufficient-team/editorial/

public class SmallestSufficientTeam {
    /********************************** DP + Bit-masking ************************************
     * Intuition:
        * Read editorial
     * Time Complexity:  O(n * 2^m)
        * One DP State and loop of size n in each call
     * Space Complexity: O(2^m)
        * DP Array and Stack space
     */
    public int[] smallestSufficientTeam__memo(String[] req_skills, List<List<String>> people) {
        int n = people.size();
        int m = req_skills.length;

        // HashMap to maintain id for each skill
        int skillRequired = (1 << m) - 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++){
            map.put(req_skills[i], i);
        }

        // Make a skill array of all people
        int[] peopleSkillMask = new int[n];
        for (int i = 0; i < n; i++){
            for (String skill : people.get(i))
                peopleSkillMask[i] |= 1 << map.get(skill);
        }

        // DP Array
        Long[] dp = new Long[skillRequired + 1];
        // Index and Size of smallest team in a mask
        long smallestPeopleMask = f(skillRequired, peopleSkillMask, dp);

        // Index of smallest team
        int[] smallestTeam = new int[Long.bitCount(smallestPeopleMask)];
        int ind = 0;
        for (int i = 0; i < n; i++){
            if (((smallestPeopleMask >> i) & 1) == 1)
                smallestTeam[ind++] = i;
        }
        return smallestTeam;
    }

    private long f(int skillRequired, int[] peopleSkillMask, Long[] dp){
        if (skillRequired == 0)
            return 0;
        if (dp[skillRequired] != null)
            return dp[skillRequired];

        for (int i = 0; i < peopleSkillMask.length; i++){
            int remainingSkill = skillRequired & ~peopleSkillMask[i];
            if (remainingSkill != skillRequired){
                long teamSize = f(remainingSkill, peopleSkillMask, dp)  | (1L << i);
                if (dp[skillRequired] == null || Long.bitCount(teamSize) < Long.bitCount(dp[skillRequired])){
                    dp[skillRequired] = teamSize;
                }
            }
        }
        return dp[skillRequired];
    }


    /***************************************** Tabulation *****************************************8
     * Intuition:
        * Read editorial
     * Time Complexity:  O(n * 2^m)
        * One DP State and loop of size n in each call
     * Space Complexity: O(2^m)
        * DP Array
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = people.size();
        int m = req_skills.length;

        // HashMap to maintain id for each skill
        int skillRequired = (1 << m) - 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++){
            map.put(req_skills[i], i);
        }

        // Make a skill array of all people
        int[] peopleSkillMask = new int[n];
        for (int i = 0; i < n; i++){
            for (String skill : people.get(i))
                peopleSkillMask[i] |= 1 << map.get(skill);
        }

        // DP Array
        long[] dp = new long[skillRequired + 1];
        Arrays.fill(dp, (1L << n) - 1);
        dp[0]= 0;

        for (int skillReq = 1; skillReq <= skillRequired; skillReq++){
            for (int i = 0; i < peopleSkillMask.length; i++){
                int remainingSkill = skillReq & ~peopleSkillMask[i];
                if (remainingSkill != skillReq){
                    long teamSize = dp[remainingSkill]  | (1L << i);
                    if (Long.bitCount(teamSize) < Long.bitCount(dp[skillReq])){
                        dp[skillReq] = teamSize;
                    }
                }
            }
        }

        // Index and Size of smallest team in a mask
        long smallestPeopleMask = dp[skillRequired];

        // Index of smallest team
        int[] smallestTeam = new int[Long.bitCount(smallestPeopleMask)];
        int ind = 0;
        for (int i = 0; i < n; i++){
            if (((smallestPeopleMask >> i) & 1) == 1)
                smallestTeam[ind++] = i;
        }
        return smallestTeam;
    }
}
