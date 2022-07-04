class LRUCache {
    private Node head, tail;
    private HashMap<Integer, Node> map;
    private int size, capacity;
    
    public LRUCache(int capacity) {
        this.head = new Node(0, 0);    
        this.tail = new Node(0, 0);
        this.map = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        remove(node);
        addInFront(node);
        return node.data;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        
        if (node != null){
            node.data = value;
            remove(node);
            addInFront(node);
        }
        else{
            if (size == capacity){
                Node toRemove = tail.prev;
                remove(toRemove);
                map.remove(toRemove.key);

                Node nodeToAdd = new Node(key, value);
                addInFront(nodeToAdd);
                map.put(key, nodeToAdd);
            }
            else if (size < capacity){
                Node nodeToAdd = new Node(key, value);
                addInFront(nodeToAdd);
                map.put(key, nodeToAdd);
                size++;
            }
        }
    }
    
    private void remove(Node node){
        Node beforeNode = node.prev;
        Node afterNode = node.next;
        
        beforeNode.next = afterNode;
        afterNode.prev = beforeNode;
    }
    
    private void addInFront(Node node){
        Node nextToHead = head.next; 
        head.next = node;
        node.next = nextToHead;
        
        nextToHead.prev = node;
        node.prev = head;
    }
    
    class Node{
        int key, data;
        Node prev, next;
        public Node(int key, int data){
            this.key = key;
            this.data = data;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */