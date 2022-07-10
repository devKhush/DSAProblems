class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<asteroids.length; i++){
            
            if (stack.isEmpty() || asteroids[i] >= 0)
                stack.push(asteroids[i]);
            
            else if (asteroids[i] < 0){
                while (true){
                    if (stack.peek() < 0){
                        stack.push(asteroids[i]);
                        break;
                    }
                    else if (stack.peek() == -asteroids[i]){
                        stack.pop();
                        break;
                    }
                    else if (stack.peek() > -asteroids[i])
                        break;
                    else{       // implies stack.peek() < -asteroids[i]
                        stack.pop();
                        if (stack.isEmpty()){
                            stack.push(asteroids[i]);
                            break;
                        }
                    }
                }
            }
        }
        
        int[] arr = new int[stack.size()];
        for (int j = arr.length - 1; j >= 0; j--)
            arr[j] = stack.pop();
        
        return arr;
    }
}