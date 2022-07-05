class LFUCache {
    private Node head, tail;
    private HashMap<Integer, Node> map;
    int size, capacity;

    public LFUCache(int capacity) {
        this.head = new Node(0, 0, 0);
        this.tail = new Node(0, 0, 0);
        this.map = new HashMap<>();
        this.size = 0;
        this.capacity = capacity; 
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key) || capacity == 0)
            return -1;
        
        Node node = map.get(key);
        node.frequencyCount++;
        removeNode(node);
        addNode(node);
        return node.data;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) 
            return;
        
        Node node = map.get(key);
        
        if (node != null){
            node.frequencyCount++;
            node.data = value;
            
            removeNode(node);
            addNode(node);
        }
        else{
            if (size == capacity){
                Node nodeToRemove = tail.prev;
                removeNode(nodeToRemove);
                map.remove(nodeToRemove.key);
                
                Node nodeToAdd = new Node(key, value, 1);
                addNode(nodeToAdd);
                map.put(key, nodeToAdd);
            }
            else if (size < capacity){
                Node nodeToAdd = new Node(key, value, 1);
                
                addNode(nodeToAdd);
                map.put(key, nodeToAdd);
                size++;
            }
        }
    }
    
    private void addNode(Node nodeToAdd){
        Node ptr = head.next;
        
        while (ptr.frequencyCount > nodeToAdd.frequencyCount)
            ptr = ptr.next;
        
        Node addAfterNode = ptr.prev;
        
        addAfterNode.next = nodeToAdd;
        nodeToAdd.next = ptr;
        ptr.prev = nodeToAdd;
        nodeToAdd.prev = addAfterNode;
    }
    
    
    private void removeNode(Node node){
        Node beforeNode = node.prev;
        Node afterNode = node.next;
        
        beforeNode.next = afterNode;
        afterNode.prev = beforeNode;
        
        node.next = null;
        node.prev = null;
    }
    
    static class Node{
        int data, key, frequencyCount;
        Node next, prev;
        public Node(int key, int value, int count){
            this.key = key;
            this.data = value;
            this.frequencyCount = count;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */