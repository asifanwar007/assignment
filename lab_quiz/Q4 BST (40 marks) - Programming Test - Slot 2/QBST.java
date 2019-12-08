import java.util.Scanner;

// BSTabstract builds a BST. It maintains a reference to the root
// root is BSTNode<T> See BSTNode.java for its definition.
class BST<T extends Comparable<T>> extends BSTabstract<T> {
  
   BSTNode<T> root() { return root; }

   int find(T v) {
   /* 14 marks TODO: MODIFY here.  Write method find.
    *    "<=" elements are on the left of a node and ">" on the right
    *    Method should return the number of items found.
    */

     
   }

   boolean delete(T v) {
   /* 26 marks TODO: MODIFY here.  Write method delete
    *    "<=" elements are on the left of a node and ">" on the right
    *    Remove the top-most node with the value v, if it exists
    */

    
   }
}

class main {

   public static void main(String arg[]) {

      BST<Integer> bst = new BST<Integer>();
      bst.loadTree(arg[0]);
      Scanner input = new Scanner(System.in);

      boolean find = true;

      if(arg.length > 1) find = false;

      if(find)
         while(input.hasNext()) System.out.println(bst.find(input.nextInt()));
      else
         while(input.hasNext()) System.out.println(bst.delete(input.nextInt()));
      bst.test();
   }
}