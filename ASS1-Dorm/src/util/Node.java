/**
 *
 * @author Admin
 */
package util;

public class Node<T> {
    T info;
    Node next;

    public Node() {}

    public Node(T info, Node next) {
        this.info = info;
        this.next = next;
    }

    public Node(T info) {
        this(info, null);
    }
}
