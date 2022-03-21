class Solution {
    
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        int maxIndex = 0;
        
        for (int i=0; i<temperatures.length; i++){
            int j = i+1;
            while (j<temperatures.length  &&  temperatures[i] >= temperatures[j])
                j++;
            if (j==temperatures.length)
                answer[i] = 0;
            else
                answer[i] = j - i;
        }
        return answer;
    }
    
        
    public int[] dailyTemperatures_123(int[] temperatures) {
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