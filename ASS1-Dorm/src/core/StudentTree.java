package core;

import base.MyQueue;
import base.TreeNode;
import dto.Room;
import dto.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentTree {

    TreeNode root;

    public StudentTree() {
    }

    public void clear() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Student x) {
        if (isEmpty()) {
            TreeNode newNode = new TreeNode(x);
            root = newNode;
            return;
        }

        TreeNode<Student> curr;
        TreeNode<Student> parentOfCurr;
        curr = root;
        parentOfCurr = null;
        while (curr != null) {
            if (curr.info == x) {
                System.out.println("They key " + x + " already exists.");
                return;
            }

            parentOfCurr = curr;
            if (x.compareScode(curr.info) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        TreeNode newNode = new TreeNode(x);
        if (x.compareScode(parentOfCurr.info) < 0) {
            parentOfCurr.left = newNode;
        } else {
            parentOfCurr.right = newNode;
        }
    }

    public void insertMany(Student[] values) {
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

    public void deleteByMerging(Student x) {
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion.");
            return;
        }

        TreeNode<Student> deleteNode;
        TreeNode parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info.compareScode(x) == 0) {
                break;
            }

            if (x.compareScode(deleteNode.info) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }

        if (deleteNode == null) {
            System.out.println("The key " + x + "does not exist, no deletion");
            return;
        }

        if (deleteNode.left == null && deleteNode.right == null) {
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

        if (deleteNode.left != null && deleteNode.right == null) {
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

        if (deleteNode.left == null && deleteNode.right != null) {
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

        if (deleteNode.left != null && deleteNode.right != null) {
            TreeNode rightOfDeleteNode;
            TreeNode replaceNode;

            rightOfDeleteNode = deleteNode.right;
            replaceNode = deleteNode.left;
            while (replaceNode.right != null) {
                replaceNode = replaceNode.right;
            }

            replaceNode.right = rightOfDeleteNode;
            deleteNode.right = null;

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
    }

    public void deleteByCopying(Student x) {
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion.");
            return;
        }

        TreeNode<Student> deleteNode;
        TreeNode<Student> parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info.compareScode(x) == 0) {
                break;
            }

            if (x.compareScode(deleteNode.info) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }

        if (deleteNode == null) {
            System.out.println("The key " + x + "does not exist, no deletion");
            return;
        }

        if (deleteNode.left == null && deleteNode.right == null) {
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

        if (deleteNode.left != null && deleteNode.right == null) {
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

        if (deleteNode.left == null && deleteNode.right != null) {
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

        if (deleteNode.left != null && deleteNode.right != null) {
            TreeNode<Student> deleteNodeLeftChild;

            deleteNodeLeftChild = deleteNode.left;
            TreeNode<Student> deleteNodeLeftChildParent = null;
            while (deleteNodeLeftChild.right != null) {
                deleteNodeLeftChildParent = deleteNodeLeftChild;
                deleteNodeLeftChild = deleteNodeLeftChild.right;
            }

            deleteNode.info = deleteNodeLeftChild.info;

            if (deleteNodeLeftChildParent == null) {
                deleteNode.left = deleteNodeLeftChild.left;
            } else {
                deleteNodeLeftChildParent.right = deleteNodeLeftChild.left;
            }

            deleteNodeLeftChild.left = null;
        }
    }

    public static void inOrderToArray(ArrayList<Student> a, TreeNode<Student> p) {
        if (p == null) {
            return;
        }
        inOrderToArray(a, p.left);
        a.add(p.info);
        inOrderToArray(a, p.right);
    }

    public void balance(ArrayList<Student> data, int first, int last) {
        if (first <= last) {
            int mid = (first + last) / 2;
            insert(data.get(mid));
            balance(data, first, mid - 1);
            balance(data, mid + 1, last);
        }
    }

    public void balance() {
        ArrayList<Student> sortedArray = new ArrayList<>();

        inOrderToArray(sortedArray, this.root);

        clear();

        balance(sortedArray, 0, sortedArray.size() - 1);
    }

    public TreeNode getParentNode(TreeNode<Student> p) {
        TreeNode<Student> result = null;
        TreeNode<Student> check = root;
        while (check != null) {
            if (check == p) {
                break;
            }
            if (p.info.compareScode(check.info) < 0) {
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

    public Student binarySearch(String rcode) {
        return binarySearch(root, rcode);
    }

    private Student binarySearch(TreeNode<Student> node, String scode) {
        if (node == null) {
            return null; // Base case: not found
        }

        // Compare the rcode of the current node's Student with the search rcode
        int comparison = scode.compareTo(node.info.getScode());

        if (comparison < 0) {
            // Search in the left subtree
            return binarySearch(node.left, scode);
        } else if (comparison > 0) // Search in the right subtree
        {
            return binarySearch(node.right, scode);
        } else {
            // Found the Student
            return node.info;
        }

    }

    // 2.1
    public void loadData(String filename) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Student s = new Student(data[0], data[1], Integer.parseInt(data[2]));
                    if (searchByCode(data[0]) == null) {
                        this.insert(s);
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.format("Loaded %d students.\n", count);
    }

    // 2.2
    public void addToEnd(Student student) {

        insert(student);
    }

    // 2.3
    public void displayByPreOrder() {
        if (isEmpty()) {
            System.out.println("The tree is empty.");
            return;
        }
        preOrder(root);  // Duyệt cây và in ra
    }

    // 2.4
    public void saveData(String filename) {
        // data = rcode, name, dom, floor, type, booked, price
        loadData(filename);

        if (this.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            saveDataInOrder(root, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.format("Saved students to %s \n", filename);
    }

    public void saveDataInOrder(TreeNode<Student> p, BufferedWriter writer) throws IOException {
        if (p == null) {
            return;
        }

        inOrder(p.left);

        // save data in Node p
        String[] lineComponents = new String[3];
        Student s = p.info;
        lineComponents[0] = s.getScode();
        lineComponents[1] = s.getName();
        lineComponents[2] = String.valueOf(s.getByear());

        String line = String.join(",", lineComponents);
        writer.write(line);
        writer.newLine();
        // -------------------

        inOrder(p.right);
    }

    //2.5
    public Student searchByCode(String scode) {
        return binarySearch(scode);
    }

    // 2.6
    public void deleteByCode(String scode) {
        Student student = searchByCode(scode);
        if (student != null) {
            deleteByMerging(student);  // Xóa sinh viên khỏi cây bằng phương thức deleteByMerging
            System.out.println("Student with code " + scode + " has been deleted.");
        } else {
            System.out.println("Student with code " + scode + " not found.");
        }
    }

    // 2.7
    public void searchByName(String sname) {
        searchByNameRecursive(root, sname);
    }

    private void searchByNameRecursive(TreeNode<Student> node, String sname) {
        if (node == null) {
            return;
        }
        if (node.info.getName().equalsIgnoreCase(sname)) {
            System.out.println("Student found: " + node.info);
        }
        searchByNameRecursive(node.left, sname);
        searchByNameRecursive(node.right, sname);
    }

    // 2.8
    public void searchStudentRoom(String scode) {
        Student student = searchByCode(scode);
        if (student != null) {
            Room bookedRoom = student.getBookedRoom();  // Truy cập trực tiếp bookedRoom
            if (bookedRoom != null) {
                System.out.println("Student with code " + scode + " has booked the room:");
                System.out.println("Room code: " + bookedRoom.getRcode());
                System.out.println("Room name: " + bookedRoom.getName());

            } else {
                System.out.println("Student with code " + scode + " not found.");
            }

        }

    }
}