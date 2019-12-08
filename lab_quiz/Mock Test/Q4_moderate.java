/**
Find if a Binary Search Tree is Height Balanced
***********************************************

Consider a height-balancing scheme where a Binary Search Tree has to meet the 
following conditions to be considered as height-balanced.
  A) An empty tree is height-balanced.
  B) A non-empty binary search tree T is height-balanced if:
     1. The left subtree of T is height-balanced
     2. The right subtree of T is height-balanced
     3. The difference between heights of left subtree and the right subtree is not more than 1.

Complete the implementation of the method boolean isBalanced(Node root) defined in the class Q4_moderate. 
This method takes the root of the binary search tree as input and returns 1 if the tree is height-balanced 
(according to the above scheme), 0 otherwise. You may assume the BST does not contain any duplicates. 
You may create additional helper functions if required, but make sure the isBalanced method returns the final output.

Sample Test Cases:
=================
1) Input: (4 elements inserted in the order 50, 30, 70, 60 - root is 50)
     50
    /  \
   30   70
       /
      60
   Output: 1 (tree is height balanced)
2) Input: (3 elements inserted in the order 50, 70, 80 - root is 50)
     50
       \
       70
         \
         80
   Output: 0 (tree is not height balanced)

Note: You are required only to implement the isBalanced() method and nothing else. 
The creation of the BST and calling the isBalanced function has been taken care of by our driver code.

WARNING: DO NOT UNCOMMENT these class definitions. 
These are provided just as a reference. Our driver code already contains these definitions.

class Node {
     int key; 
     Node left, right;
     public Node(int item) {
         key = item;
         left = right = null;
     }
 }

 class Tree {
     Node root;
     Tree() {
         root = null;
     }
 }
*/

class Q4_moderate {
    
    public int isBalanced(Node root) {
        // TODO: Implement this method (you may create additional helper methods if required)
        // Hint: Creating a helper to calculate height of a tree could make things easier
        return -1;
    }
}

