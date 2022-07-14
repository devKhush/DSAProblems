class Solution {
    public boolean isBipartite(int[][] adjList) {
        int V = adjList.length;
        int[] color = new int[V];
        
        for (int vertex = 0; vertex < V; vertex++)
            if (color[vertex] == 0)
                if (!twoColoring_BFS(vertex, color, adjList))
                    return false;
        
        return true;
    }
    
    public boolean twoColoring_BFS(int vertex, int[] color, int[][] adjList){
        Queue<Integer> queue = new ArrayDeque<>();
        
        color[vertex] = 1;
        queue.add(vertex);
        
        while (!queue.isEmpty()){
            int currentVertex = queue.remove();
            
            for (int adjacentVertex : adjList[currentVertex]){
                if (color[adjacentVertex] == 0){
                    color[adjacentVertex] = color[currentVertex] == 1 ? 2 : 1;
                    queue.add(adjacentVertex);
                }
                else if (color[adjacentVertex] == color[currentVertex])
                    return false;
            }
        }
        return true;
    }
}