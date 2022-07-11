package Stacks.AsteroidCollision;
import java.util.Stack;

// https://leetcode.com/problems/asteroid-collision/
// https://www.youtube.com/watch?v=6GGTBM7mwfs

class  AsteroidCollision {
    // Time Complexity: O(n)
    // Space Complexity: O(n)
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


    // Clean Code 1
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] asteroidCollision_CleanCode_1(int[] asteroids) {
        int n = asteroids.length;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++){
            while (!stack.isEmpty()  &&  stack.peek() > 0  && asteroids[i] < 0  &&  stack.peek() < -asteroids[i])
                stack.pop();

            if (!stack.isEmpty()  &&  stack.peek() > 0  && asteroids[i] < 0  &&  stack.peek() == -asteroids[i])
                stack.pop();

            else if (!stack.isEmpty()  &&  stack.peek() > 0  && asteroids[i] < 0  &&  stack.peek() > -asteroids[i])
                continue;
            else
                stack.push(asteroids[i]);
        }

        int[] collidedAsteroid = new int[stack.size()];
        for (int i = collidedAsteroid.length - 1;  i >= 0;  i--)
            collidedAsteroid[i] = stack.pop();

        return collidedAsteroid;
    }




    // Clean Code 2
    // Stack using arrays are much fatser
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] asteroidCollision_CleanCode_2(int[] asteroids) {
        int n = asteroids.length;

        int[] stack = new int[n];
        int stackSize = 0;

        for (int i = 0; i < n; i++){
            while (stackSize > 0  && asteroids[i] < 0 &&  stack[stackSize - 1] > 0  &&  stack[stackSize - 1] < -asteroids[i])
                stackSize--;
            if (stackSize > 0  && asteroids[i] < 0 &&  stack[stackSize - 1] > 0  &&  stack[stackSize - 1] == -asteroids[i])
                stackSize--;
            else if (stackSize > 0  && asteroids[i] < 0 &&  stack[stackSize - 1] > 0  &&  stack[stackSize - 1] > -asteroids[i])
                continue;
            else
                stack[stackSize++] = asteroids[i];
        }

        int[] collidedAsteroid = new int[stackSize];
        for (int i = collidedAsteroid.length - 1;  i >= 0;  i--)
            collidedAsteroid[i] = stack[--stackSize];

        return collidedAsteroid;
    }
}