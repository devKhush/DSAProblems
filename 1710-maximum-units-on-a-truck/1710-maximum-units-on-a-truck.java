class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a,b) -> (b[1] - a[1]));
        
        int totalBoxesAdded = 0, totalUnits = 0;
        
        for (int i = 0; i < boxTypes.length; i++){
            if (boxTypes[i][0] + totalBoxesAdded <= truckSize){
                totalBoxesAdded += boxTypes[i][0];
                totalUnits += boxTypes[i][1] * boxTypes[i][0];
            }
            else{
                int boxToAdd = truckSize - totalBoxesAdded;
                totalUnits += boxTypes[i][1] * boxToAdd;
                totalBoxesAdded += boxToAdd;
                break;
            }
        }
        return totalUnits;
    }
}