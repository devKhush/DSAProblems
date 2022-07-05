
public class LFUCache {
    private HashMap<Integer, DoublyLinkedList> frequencyTolistMap;
    private HashMap<Integer, DLLNode> cacheMap;
    private int size, capacity;
    private int leastFrequencyKey;

    public LFUCache(int capacity) {
        this.frequencyTolistMap = new HashMap<>();
        this.cacheMap = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.leastFrequencyKey = 0;
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key) || capacity == 0)
            return -1;

        DLLNode node = cacheMap.get(key);
        updateNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;
        DLLNode node = cacheMap.get(key);

        if (node != null){
            node.value = value;
            updateNode(node);
        }
        else{
            if (size == capacity){
                DoublyLinkedList leastFrequentList = frequencyTolistMap.get(leastFrequencyKey);
                DLLNode leastFrequentNode = leastFrequentList.tail.prev;
                leastFrequentList.removeNode(leastFrequentNode);
                cacheMap.remove(leastFrequentNode.key);
                size--;
            }

            DLLNode nodeToInsert = new DLLNode(key, value);
            DoublyLinkedList leastFrequentList = frequencyTolistMap.getOrDefault(1, new DoublyLinkedList());
            leastFrequentList.addNodeInFront(nodeToInsert);
            frequencyTolistMap.put(1, leastFrequentList);
            cacheMap.put(key, nodeToInsert);

            leastFrequencyKey = 1;
            size++;
        }
    }

    private void updateNode(DLLNode node){
        DoublyLinkedList earlierDLL = frequencyTolistMap.get(node.frequency);
        earlierDLL.removeNode(node);

        if (leastFrequencyKey == node.frequency  && earlierDLL.size == 0)
            leastFrequencyKey++;

        node.frequency++;
        DoublyLinkedList newDLLToPutInNode = frequencyTolistMap.getOrDefault(node.frequency, new DoublyLinkedList());
        newDLLToPutInNode.addNodeInFront(node);
        frequencyTolistMap.put(node.frequency, newDLLToPutInNode);
    }


    /** ********************************* Utility Class ****************************************
     Class "Doubly Linked List"
     Same as created in LRU Cache with same functions of "addNodeInFront()" & "removeLastNode()"
     */
    static class DoublyLinkedList{
        DLLNode head, tail;
        int size;

        public DoublyLinkedList(){
            this.head = new DLLNode(0, 0);
            this.tail = new DLLNode(0, 0);
            this.size = 0;
            head.next = tail;
            tail.prev = head;
        }

        // Same as "addNode()" in LRU Cache
        public void addNodeInFront(DLLNode node){
            DLLNode nextToHead = head.next;
            head.next = node;
            node.next = nextToHead;
            nextToHead.prev = node;
            node.prev = head;
            size++;
        }

        // Same as "removeLastNode()" in LRU Cache
        public void removeNode(DLLNode node){
            DLLNode beforeNode = node.prev;
            DLLNode afterNode = node.next;

            beforeNode.next = afterNode;
            afterNode.prev = beforeNode;
            node.next = null;
            node.prev = null;
            size--;
        }
    }


    /** ********************************* Utility Class ****************************************
     Class "Doubly Linked List Node"
     Attributes are Node{key, value, nextNode, previousNode, frequencyOfAccessingThisNode}
     */
    static class DLLNode{
        int key, value, frequency;
        DLLNode next, prev;

        public DLLNode(int key, int value){
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */