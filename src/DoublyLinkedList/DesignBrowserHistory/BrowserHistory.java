package DoublyLinkedList.DesignBrowserHistory;

// https://leetcode.com/problems/design-browser-history/description/

public class BrowserHistory {
    /*********************************** Doubly LinkedLiat Solution *************************************/
    private DoublyLinkedList ddl;

    public BrowserHistory(String homepage) {
        this.ddl = new DoublyLinkedList(homepage);
    }

    public void visit(String url) {
        this.ddl.addNew(url);
    }

    public String back(int steps) {
        for (int i = 0; i < steps; i++)
            this.ddl.moveBack();
        return this.ddl.getPtr().url;
    }

    public String forward(int steps) {
        for (int i = 0; i < steps; i++)
            this.ddl.moveNext();
        return this.ddl.getPtr().url;
    }


    /************************************* Doubly Linked List class ************************************
     * All operations of Doubly-Linked-List are of worth O(1) time.
     */
    static class DoublyLinkedList{
        private DDLNode head, tail, ptr;

        public DoublyLinkedList(String homepage){
            this.head = new DDLNode(homepage);
            this.tail = this.head;
            this.ptr = this.head;
        }

        public void moveNext(){
            if (ptr.next != null)
                ptr = ptr.next;
        }

        public void addNew(String url){
            DDLNode toRemove = ptr.next;
            if (toRemove != null)
                toRemove.prev = null;

            DDLNode newPage = new DDLNode(url);
            ptr.next = newPage;
            newPage.prev = ptr;
            this.moveNext();
        }

        public void moveBack(){
            if (ptr.prev != null)
                ptr = ptr.prev;
        }

        public DDLNode getPtr(){
            return this.ptr;
        }

        /*********************************** DDL Node Structure *******************************/
        static class DDLNode{
            String url;
            DDLNode next, prev;
            public DDLNode(String url){
                this.url = url;
            }
        }
    }
}
