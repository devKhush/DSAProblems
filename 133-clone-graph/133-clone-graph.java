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
        
        Node[] nodeMap = new Node[101];
        Queue<Node> bfsQueue = new ArrayDeque<>();
        
        int startVertex = node.val;
        
        nodeMap[startVertex] = new Node(startVertex);
        bfsQueue.add(node);
        
        while (!bfsQueue.isEmpty()){
            Node vertex = bfsQueue.remove();
            Node vertexInNewGraph = nodeMap[vertex.val];

            for (Node neighbour : vertex.neighbors){
                if (nodeMap[neighbour.val] == null){
                    nodeMap[neighbour.val] = new Node(neighbour.val);
                    bfsQueue.add(neighbour);
                }
                
                Node adjacentInNewGraph = nodeMap[neighbour.val];
                adjacentInNewGraph.neighbors.add(vertexInNewGraph);
            }
        }
        return nodeMap[startVertex]; 
    }
}