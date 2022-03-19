class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[temperatures.length];
        
        for (int i=0; i<temperatures.length; i++){
            while(!stack.isEmpty()  &&  temperatures[i] > temperatures[stack.peek()]){
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty())
            answer[stack.pop()] = 0;
        return answer;
    }
}