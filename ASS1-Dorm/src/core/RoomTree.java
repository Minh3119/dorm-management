package core;

import base.MyQueue;
import base.TreeNode;
import dto.Room;
import dto.RoomType;
import dto.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.Inputter;


public class RoomTree {
    
    private static String FILE_NAME = "resources/rooms.txt";
    
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
        System.out.println(p);
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
        TreeNode<Room> p;
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.format("%-10s | %-20s | %-5s | %-5s | %-7s | %4s | %-6s | %s\n",
                "rcode", "name", "dom", "floor", "type", "beds", "booked", "price");
        System.out.println("-------------------------------------------------------------------------------------------------");
        while (!q.isEmpty()) {
            p = (TreeNode) q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            System.out.println(p);
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
            System.out.println("The Room " + x + "does not exist, no deletion");
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
            System.out.println("The room " + x + "does not exist, no deletion");;
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
    
    public int count() {
        return count(root);
    }

    private int count(TreeNode<Room> node) {
        if (node == null) {
            return 0; // Base case: if the node is null, return 0
        }

        // Count this node (1) plus the count of left and right subtrees
        return 1 + count(node.left) + count(node.right);
    }
    
    
    
    
    
    // 1.1
    public void loadData() {
        // data = rcode, name, dom, floor, type, booked, price
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    RoomType type = Inputter.convertsToRoomType(data[4]);
                    int booked = Integer.parseInt(data[5]);
                    double price = Double.parseDouble(data[6]);

                    Room room = new Room(data[0], data[1], data[2], data[3], type, booked, price);
                    if (searchByCode(data[0]) == null) {
                        this.insert(room);
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.format("Loaded %d rooms.\n", count);
    }

    // 1.2
    // insert(room)

    // 1.3
    public void display() {
        // display all rooms in the list
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.format("%-10s | %-20s | %-5s | %-5s | %-7s | %4s | %-6s | %s\n",
                "rcode", "name", "dom", "floor", "type", "beds", "booked", "price");
        System.out.println("-------------------------------------------------------------------------------------------------");
        inOrder(this.root);
    }

    public void display(Room room) {
        // display 1 Room
        System.out.format("%-10s | %-20s | %-5s | %-5s | %-7s | %4s | %-6s | %s\n",
                "rcode", "name", "dom", "floor", "type", "beds", "booked", "price");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(room);
    }

    // 1.4
    public void saveData() {
        // data = rcode, name, dom, floor, type, booked, price
        loadData();

        if (this.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            saveDataPostOrder(this.root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.format("Saved rooms to %s \n", FILE_NAME);
    }
    
    public void saveDataPostOrder(TreeNode<Room> p, BufferedWriter writer) throws IOException {
        if (p == null) {
            return;
        }
        saveDataPostOrder(p.left, writer);
        saveDataPostOrder(p.right, writer);
        
        String[] lineComponents = new String[7];
        Room room = p.info;
        lineComponents[0] = room.getRcode();
        lineComponents[1] = room.getName();
        lineComponents[2] = room.getDom();
        lineComponents[3] = room.getFloor();
        lineComponents[4] = room.getRoomType().toString();
        lineComponents[5] = String.valueOf(room.getBooked());
        lineComponents[6] = String.valueOf(room.getPrice());
        String line = String.join(",", lineComponents);
        writer.write(line);
        writer.newLine();
    }
    
    // 1.5
    public Room searchByCode(String rcode) {
        return binarySearchCode(root, rcode);
    }
    private Room binarySearchCode(TreeNode<Room> node, String rcode) {
        if (node == null) {
            return null;
        }

        // Compare the rcode of the current node's Room with the search rcode
        int comparison = rcode.compareTo(node.info.getRcode());

        if (comparison < 0) {
            // Search in the left subtree
            return binarySearchCode(node.left, rcode);
        } else if (comparison > 0) {
            // Search in the right subtree
            return binarySearchCode(node.right, rcode);
        } else {
            // Found the Room
            return node.info;
        }
    }
    
    
    // 1.6
    // deleteByCopying(searchByCode(rcode));

    // 1.7
    // deleteByMerging(searchByCode(rcode));

    // 1.8
    // just use balance()

    // 1.9
    // just use breadth()

    // 1.10 
    // use count() bro

    // 1.11
    public Room searchByName(String rname) {
        return binarySearchName(root, rname);
    }
    private Room binarySearchName(TreeNode<Room> node, String rname) {
        if (node == null) {
            return null;
        }

        // Compare the rcode of the current node's Room with the search rcode
        int comparison = rname.compareTo(node.info.getName());

        if (comparison < 0) {
            // Search in the left subtree
            return binarySearchName(node.left, rname);
        } else if (comparison > 0) {
            // Search in the right subtree
            return binarySearchName(node.right, rname);
        } else {
            // Found the Room
            return node.info;
        }
    }
    
    
    // 1.12
    public void searchBookedByRcode(String rcode) {
        // Tìm sinh viên trùng
        ArrayList<Student> studentsInRoom = new ArrayList<>();
        searchStudentsInRoom(root, rcode, studentsInRoom);

        if (studentsInRoom.isEmpty()) {
            System.out.println("No students live in room " + rcode + ".");
        } else {
            System.out.println("Students list living in room " + rcode + ":");
            for (Student student : studentsInRoom) {
                System.out.println(student);
            }
        }
    }

    // Pressing Toàn Bản đồ 
    private void searchStudentsInRoom(TreeNode<Student> node, String rcode, ArrayList<Student> result) {
        if (node == null) {
            return;
        }

        Room bookedRoom = node.info.getBookedRoom();
        if (bookedRoom != null && rcode.equals(bookedRoom.getRcode())) {
            result.add(node.info);
        }

        searchStudentsInRoom(node.left, rcode, result);
        searchStudentsInRoom(node.right, rcode, result);
    }



}
