class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        
        int[] stack = new int[n];
        int stackSize = 0;
        
        for (int i = 0; i < n; i++){
            while (stackSize > 0  &&  stack[stackSize - 1] > 0  && asteroids[i] < 0  &&  stack[stackSize - 1] < -asteroids[i])
                stackSize--;
            
            if (stackSize > 0  &&  stack[stackSize - 1] > 0  && asteroids[i] < 0  &&  stack[stackSize - 1] == -asteroids[i])
                stackSize--;
            
            else if (stackSize > 0  &&  stack[stackSize - 1] > 0  && asteroids[i] < 0  &&  stack[stackSize - 1] > -asteroids[i])
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