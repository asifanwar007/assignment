import java.util.Scanner;

// DlistAbstract imlements a doubly linked list. It maintains a head node.

class Dlist<T extends Comparable<T>> extends DlistAbstract<T> {

   public DlistNode<T> head() { return head; }

   void insertafter(DlistNode<T> node, DlistNode<T> newnode) {

  /* 8 mark TODO: Modify this. Write method insertafter
   *    newnode is inserted after node in a doubly-linked fashion
   */

   }

   void delete(DlistNode<T> node) {

  /* 5 marks TODO: Modify this. Write method delete
   *    node is deleted from the doubly-linked list
   */

   }

   void sort() {

   /* 14 marks TODO: Modify this. Write method sort
    *    It sorts the doubly linked list in an increasing order, starting AFTER head
    *              i.e., head().next() is the node with the the smallest value
    *    You may implement any method to sort the list, for example, insertion sort.
    */

   }
}

class main {
   public static void main(String arg[]) {
      Dlist<Integer> list = new Dlist<Integer>();
      Scanner input = new Scanner(System.in);
      int len = input.nextInt();
      for (int i = 0; i < len; i++) {
        list.insert(input.nextInt());
      }
      int num_op = input.nextInt();
      for (int i = 0; i < num_op; i++){
        char c = input.next().charAt(0);
        if (c == 's'){
          list.sort();
        }
        else if (c == 'i'){
          int first = input.nextInt();
          int second = input.nextInt();
          DlistNode<Integer> head = list.head().next();
          while (head.value() != first){
            head = head.next();
          }
          DlistNode<Integer> val = new DlistNode<Integer>(second);
          list.insertafter(head, val);
        }
        else if (c == 'd'){
          int first = input.nextInt();
          DlistNode<Integer> head = list.head().next();
          while (head.value() != first){
            head = head.next();
            if (head == null){
              continue;
            }
          }
          list.delete(head);
        }
      }

      list.print();
   }
}

