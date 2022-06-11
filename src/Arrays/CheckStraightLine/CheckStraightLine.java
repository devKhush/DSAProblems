package Arrays.CheckStraightLine;

class CheckStraightLine {

    // Simple Solution based on the fact that slope of every two points should be same,
    // if all of them are in same straight line
    // TC -> O(n)
    // SC -> O(1)
    private boolean checkStraightLine1(int[][] coordinates) {
        int x0 = coordinates[0][0];
        int x1 = coordinates[1][0];
        int y0 = coordinates[0][1];
        int y1 = coordinates[1][1];

        // Slope will be dy/dx, though not calculating it bcoz, it will give Division by 0 error in case dx = 0
        int dy = y1 - y0;
        int dx = x1 - x0;
        
        for (int i = 1; i < coordinates.length; i++){
            x0 = coordinates[i-1][0];
            x1 = coordinates[i][0];
            y0 = coordinates[i-1][1];
            y1 = coordinates[i][1];

            // Calculate slope between every two points
            // Not directly calculating (dy/dx = (y1-y0)/(x1-x0)) bcoz, it might give Division by 0 error
            // in case dx = 0   or  (x1-x0) = 0
            if (dy * (x1 - x0) != (y1 - y0) * dx)
                return false;
        }
        return true;
    }


    private boolean checkStraightLine2(int[][] coordinates) {
        int x0 = coordinates[0][0];
        int x1 = coordinates[1][0];
        int y0 = coordinates[0][1];
        int y1 = coordinates[1][1];

        // Slope will be dy/dx, though not calculating it bcoz, it will give Division by 0 error in case dx = 0
        int dy = y1 - y0;
        int dx = x1 - x0;

        for (int i = 1; i < coordinates.length; i++){
            x1 = coordinates[i][0];
            y1 = coordinates[i][1];

            // Calculate slope between every current point & starting point
            // Not directly calculating (dy/dx = (y1-y0)/(x1-x0)) bcoz, it might give Division by 0 error
            // in case dx = 0   or  (x1-x0) = 0
            if (dy * (x1 - x0) != (y1 - y0) * dx)
                return false;
        }
        return true;
    }
}