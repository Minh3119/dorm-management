/**
 *
 * @author Admin
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
    
    void addLast(T x) {
        Node<T> newNode = new Node(x);
        
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        
        tail.next = newNode;
        tail = newNode;
    }
    
    void addManyLast(T[] a) {
        for (int i=0; i<a.length; i++) {
            addLast(a[i]);
        }
    }
    
    void visit(Node<T> p) {
        System.out.println(p.info + " ");
    }
    
    void traverse() {
        Node<T> p = head;
        for (;p != null;) {
            visit(p);
            p = p.next;
        }
        System.out.println("");
    }
    
    void addFirst(T x) {
        Node<T> newNode = new Node(x);
        
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
    
    void addManyFirst(T[] a) {
        for (int i=a.length-1; i>=0; i--) {
            addFirst(a[i]);
        }
    }
    
    // CQ2_1
    Node<T> getFirst() {
        return this.head;
    }
    
    Node<T> getLast() {
        return this.tail;
    }
    
    Node<T> get(T x) {
        Node p = this.head;
        while (p != null) {
            if (p.info == x) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
    
    Node<T> find(Node<T> p) {
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
    
    Node<T> getNext(Node<T> p) {
        if (p != null) {
            return p.next;
        }
        return null;
    }
    
    Node<T> getPrev(Node<T> p) {
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
    
    Node<T> getByIndex(int index) {
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
    
    void insertAfter(Node<T> p, T x) {
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
    
    void insertBefore(Node<T> p, T x) {
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
    
    void insert(int index, T x) {
        Node n = getByIndex(index);
        
        if (n == null) {
            return;
        }
        
        insertBefore(n, x);
    }
    
    //CQ2_3
    void remove(Node<T> p) {
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
    void remove(int x) {
        Node<T> n = getByIndex(x);
        remove(n);
    }
    
    void removeAfter(Node<T> p) {
        Node<T> n = find(p);
        
        if (n == null) {
            return;
        }
        
        remove(getNext(n));
    }
    
    void removeBefore(Node<T> p) {
        Node<T> n = find(p);
        
        if (n == null) { 
            return;
        }
        
        remove(getPrev(n));
    }
    
    void set(Node<T> p, T x) {
        Node n = find(p);
        
        if (n == null) { 
            return;
        }
        
        n.info = x;
    }
    
//    Node<T> max() {
//        Node<T> p = head;
//        Node<T> m = p;
//        while (p != null) {
//            if (p.info > m.info) {
//                m = p;
//            }
//            p = p.next;
//        }
//        return m;
//    }
    
//    Node<T> secondMax() {
//        Node max = max();
//        Node p = head;
//        Node secondMax = null;
//
//        // Traverse the list to find the second maximum
//        while (p != null) {
//            if (p.info < max.info) {
//                if (secondMax == null || p.info > secondMax.info) {
//                    secondMax = p;
//                }
//            }
//            p = p.next;
//        }
//
//        return secondMax;
//    }

    
    void swap(Node<T> p, Node<T> q) {
        T temp = p.info;
        p.info = q.info;
        q.info = temp;
    }
    
//    void sort() {
//        Node p = head;
//        Node q;
//        while (p != null) {
//            q = p.next;
//            while (q != null) {
//                if (p.info > q.info) {
//                    swap(p, q);
//                }
//                q = q.next;
//            }
//            p = p.next;
//        }
//    }
//    
//    void sort(Node p, Node q) {
//        while (p != null) {
//            Node t = p.next;
//            while (t != null) {
//                if (t == q.next) {
//                    return;
//                }
//                if (p.info > t.info) {
//                    swap(p, t);
//                }
//                t = t.next;
//            }
//            p = p.next;
//        }
//    }
    
    void reverse() {
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
