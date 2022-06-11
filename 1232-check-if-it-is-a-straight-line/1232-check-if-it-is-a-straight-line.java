class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0];
        int x1 = coordinates[1][0];
        int y0 = coordinates[0][1];
        int y1 = coordinates[1][1];
        
        int dy = y1 - y0;
        int dx = x1 - x0;
        
        for (int i = 1; i < coordinates.length; i++){
            x0 = coordinates[i-1][0];
            x1 = coordinates[i][0];
            y0 = coordinates[i-1][1];
            y1 = coordinates[i][1];
            
            if (dy * (x1 - x0) != (y1 - y0) * dx)
                return false;
        }
        return true;
    }
}