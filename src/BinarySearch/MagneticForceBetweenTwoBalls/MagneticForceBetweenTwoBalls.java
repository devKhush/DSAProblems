package BinarySearch.MagneticForceBetweenTwoBalls;
import java.util.Arrays;

// PRE-REQUISITE: "AGGRESSIVE COWS: BINARY SEARCH"
//  SAME AS THAT QUESTION

class MagneticForceBetweenTwoBalls {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        
        int low = 1, high = position[position.length - 1] - position[0];
        int maximumDistance = -1;
        
        while (low <= high){
            int midDistance  = low + (high - low)/2;
            
            if (canDistributeBallsIntoBasket(position, m, midDistance)){
                maximumDistance = midDistance;
                low = midDistance + 1;
            }
            else
                high = midDistance - 1;
        }
        return maximumDistance;
    }
    
    private boolean canDistributeBallsIntoBasket(int[] positions, int balls, int minDistance){
        int ballsAdded = 1;
        int lastBallPosition = positions[0];
        
        for (int i = 1; i < positions.length; i++){
            if (positions[i] - lastBallPosition >= minDistance){
                ballsAdded++;
                lastBallPosition = positions[i];
            }
            if (ballsAdded == balls)
                return true;
        }
        return false;
    }
}