/**
 *
 * @author Admin
 */
package util;

public class MyLinkedList {
    Node head;
    Node tail;

    public MyLinkedList() {
    }
    
    boolean isEmpty() {
        return head==null;
    }
    
    void clear() {
        head = null;
        tail = null;
    }
    
    void addLast(int x) {
        Node newNode = new Node(x);
        
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        
        tail.next = newNode;
        tail = newNode;
    }
    
    void addManyLast(int[] a) {
        for (int i=0; i<a.length; i++) {
            addLast(a[i]);
        }
    }
    
    void visit(Node p) {
        System.out.println(p.info + " ");
    }
    
    void traverse() {
        Node p = head;
        for (;p != null;) {
            visit(p);
            p = p.next;
        }
        System.out.println("");
    }
    
    void addFirst(int x) {
        Node newNode = new Node(x);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }       
    }
    
    void removeFirst() {
        if (!isEmpty()){
            if (head == tail) {
                clear();
                return;
            }
            
            Node newHead = head.next;
            head.next = null;
            head = newHead; 
        }
    }
    
    void removeLast() {
        if (isEmpty()) {
            return;
        }
        
        if (head == tail) {
            clear();
            return;
        }
        
        Node p = head;
        while (p.next != tail) {
            p = p.next;
        }
        
        p.next = null;
        tail = p;
    }
    
    void printInfo() {
        System.out.println("Head = " + head);
        System.out.println("Tail = " + tail);
    }
    
    void addManyFirst(int[] a) {
        for (int i=a.length-1; i>=0; i--) {
            addFirst(a[i]);
        }
    }
    
    // CQ2_1
    Node getFirst() {
        return this.head;
    }
    
    Node getLast() {
        return this.tail;
    }
    
    Node get(int x) {
        Node p = this.head;
        while (p != null) {
            if (p.info == x) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
    
    Node find(Node p) {
        if (p == null) {
            return null;
        }
        
        Node a = this.head;
        while (a != null) {
            if (a.info == p.info) {
                return a;
            }
            a = a.next;
        }
        return null;
    }
    
    Node getNext(Node p) {
        if (p != null) {
            return p.next;
        }
        return null;
    }
    
    Node getPrev(Node p) {
        if (p == null) { 
            return null;
        }
        
        Node a = head;
        while (a != null) {
            if (a.next == p) {
                return a;
            }
            a = a.next;
        }
        return null;
    }
    
    // CQ2_2
    int size() {
        int i = 0;
        Node n = head;
        while (n != null) {
            i++;
            n = n.next;
        }
        return i;
    }
    
    Node getByIndex(int index) {
        Node p = head;
        int i = 0;
        while (p != null) {
            if (i == index) {
                return p;
            }
            p = p.next;
            i++;
        }
        return null;
    }
    
    void insertAfter(Node p, int x) {
        Node n = find(p);
        
        if (n == null) {
            return;
        }
        
        if (n.next == null) {
            addLast(x);
        } else {
            Node newNode = new Node(x);
            newNode.next = n.next;
            n.next = newNode;
        }
    }
    
    void insertBefore(Node p, int x) {
        if (p!=null && p == head) {
            addFirst(x);
            return;
        }
        
        Node prev = getPrev(p);
        
        if (prev == null) {
            return;
        }
        
        Node newNode = new Node(x);
        newNode.next = prev.next;
        prev.next = newNode;
    }
    
    void insert(int index, int x) {
        Node n = getByIndex(index);
        
        if (n == null) {
            return;
        }
        
        insertBefore(n, x);
    }
    
    //CQ2_3
    void remove(Node p) {
        if (p == null) { 
            return;
        }
        
        if (p == head) {
            removeFirst();
        }
        else if (p == tail) {
            removeLast();
        }
        else {
            getPrev(p).next = p.next;
            p.next = null;
        }
    }
    
    void remove(int x) {
        Node n = getByIndex(x);
        remove(n);
    }
    
    void removeAfter(Node p) {
        Node n = find(p);
        
        if (n == null) { 
            return;
        }
        
        remove(getNext(n));
    }
    
    void removeBefore(Node p) {
        Node n = find(p);
        
        if (n == null) { 
            return;
        }
        
        remove(getPrev(n));
    }
    
    void set(Node p, int x) {
        Node n = find(p);
        
        if (n == null) { 
            return;
        }
        
        n.info = x;
    }
    
    Node max() {
        Node p = head;
        Node m = p;
        while (p != null) {
            if (p.info > m.info) {
                m = p;
            }
            p = p.next;
        }
        return m;
    }
    
    Node secondMax() {
        Node max = max();
        Node p = head;
        Node secondMax = null;

        // Traverse the list to find the second maximum
        while (p != null) {
            if (p.info < max.info) {
                if (secondMax == null || p.info > secondMax.info) {
                    secondMax = p;
                }
            }
            p = p.next;
        }

        return secondMax;
    }

    
    void swap(Node p, Node q) {
        int temp = p.info;
        p.info = q.info;
        q.info = temp;
    }
    
    void sort() {
        Node p = head;
        Node q;
        while (p != null) {
            q = p.next;
            while (q != null) {
                if (p.info > q.info) {
                    swap(p, q);
                }
                q = q.next;
            }
            p = p.next;
        }
    }
    
    void sort(Node p, Node q) {
        while (p != null) {
            Node t = p.next;
            while (t != null) {
                if (t == q.next) {
                    return;
                }
                if (p.info > t.info) {
                    swap(p, t);
                }
                t = t.next;
            }
            p = p.next;
        }
    }
    
    void reverse() {
        Node next, current, previous;
        current = head;
        previous = null;
        
        tail = head;
        
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;        
    }
}
