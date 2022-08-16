public class LinkedList<T> {
    // Definition of the Node class that LinkedList internally uses
    class Node {
        T item;
        Node next;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }
    }

    private Node head;
    private Node tail;
    // to keep track of the size of the array
    private int size = 0;

    public void push(T item) {
        // TODO: complete the push method
        // You need to consider 2 cases. When list is empty and when it's not
        // Don't forget to increment size!

        Node node = new Node(item);

        if (head == null) {
            head = node;
            tail = head;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;

    }

    public T get(int i) throws ListIndexOutOfBoundException {
        rangeCheck(i); // this throws an exception but we won't catch it here
        // TODO: complete the rest of the method
        // instead of null, you should return the item stored in the node

        Node cursor = head;
        for (int j = 0; j < i; ++j) {

            cursor = cursor.next;
        }

        return cursor.item;
    }

    public int find(T item) {
        // TODO: complete the find method
        int i = 0;
        Node cursor = head;
        // for (i = -1; i < size; i++) {
        while (cursor != null) {
            if (cursor.item.equals(item)) {
                return i;
            }

            cursor = cursor.next;
            i++;
        }
        // }
        return -1;

    }

    public void insert(int i, T item) throws ListIndexOutOfBoundException {
        // TODO: Complete the insert method
        /*
         * there are 3 cases:
         * 1. list is empty (head is null)
         * 2. inserting at the beginning of the list (i = 0)
         * 3. inserting anywhere else
         * 
         * the new node will be inserted at the index and push the
         * current node to right. e.g.
         * [ac1][ac2][ac3]
         * insert(1, ac1.5)
         * [ac1][ac1.5][ac2][ac3]
         * insert(3, ac2.5)
         * [ac1][ac1.5][ac2][ac2.5][ac3]
         */
        Node node = new Node(item);
        if (head == null) { // first case
            // head = new Node(item);
            head = new Node(item);
            tail = head;
            this.size++;
        } else if (i == 0) { // if i=0
            node.next = head;
            head = node;
            this.size++;
        } else { // anywhere
            rangeCheck(i);
            Node cursor = this.head;
            for (int j = 0; j < i - 1; ++j) {
                cursor = cursor.next;
            }
            node.next = cursor.next;
            cursor.next = node;
            this.size++;
        }

    }

    public int size() {
        return size;
    }

    public T delete(int i) throws ListIndexOutOfBoundException, EmptyListException {
        // TODO: complete the delete method
        // use this to store the item and return it in the end
        T deletedItem = null;
        if (head == null) {
            // deletedItem = null;
            return deletedItem;

        } else if (i == 0) {
            deletedItem = head.item;
            head = head.next;
            this.size--;
        } else {
            Node cursor = this.head;

            // for (int j = 0; j <= i; j++) {
            for (int j = 0; j < i - 1; j++) {
                cursor = cursor.next;
            }
            deletedItem = cursor.next.item;
            cursor.next = cursor.next.next;

            size--;
        }
        return deletedItem;

    }

    /*
     * similar to insert, consider 3 cases
     * empty list, insert at the beginning, and anywhere else
     * don't forget to decrement size
     * 
     * 💡 HINT: you can use cursor.next = cursor.next.next
     */

    // Utility methods
    public void rangeCheck(int i) throws ListIndexOutOfBoundException {
        if (i < 0 || i >= size)
            throw new ListIndexOutOfBoundException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        sb.append("[");
        while (cur != null) {
            if (cur.next == null) {
                sb.append(cur.item.toString());
            } else {
                sb.append(cur.item.toString());
                sb.append(", ");
            }
            cur = cur.next;
        }

        sb.append("]");
        return sb.toString();
    }
}

class ListIndexOutOfBoundException extends Exception {
}

class EmptyListException extends Exception {
}
