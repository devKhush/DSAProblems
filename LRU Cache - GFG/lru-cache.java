// { Driver Code Starts
import java.io.*;
import java.util.*;
import java.lang.*;

  public class LRUDesign {

    public static void main(String[] args) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {

            int capacity = Integer.parseInt(read.readLine());
            int queries = Integer.parseInt(read.readLine());
            LRUCache cache = new LRUCache(capacity);
            String str[] = read.readLine().trim().split(" ");
            int len = str.length;
            int itr = 0;

            for (int i = 0; (i < queries) && (itr < len); i++) {
                String queryType = str[itr++];
                if (queryType.equals("SET")) {
                    int key = Integer.parseInt(str[itr++]);
                    int value = Integer.parseInt(str[itr++]);
                    cache.set(key, value);
                }
                if (queryType.equals("GET")) {
                    int key = Integer.parseInt(str[itr++]);
                    System.out.print(cache.get(key) + " ");
                }
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


// design the class in the most optimal way

class LRUCache{
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
    
    public void set(int key, int value) {
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
