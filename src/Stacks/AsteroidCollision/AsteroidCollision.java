package Stacks.AsteroidCollision;
import java.util.Stack;

// https://leetcode.com/problems/asteroid-collision/
// https://www.youtube.com/watch?v=6GGTBM7mwfs

class  AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<asteroids.length; i++){
            
            if (stack.isEmpty() || asteroids[i]>0)
                stack.push(asteroids[i]);
            else{
                while (true){
                    if (stack.peek()<0){
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
        for (int j = stack.size()-1; j>=0; j--)
            arr[j] = stack.pop();
        
        return arr;
    }
}