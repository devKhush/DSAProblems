package Stacks.DailyTemperatures;

// Based on simple logic
// DRY Run once, O(n^2) in worst case
// 95% faster than stack one

public class DailyTemperatures_WithoutStack {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        int maxIndex = 0;
        
        for (int i=temperatures.length-1; i>=0; i--){
            int j = i-1;
            while (j>=0  &&  temperatures[i] > temperatures[j]){
                answer[j] = i - j;
                j--;
            }
        }
        return answer;
    }
}