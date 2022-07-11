package Stacks.DailyTemperatures;
import java.util.Stack;

// https://leetcode.com/problems/daily-temperatures/
// This approach is similar to Next Greater EElement in 'Stack' Problem

class DailyTemperatures {
    // Simple Brute Force Solution
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[temperatures.length];

        for (int i = 0; i < n; i++){
            int j = i + 1;

            while (j < n  &&  temperatures[i] >= temperatures[j])
                j++;

            answer[i] = j == n ? 0 : j - i;
        }
        return answer;
    }


    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] dailyTemperatures_StackSolution_1(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++){
            while (!stack.isEmpty()  &&  temperatures[stack.peek()] < temperatures[i]){
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }

        while (!stack.isEmpty())
            answer[stack.pop()] = 0;

        return answer;
    }


    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] dailyTemperatures_StackSolution_2(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--){
            while (!stack.isEmpty()  &&  temperatures[i] >= temperatures[stack.peek()])
                stack.pop();

            answer[i] = !stack.isEmpty() ? stack.peek() - i : 0;
            stack.push(i);
        }
        return answer;
    }
}