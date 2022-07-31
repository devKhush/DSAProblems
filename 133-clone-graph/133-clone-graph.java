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
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        
        HashMap<Integer, Node> nodeMap = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        Queue<Node> bfsQueue = new ArrayDeque<>();
        
        int startVertex = node.val;
        Node copyNode = new Node(startVertex);
        
        visited.add(startVertex);
        nodeMap.put(startVertex, copyNode);
        bfsQueue.add(node);
        
        while (!bfsQueue.isEmpty()){
            Node vertex = bfsQueue.remove();
            Node vertexInNewGraph = nodeMap.get(vertex.val);

            
            for (Node neighbour : vertex.neighbors){
                if (!nodeMap.containsKey(neighbour.val))
                    nodeMap.put(neighbour.val, new Node(neighbour.val));
                
                Node adjacentInNewGraph = nodeMap.get(neighbour.val);
                adjacentInNewGraph.neighbors.add(vertexInNewGraph);
                
                if (!visited.contains(neighbour.val)){
                    bfsQueue.add(neighbour);
                    visited.add(neighbour.val);
                }
            }
        }
        return nodeMap.get(startVertex);
        
    }
}