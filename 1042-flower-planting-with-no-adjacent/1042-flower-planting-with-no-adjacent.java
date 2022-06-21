class Solution {
    public int[] gardenNoAdj(int n, int[][] graphEdges) {
        int[] flowerPlacedMap = new int[n];
        int m = 4;

        boolean gardenDecorated = canDecorateGardensWithFlowers(1, m, n, flowerPlacedMap, graphEdges);
        return flowerPlacedMap;
    }

    private boolean canDecorateGardensWithFlowers(int garden, int m, int n, int[] flowerPlacedMap, int[][] graphEdges){
        if (garden == n +1)
            return true;

        for (int flower = 1; flower <= m; flower++){
            if (canPlaceFlowerInGarden(garden, flower, flowerPlacedMap, graphEdges)){
                flowerPlacedMap[garden - 1] = flower;

                if (canDecorateGardensWithFlowers(garden + 1, m, n, flowerPlacedMap, graphEdges))
                    return true;

                flowerPlacedMap[garden - 1] = 0;
            }
        }
        return false;
    }

    private boolean canPlaceFlowerInGarden(int garden, int flower, int[] flowerPlacedMap, int[][] graphEdges){
        for (int[] graphEdge : graphEdges){
            if (graphEdge[0] == garden) {
                int neighbourGarden = graphEdge[1];
                if (flowerPlacedMap[neighbourGarden - 1] == flower) {
                    return false;
                }
            }

            if (graphEdge[1] == garden) {
                int neighbourGarden = graphEdge[0];
                if (flowerPlacedMap[neighbourGarden - 1] == flower)
                    return false;
            }
        }
        return true;
    }
    
    
}