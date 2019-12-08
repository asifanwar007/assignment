import java.util.Scanner;

// HeapTable supports heapification of Table (Table extends Table_, see Table_.java)
class HeapTable extends Table<Integer> {

   HeapTable() { super(Integer.class); }

   void bubbleDown(int node, int count) { // Hint: complete and use function
   }

   void heapifyMin(int count) {
  /* 15 marks TODO: MODIFY THIS. You may write helper functions in this file
   *       Write method heapifyMin. It turns an unordered Table into a min-heap.
   *       Note that Table<K> implements Table_<K> (see Table_.java)
   */
   }
}

class main {

   public static void main(String arg[]) {

      // Table<Integer> numbers = new Table<Integer>(Integer.class);
      HeapTable numbers = new HeapTable();
      Scanner input = new Scanner(System.in);

      int num = 0;
      while(input.hasNext()) {
         if(num == numbers.length()) numbers.setLength(2*num);
         numbers.set(num++, input.nextInt());
      }

      numbers.heapifyMin(num);
      numbers.print();
      for(int i=0; i<num; i++) System.out.print(numbers.get(i) + " "); System.out.println("");
   }
}
