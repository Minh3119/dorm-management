/**
 *
 * @author Group 4
 */
package util;

public class MyLinkedList <T> {
    Node<T> head;
    Node<T> tail;

    public MyLinkedList() {
    }
    
    boolean isEmpty() {
        return head==null;
    }
    
    void clear() {
        head = null;
        tail = null;
    }
    
    protected void addLast(T x) {
        Node<T> newNode = new Node(x);
        
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        
        tail.next = newNode;
        tail = newNode;
    }
    
    public void addManyLast(T[] a) {
        for (int i=0; i<a.length; i++) {
            addLast(a[i]);
        }
    }
    
    void visit(Node<T> p) {
        System.out.println(p.info + " ");
    }
    
    public void traverse() {
        Node<T> p = head;
        for (;p != null;) {
            visit(p);
            p = p.next;
        }
        System.out.println("");
    }
    
    protected void addFirst(T x) {
        Node<T> newNode = new Node(x);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }       
    }
    
    public void removeFirst() {
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
    
    public void removeLast() {
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
    
    public void addManyFirst(T[] a) {
        for (int i=a.length-1; i>=0; i--) {
            addFirst(a[i]);
        }
    }

    public Node<T> getFirst() {
        return this.head;
    }
    
    public Node<T> getLast() {
        return this.tail;
    }
    
    public Node<T> get(T x) {
        Node p = this.head;
        while (p != null) {
            if (p.info == x) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
    
    public Node<T> find(Node<T> p) {
        if (p == null) {
            return null;
        }
        
        Node<T> a = this.head;
        while (a != null) {
            if (a.info == p.info) {
                return a;
            }
            a = a.next;
        }
        return null;
    }
    
    public Node<T> getNext(Node<T> p) {
        if (p != null) {
            return p.next;
        }
        return null;
    }
    
    public Node<T> getPrev(Node<T> p) {
        if (p == null) { 
            return null;
        }
        
        Node<T> a = head;
        while (a != null) {
            if (a.next == p) {
                return a;
            }
            a = a.next;
        }
        return null;
    }

    public int size() {
        int i = 0;
        Node n = head;
        while (n != null) {
            i++;
            n = n.next;
        }
        return i;
    }
    
    public Node<T> getByIndex(int index) {
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
    
    public void insertAfter(Node<T> p, T x) {
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
    
    public void insertBefore(Node<T> p, T x) {
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
    
    public void insert(int index, T x) {
        Node n = getByIndex(index);
        
        if (n == null) {
            return;
        }
        
        insertBefore(n, x);
    }

    public void remove(Node<T> p) {
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
    
    // remove by index
    public void remove(int x) {
        Node<T> n = getByIndex(x);
        remove(n);
    }
    
    public void removeAfter(Node<T> p) {
        Node<T> n = find(p);
        
        if (n == null) {
            return;
        }
        
        remove(getNext(n));
    }
    
    public void removeBefore(Node<T> p) {
        Node<T> n = find(p);
        
        if (n == null) { 
            return;
        }
        
        remove(getPrev(n));
    }
    
    public void set(Node<T> p, T x) {
        Node n = find(p);
        
        if (n == null) { 
            return;
        }
        
        n.info = x;
    }
    
    public void swap(Node<T> p, Node<T> q) {
        T temp = p.info;
        p.info = q.info;
        q.info = temp;
    }

    public void reverse() {
        Node<T> next, current, previous;
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
