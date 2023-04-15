package DoublyLinkedList.LFU_Cache.LFUCache_Optimal;
import java.util.HashMap;

// MUST WATCH:
// https://youtu.be/0PSB9y8ehbk
// https://youtu.be/mzqHlAW7jeE
// https://leetcode.com/problems/lfu-cache/description/

/** ************************************ INTUITION *******************************************
 * We will concept of "LRU cache" (maintaining Doubly Linked List) for every possible frequency of value
   in the LFU Cache.
 * For each value of frequency (e.g, 1, 2, 3, etc), there will be one Doubly Linked List, that will
   store the "Nodes" in the Least recently used manner (just like in LRU Cache)

 * Time Complexity:
        * put() : O(1)
        * get() : O(1)
 * Space Complexity: O(capacity)
 */

/**
 * @param "capacity": total capacity of LFU Cache
 * @param "size": current size of LFU cache
 * @param "minFrequency": the minimum accessed frequency the value in the entire LFU cache
 * @param "cacheMap": a hash map that has key to Node mapping, which used for storing all nodes by their keys
 * @param "frequencyToListMap": a hash map that has key to doubly linked list mapping, which used for storing all
                              double linked list by their frequencies
 */
public class LFUCache {
    private final HashMap<Integer, DoublyLinkedList> frequencyToListMap;
    private final HashMap<Integer, Node> cacheMap;
    private int size;
    private final int capacity;
    private int leastFrequency;

    public LFUCache(int capacity) {
        this.frequencyToListMap = new HashMap<>();
        this.cacheMap = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.leastFrequency = 0;
    }

    // get node's value by key, and then update node frequency as well as position of that node
    // according to frequency
    public int get(int key) {
        Node node = cacheMap.get(key);
        if (node == null || capacity == 0)
            return -1;

        updateNodePosition(node);
        return node.value;
    }

    /*
     * add new node into LFU cache, as well as Double linked list
     * condition 1: if LFU cache has input key, update node value and node position in list
     * condition 2: if LFU cache does NOT have input key
     *  - sub condition 1: if LFU cache does NOT have enough space, remove the Least Recent Used node
     *    in minimum frequency list, then add new node
     *  - sub condition 2: if LFU cache has enough space, add new node directly
     * **/
    public void put(int key, int value) {
        if (capacity == 0)
            return;
        Node node = cacheMap.get(key);

        if (node != null){
            node.value = value;
            updateNodePosition(node);
        }
        else{
            if (size == capacity){
                // If size is full, get the Doubly Linked list with the least frequency
                DoublyLinkedList leastFrequentList = frequencyToListMap.get(leastFrequency);
                Node leastFrequentNode = leastFrequentList.tail.prev;
                leastFrequentList.remove(leastFrequentNode);
                cacheMap.remove(leastFrequentNode.key);
                size--;
            }

            // Get the Doubly Linked List with frequency '1', because new Node will have frequency 1
            // and then add new node into the list, as well as into LFU cache
            DoublyLinkedList leastFrequentList = frequencyToListMap.getOrDefault(1, new DoublyLinkedList());
            node = new Node(key, value);
            leastFrequentList.addNodeToFront(node);
            frequencyToListMap.put(1, leastFrequentList);
            cacheMap.put(key, node);

            // Reset minimum frequency to 1, because of addition of new node. New node is accessed only once till now
            leastFrequency = 1;
            size++;
        }
    }

    private void updateNodePosition(Node node){
        DoublyLinkedList oldDLL = frequencyToListMap.get(node.frequency);
        oldDLL.remove(node);

        // if current Node is the last node in list, which has the lowest frequency and current node is the only node in that list
        // Then increase min. frequency value by 1, this min. frequency will be same as that of current Node
        if (leastFrequency == node.frequency  && oldDLL.isEmpty())
            leastFrequency++;

        node.frequency++;

        // Add Current node to another list has current frequency + 1,
        // if we do not have the list with this frequency, initialize it
        DoublyLinkedList newDLL = frequencyToListMap.getOrDefault(node.frequency, new DoublyLinkedList());
        newDLL.addNodeToFront(node);
        frequencyToListMap.put(node.frequency, newDLL);
    }


    /** ********************************* Utility Class ****************************************
     Class "Doubly Linked List"
     Same as created in LRU Cache with same functions of "addNodeInFront()" & "removeLastNode()"
     * @param "listSize": current size of double linked list
     * @param "head": head node of double linked list
     * @param "tail": tail node of double linked list
     */
    static class DoublyLinkedList{
        Node head, tail;
        int size;

        public DoublyLinkedList(){
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            this.size = 0;
            head.next = tail;
            tail.prev = head;
        }

        /* Add new node just after the head of list and increase list size by 1
         * Same as "addNode()" in LRU Cache
         */
        public void addNodeToFront(Node node){
            Node temp = head.next;
            head.next = node;
            node.prev = head;
            node.next = temp;
            temp.prev = node;
            size++;
        }

        /* Remove input node and decrease list size by 1
         * Same as "removeLastNode()" in LRU Cache
         */
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            size--;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }


    /** ********************************* Utility Class ****************************************
     Class "Doubly Linked List Node"
     Attributes are Node{key, value, nextNode, previousNode, frequencyOfAccessingThisNode}
     * @param "key": node key
     * @param "value": node value
     * @param "frequency": Accessed frequency count of current node (no. of times the nodes is accessed)
     (all nodes connected in same double linked list has same frequency)
     * @param "prev": previous pointer of current node
     * @param "next": next pointer of current node
     */
    static class Node {
        int key, value, frequency;
        Node next, prev;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }
}
