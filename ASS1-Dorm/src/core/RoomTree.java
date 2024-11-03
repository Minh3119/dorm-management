package core;

import base.MyQueue;
import base.TreeNode;
import dto.Room;
import java.util.ArrayList;


public class RoomTree {
    TreeNode root;

    public RoomTree() {
    }

    public void clear() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Room x) {
        if (isEmpty()) {
            //System.out.println("Insert root: " + x);
            TreeNode newNode = new TreeNode(x);
            root = newNode;
            return;
        }

        TreeNode<Room> curr;
        TreeNode<Room> parentOfCurr;
        curr = root;
        parentOfCurr = null;
        while (curr != null) {
            if (curr.info == x) {
                System.out.println("They key " + x + " already exists.");
                return;
            }

            parentOfCurr = curr;
            if (x.compareRcode(curr.info) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        TreeNode newNode = new TreeNode(x);
        if (x.compareRcode(parentOfCurr.info) < 0) {
            //System.out.println("Insert " + parentOfCurr.info + ".left = " + x);
            parentOfCurr.left = newNode;
        } else {
            //System.out.println("Insert " + parentOfCurr.info + ".right = " + x);
            parentOfCurr.right = newNode;
        }
    }

    public void insertMany(Room[] values) {
        for (int i = 0; i < values.length; i++) {
            insert(values[i]);
        }
    }

    public void visit(TreeNode p) {
        System.out.print(p + " ");
    }

    public void preOrder(TreeNode p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void inOrder(TreeNode p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    public void postOrder(TreeNode p) {
        if (p == null) {
            return;
        }

        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    public void breadth() {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        TreeNode p;
        while (!q.isEmpty()) {
            p = (TreeNode) q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            visit(p);
        }
    }

    public void deleteByMerging(Room x) {
        // check if BSTree is empty
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion.");
            return;
        }

        // search node to be deleted
        TreeNode<Room> deleteNode;
        TreeNode parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info.compareRcode(x) == 0) {
                break;
            }

            // continue search
            if (x.compareRcode(deleteNode.info) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }

        // check if found x
        if (deleteNode == null) {
            System.out.println("The key " + x + "does not exist, no deletion");
            return;
        }

        // Case 1: delete leaf node
        if (deleteNode.left == null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = null;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = null;
                } else {
                    parentOfDeleteNode.right = null;
                }
            }
            return;
        }

        // Case 2: delete node having only left child
        if (deleteNode.left != null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }

        // Case 3: delete node having only right child
        if (deleteNode.left == null && deleteNode.right != null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.right;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.right;
                } else {
                    parentOfDeleteNode.right = deleteNode.right;
                }
            }
            deleteNode.right = null;
            return;
        }

        // Case 4: delete node having both left and right children
        if (deleteNode.left != null && deleteNode.right != null) {
            TreeNode rightOfDeleteNode;
            TreeNode replaceNode;   // the right most node - this will replace deleteNode

            // find the right most node on the left sub-tree of deleteNode
            rightOfDeleteNode = deleteNode.right;
            replaceNode = deleteNode.left;
            while (replaceNode.right != null) {
                replaceNode = replaceNode.right;
            }

            // gan nhanh phai vao max(nhanh trai)
            replaceNode.right = rightOfDeleteNode;
            deleteNode.right = null;
            // now deleteNode has only left child

            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                // attach leftChild to deleteNode position
                // gan nhanh trai vao vi tri vua xoa
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }
    }

    public void deleteByCopying(Room x) {
        // check if BSTree is empty
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion.");
            return;
        }

        // search node to be deleted
        TreeNode<Room> deleteNode;
        TreeNode<Room> parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info.compareRcode(x) == 0) {
                break;
            }

            // continue search
            if (x.compareRcode(deleteNode.info) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }

        // check if found x
        if (deleteNode == null) {
            System.out.println("The key " + x + "does not exist, no deletion");;
            return;
        }

        // Case 1: delete leaf node
        if (deleteNode.left == null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = null;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = null;
                } else {
                    parentOfDeleteNode.right = null;
                }
            }
            return;
        }

        // Case 2: delete node having only left child
        if (deleteNode.left != null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }

        // Case 3: delete node having only right child
        if (deleteNode.left == null && deleteNode.right != null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.right;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.right;
                } else {
                    parentOfDeleteNode.right = deleteNode.right;
                }
            }
            deleteNode.right = null;
            return;
        }

        // Case 4: delete node having both left and right children
        if (deleteNode.left != null && deleteNode.right != null) {
            //TreeNode deleteNodeRightChild;    -- no usage, because deleteNode right branch remains untouched thru the process
            TreeNode<Room> deleteNodeLeftChild;

            // find the right most node (deleteNodeLeftChild) on the left sub-tree of deleteNode
            deleteNodeLeftChild = deleteNode.left;
            TreeNode<Room> deleteNodeLeftChildParent = null;
            while (deleteNodeLeftChild.right != null) {
                deleteNodeLeftChildParent = deleteNodeLeftChild;
                deleteNodeLeftChild = deleteNodeLeftChild.right;
            }

            deleteNode.info = deleteNodeLeftChild.info;

            if (deleteNodeLeftChildParent == null) {
                // which means deleteNodeLeftChild == deleteNode.left
                // while loop did not go through any iteration
                // --> deleteNodeLeftChild.right == null
                deleteNode.left = deleteNodeLeftChild.left;
            } else {
                deleteNodeLeftChildParent.right = deleteNodeLeftChild.left;
            }

            deleteNodeLeftChild.left = null;
        }
    }

    public static void inOrderToArray(ArrayList<Room> a, TreeNode<Room> p) {
        if (p == null) {
            return;
        }
        inOrderToArray(a, p.left);
        a.add(p.info);
        inOrderToArray(a, p.right);
    }

    public void balance(ArrayList<Room> data, int first, int last) {
        if (first <= last) {
            int mid = (first + last) / 2;
            insert(data.get(mid));
            balance(data, first, mid - 1);
            balance(data, mid + 1, last);
        }
    }

    public void balance() {
        ArrayList<Room> sortedArray = new ArrayList<>();

        // buoc 1+2
        inOrderToArray(sortedArray, this.root);

        // buoc 3
        clear();

        // buoc 4
        balance(sortedArray, 0, sortedArray.size() - 1);
    }

    public TreeNode getParentNode(TreeNode<Room> p) {
        TreeNode<Room> result = null;
        TreeNode<Room> check = root;
        while (check != null) {
            if (check == p) {
                break;
            }
            if (p.info.compareRcode(check.info) < 0) {
                result = check;
                check = check.left;
            } else {
                result = check;
                check = check.right;
            }
        }
        return result;
    }

    public void rotateRight(TreeNode p) {
        if (p == null) {
            return;
        }
        if (isEmpty()) {
            System.out.println("Tree is empty, no rotation.");
            return;
        }

        TreeNode LCH = p.left;
        if (LCH == null) {
            System.out.println("Can't rotate right");
            return;
        }

        // rotate subtree
        TreeNode LCHR = LCH.right;
        LCH.right = p;
        p.left = LCHR;

        // attach subtree to grandParent
        TreeNode grandParent = getParentNode(p);
        if (grandParent == null) {
            // p == root
            root = LCH;
            return;
        }
        if (grandParent.left == p) {
            grandParent.left = LCH;
        } else {
            grandParent.right = LCH;
        }
    }

    public void rotateLeft(TreeNode p) {
        if (p == null) {
            return;
        }
        if (isEmpty()) {
            System.out.println("Tree is empty, no rotation.");
            return;
        }

        TreeNode LCH = p.right;
        if (LCH == null) {
            System.out.println("Can't rotate left");
            return;
        }

        // rotate subtree
        TreeNode LCHR = LCH.left;
        LCH.left = p;
        p.right = LCHR;

        // attach subtree to grandParent
        TreeNode grandParent = getParentNode(p);
        if (grandParent == null) {
            // p == root
            root = LCH;
            return;
        }
        if (grandParent.left == p) {
            grandParent.left = LCH;
        } else {
            grandParent.right = LCH;
        }
    }

    int height(TreeNode p) {
        if (p == null) {
            return 0;
        } else {
            int lDepth = height(p.left);//compute the depth of each subtree
            int rDepth = height(p.right);
            if (lDepth > rDepth) {
                return (lDepth + 1);//use the larger one
            } else {
                return (rDepth + 1);
            }
        }
    }
}
