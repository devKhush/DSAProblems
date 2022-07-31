    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public void dfs(Node vertex, HashMap<Integer, Node> visited){
        Node vertexInNewGraph = visited.get(vertex.val);
        
        for (Node neighbour : vertex.neighbors){
            if (!visited.containsKey(neighbour.val)){
                visited.put(neighbour.val, new Node(neighbour.val));
                Node adjacentInNewGraph = visited.get(neighbour.val);
                adjacentInNewGraph.neighbors.add(vertexInNewGraph);
                
                dfs(neighbour, visited);
            }
            else{
                Node adjacentInNewGraph = visited.get(neighbour.val);
                adjacentInNewGraph.neighbors.add(vertexInNewGraph);
            }
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        HashMap<Integer, Node> visited = new HashMap<>();
        
        int startVertex = node.val;
        visited.put(startVertex, new Node(startVertex));
        dfs(node, visited);
        return visited.get(startVertex);
    }
    
    
    public Node cloneGraph_BFS(Node node) {
       if (node == null)
            return null;
        
        HashMap<Integer, Node> visited = new HashMap<>();
        
        int startVertex = node.val;
        visited.put(startVertex, new Node(startVertex));

        Queue<Node> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(node);

        while (!bfsQueue.isEmpty()){
            Node vertex = bfsQueue.remove();
            Node vertexInNewGraph = visited.get(vertex.val);

            for (Node neighbour : vertex.neighbors){
                if (!visited.containsKey(neighbour.val)){
                    visited.put(neighbour.val, new Node(neighbour.val));
                    bfsQueue.add(neighbour);
                }

                Node adjacentInNewGraph = visited.get(neighbour.val);
                adjacentInNewGraph.neighbors.add(vertexInNewGraph);
            }
        }
        return visited.get(startVertex);
    }
}