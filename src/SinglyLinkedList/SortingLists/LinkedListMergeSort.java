package SinglyLinkedList.SortingLists;

public class LinkedListMergeSort {
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int data) {
            this.val = data;
        }
    }


    public ListNode merge(ListNode list1, ListNode list2){
        ListNode sorted = new ListNode(-1);
        ListNode sortedPtr = sorted;

        while (list1!=null && list2!=null){
            if (list1.val < list2.val){
                sortedPtr.next = list1;
                list1 = list1.next;
            }
            else {
                sortedPtr.next = list2;
                list2 = list2.next;
            }
            sortedPtr = sortedPtr.next;
        }

        while (list1!=null){
            sortedPtr.next = list1;
            list1 = list1.next;
            sortedPtr = sortedPtr.next;
        }
        while (list2!=null){
            sortedPtr.next = list2;
            list2 = list2.next;
            sortedPtr = sortedPtr.next;
        }
        return sorted.next;
    }

    public ListNode mergeSort(ListNode head){
        if (head==null || head.next==null)
            return head;

        ListNode fast = head, slow = head, temp = head;
        while (fast!=null && fast.next!=null){
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        temp.next = null;

        ListNode firstSortedPart = mergeSort(head);
        ListNode secondSortedPart = mergeSort(slow);

        return merge(firstSortedPart, secondSortedPart);
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(4);

        ListNode ptr = head;
        while (ptr!=null){
            System.out.print(ptr.val+" ");
            ptr = ptr.next;
        }
        System.out.println();

        head = new LinkedListMergeSort().sortList(head);
        while (head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }
        System.out.println();
    }
}

