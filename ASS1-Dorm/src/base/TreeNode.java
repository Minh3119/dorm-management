package base;

public class TreeNode<T> {
    
    public T info;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(T info, TreeNode left, TreeNode right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    public TreeNode(T info) {
        this(info, null, null);
    }

    @Override
    public String toString() {
        return String.valueOf(this.info);
    }
    
}
