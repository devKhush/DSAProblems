class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a,b) -> (b[1] - a[1]));
        
        int sizeFilled = 0;
        int maxBoxUnits = 0;
        
        for (int i = 0; i < boxTypes.length; i++){
            if (boxTypes[i][0] + sizeFilled <= truckSize){
                sizeFilled += boxTypes[i][0];
                maxBoxUnits += boxTypes[i][0] * boxTypes[i][1];
            }
            else{
                int sizeToFill = truckSize - sizeFilled;
                sizeFilled += sizeToFill;
                maxBoxUnits +=  sizeToFill * boxTypes[i][1];
                break;
            }
        }
        return maxBoxUnits;
    }
}