class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int V = numCourses;
        int[][] edges = prerequisites;
        
        ArrayList<Integer>[] adjList = adjacencyList(V, edges);
        
        return checkCycleInDirectedGraph(V, adjList);
    }
    
    private boolean checkCycleInDirectedGraph(int V, ArrayList<Integer>[] adjList){
        int[] inDegree = new int[V];
        
        for (int vertex = 0; vertex < V; vertex++)
            for (int neighbour : adjList[vertex])
                inDegree[neighbour]++;
        
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        
        for (int vertex = 0; vertex < V; vertex++)
            if (inDegree[vertex] == 0)
                bfsQueue.add(vertex);
        
        int verticesCount = 0;
        
        while (!bfsQueue.isEmpty()){
            int vertex = bfsQueue.remove();
            verticesCount++;
            
            for (int neighbour : adjList[vertex]){
                inDegree[neighbour]--;
                
                if (inDegree[neighbour] == 0)
                    bfsQueue.add(neighbour);
            }
        }
        return verticesCount == V; 
    }
    
    private ArrayList<Integer>[] adjacencyList(int V, int[][] edges){
        ArrayList<Integer>[] adjList = new ArrayList[V];
        
        for (int i = 0; i < V; i++)
            adjList[i] = new ArrayList<>();
        
        for (int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            adjList[u].add(v);
        }
        return adjList;
    }
}