/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg2.binarysearchtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

/**
 *
 * @author adelali
 */
public class Assignment2BinarySearchTree {

    /**
     * @param args the command line arguments
     */
    BST_Node root;

    BST_Node getRoot() {
        return root;
    }

    void SetRoot(int key) {
        if (isEmpty()) {
            root = insert(root, key);
        }

    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        if (!isEmpty()) {
            root = new BST_Node(root.key);
        }
    }

    // Constructor 
    Assignment2BinarySearchTree() {
        root = null;
    }

    BST_Node Delete(BST_Node ToDeleteNode, int key) {

        if (ToDeleteNode == null) {
            return ToDeleteNode;
        }

        if (key < ToDeleteNode.key) {
            ToDeleteNode.left = Delete(ToDeleteNode.left, key);
        } else if (key > ToDeleteNode.key) {
            ToDeleteNode.right = Delete(ToDeleteNode.right, key);
        } else {
            if (ToDeleteNode.left == null) {
                return ToDeleteNode.right;
            } else if (ToDeleteNode.right == null) {
                return ToDeleteNode.left;
            }

            int minv = ToDeleteNode.right.key;
            while (ToDeleteNode.right.left != null) {
                minv = ToDeleteNode.right.left.key;
                ToDeleteNode.right = ToDeleteNode.right.left;
            }
            ToDeleteNode.key = minv;

            ToDeleteNode.right = Delete(ToDeleteNode.right, ToDeleteNode.key);
        }
        return ToDeleteNode;
    }

    public int GetHeight(BST_Node node, int RightH, int LeftH) {

        BST_Node TmPNode = node;
        while (TmPNode.right != null) {
            RightH++;
            return GetHeight(TmPNode.right, RightH, LeftH);

        }
        while (TmPNode.left != null) {

            LeftH++;
            return GetHeight(TmPNode.left, RightH, LeftH);
        }
        return (RightH > LeftH) ? RightH + 1 : LeftH + 1;
    }

    BST_Node Search(int key) {
        BST_Node Result = Search(root, key);
        return Result;
    }

    BST_Node Search(BST_Node StartNode, int key) {
        if (StartNode != null) {
            if (key < StartNode.key) {
                return Search(StartNode.left, key);
            } else if (key > StartNode.key) {
                return Search(StartNode.right, key);
            } else if (key == StartNode.key) {
                return StartNode;
            }
        } else {
            return null;
        }
        return null;
    }

    BST_Node insert(BST_Node root, int key) {
        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new BST_Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    // This method mainly calls InorderRec() 
    public void NormalPrint() {
        NormalPrint(root, "Root", false);
    }

    public void NormalPrintOnlyLeaf() {
        NormalPrint(root, "Root", true);
    }

    // A utility function to do inorder traversal of BST 
    public void NormalPrint(BST_Node node, String PrintMSG, boolean Leaf) {

        //NOT . WORKING /////////////////////////////////////////////////////////////
        // Replaced With Print Function To Print View 
        //        if (root != null) {
        //            if (root.left != null) {
        //                
        //                if (root.left.right !=null | root.left.left !=null){
        //                      System.out.print(" L            " + root.left.key+ "  ");
        //                }else{
        //                     System.out.print("  L  " + root.left.key+ "  ");
        //                }
        //                
        //                
        //               
        //            }
        //           // System.out.print(root.key + "  |  ");
        //            if (root.right != null) {
        //                if (root.right.right !=null | root.right.left !=null){
        //                     System.out.print("          R  " + root.right.key+" . ");
        //                }else{
        //                     System.out.print(" R  " + root.right.key+" . ");
        //                }
        //               
        //            }
        //            System.out.println("");
        //////////////////////////////////////////////////////////////////////////////////
        if (node == null) {
            return;
        }

        NormalPrint(node.left, " Left " + node.key + ((PrintMSG == "Root") ? "" : " <= " + PrintMSG), Leaf);
        if (PrintMSG != "Root" || node.key != this.root.key) {
            if (!Leaf) {
                System.out.println(node.key + "   " + PrintMSG);
            }
        }
        NormalPrint(node.right, " Right " + node.key + ((PrintMSG == "Root") ? "" : " <= " + PrintMSG), Leaf);

        if (Leaf) {
            PrintAllLeaf(node);
        }

    }

    public void PrintAllLeaf(BST_Node node) {
        if ((node.left = node.right) == null & node.key != root.key) {
            System.out.println("IS leaf => " + node.key);
        }
    }

    public static void main(String[] args) {
        Assignment2BinarySearchTree tree = new Assignment2BinarySearchTree();

        tree.SetRoot(50);

        BST_Node TempNode = tree.root;
        TempNode = tree.insert(TempNode, 30);
        TempNode = tree.insert(TempNode, 20);
        TempNode = tree.insert(TempNode, 40);
        TempNode = tree.insert(TempNode, 70);
        TempNode = tree.insert(TempNode, 60);
        TempNode = tree.insert(TempNode, 80);

        System.out.println("root   " + tree.getRoot().key);

        System.out.println("Tree height => " + tree.GetHeight(tree.getRoot(), 1, 1));
        System.out.println("-----------Main View Before Delete 40 ----------");
        FunctionFromStackToPrintFullTreeView.print(tree.getRoot());

        BST_Node RESULT = tree.Search(40);
        System.out.println("Search For 40  : Found =>  " + RESULT.key);

        System.out.println("Deleting Value 40 ");
        tree.Delete(tree.getRoot(), 40);

        BST_Node RESULTAfterDelete = tree.Search(40);
        if (RESULTAfterDelete == null) {
            System.out.println("Search For 40  :  NotFound");
        }
        System.out.println("Tree height => " + tree.GetHeight(tree.getRoot(), 1, 1));
        System.out.println("-----------Main View After Delete 40 ----------");
        FunctionFromStackToPrintFullTreeView.print(tree.getRoot());

        tree.NormalPrint();

        // function to Find All Leafs with no right or left 
        tree.NormalPrintOnlyLeaf();

    }

}
