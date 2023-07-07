package SlidingWindow.MaximizeTheConfusionOfAnExam;

// Pre-requisite: Max Consecutive - III
// https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/

public class MaximizeTheConfusionOfAnExam {
    /************************************* Sliding Window ***********************************************
     * Time Complexity: O(4*n) ~ O(n)
     * Space Complexity: O(1)
     */
    public int maxConsecutiveAnswers__1(String answerKey, int k) {
        int maxConsecutiveTrue = maxConsecutive(answerKey, k, 'F');
        int maxConsecutiveFalse = maxConsecutive(answerKey, k, 'T');
        return Math.max(maxConsecutiveTrue, maxConsecutiveFalse);
    }
    private int maxConsecutive(String s, int k, char toReplace){
        int n = s.length();
        int maxConsecutive = 0;
        int left = 0;
        int count = 0;
        for (int right = 0; right < n; right++){
            if (s.charAt(right) == toReplace)
                count++;
            while (count > k){
                if (s.charAt(left) == toReplace)
                    count--;
                left++;
            }
            maxConsecutive = Math.max(maxConsecutive, right - left + 1);
        }
        return maxConsecutive;
    }

    /*********************************** Same Compact Solution ***************************************
     * Time Complexity: O(3*n)
     * Space Complexity: O(1)
     */
    public int maxConsecutiveAnswers__2(String answerKey, int k) {
        int n = answerKey.length();
        int leftTrue = 0, leftFalse = 0;
        int countTrue = 0, countFalse = 0;
        int maxConsecutiveTrue = 0, maxConsecutiveFalse = 0;

        for (int right = 0; right < n; right++){
            // Shifting window to find max no. of consecutive True
            if (answerKey.charAt(right) == 'F')
                countFalse++;
            while (countFalse > k){
                if (answerKey.charAt(leftTrue) == 'F')
                    countFalse--;
                leftTrue++;
            }
            maxConsecutiveTrue = Math.max(maxConsecutiveTrue, right - leftTrue + 1);


            // Shifting window to find max no. of consecutive False
            if (answerKey.charAt(right) == 'T')
                countTrue++;
            while (countTrue > k){
                if (answerKey.charAt(leftFalse) == 'T')
                    countTrue--;
                leftFalse++;
            }
            maxConsecutiveFalse = Math.max(maxConsecutiveFalse, right - leftFalse + 1);
        }
        return Math.max(maxConsecutiveTrue, maxConsecutiveFalse);
    }
}
