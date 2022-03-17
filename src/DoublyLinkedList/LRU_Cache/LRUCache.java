package DoublyLinkedList.LRU_Cache;
import java.util.HashMap;

// https://leetcode.com/problems/lru-cache/
// https://www.youtube.com/watch?v=NDpwj0VWz1U

public class LRUCache {

    // Dummy head  & Tail node
    private final Node head;
    private final Node tail;
    private final HashMap<Integer, Node> keyNodePairs;
    private int capacity; 
    
    public LRUCache(int capacity) {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        keyNodePairs = new HashMap<>();
    }
    
    public int get(int key) {
        int value = -1;
        Node node = keyNodePairs.get(key);
        if (node!=null){
            value = node.value;
            this.remove(node);
            this.addToHead(node);
        }
        return value;
    }
    
    public void put(int key, int value) {
        if (keyNodePairs.containsKey(key)){
            Node updateNode = keyNodePairs.get(key);
            updateNode.value = value;
            this.remove(updateNode);
            this.addToHead(updateNode);
        }
        else{
            Node toPut = new Node(key, value);
            if (keyNodePairs.size() == this.capacity){
                keyNodePairs.remove(tail.prev.key);
                keyNodePairs.put(key, toPut);
                this.remove(tail.prev);
                this.addToHead(toPut);
            }
            else{
                this.addToHead(toPut);
                keyNodePairs.put(key, toPut);
            }
        }
    }
    
    // Consider as adding a node in the Doubly LL in b/w of the list (always add after head)
    public void addToHead(Node node){
        Node nextToHead = head.next;
        
        head.next = node;
        node.prev = head;
        
        node.next = nextToHead;
        nextToHead.prev = node;
    }
    
    // Consider as remove a node in the Doubly LL in b/w of the list (always remove before tail)
    public void remove(Node node){
        Node nextNode = node.next;
        Node prevNode = node.prev;
        
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
    
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}
