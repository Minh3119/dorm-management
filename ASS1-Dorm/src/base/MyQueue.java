package base;

import java.util.LinkedList;

public class MyQueue {
    
    LinkedList<TreeNode> queue;

    public MyQueue() {
        this.queue = new LinkedList<TreeNode>();
    }

    public void clear() {
        this.queue.clear();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void enqueue(TreeNode x) {
        this.queue.addLast(x);
    }
    
    public TreeNode dequeue() {
        if (isEmpty()) return null;
        TreeNode p = this.queue.removeFirst();
        return p;
    }
    
    public TreeNode front() {
        if (isEmpty()) return null;
        return this.queue.getFirst();
    }   
}
