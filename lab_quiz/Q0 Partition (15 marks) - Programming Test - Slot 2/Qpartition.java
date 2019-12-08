import java.util.Scanner;

class main {

   static int partition(int array[])  {

   /* 15 marks TODO: MODIFY and complete method partition. (Also insert the call to it in main.)
    *    It accepts an integer array in which: index 0 has the parameter 'begin'
    *                                          index 1 has the parameter 'end'
    *                                          index 2 has has the separator value.
    *    It separates array[begin] to array[end], and returns the separator index SI, i.e.,
    *    after the method, array[begin] to array[SI] is <= separator value 
    *                      array[SI+1] to array[end] is > separator value 
    *                      No array element ouside the range array[begin:end] are modified.
    *    Given: All elements in the range array[begin:end] are unique.
    *           end < array.length and begin > 0.
    *           None of the elements array[begin:end] need equal the separator value.
    */

      return -1;
   }

   public static void main(String arg[]) {
      
        Scanner input = new Scanner(System.in);

	    final int MAX = 1024;
        int[] F = new int[MAX];
        int nElem = 0;

        while(input.hasNext())
           if(nElem < MAX)
              F[nElem++] = input.nextInt();
           else break;

        System.out.println("Upto index "+(nElem-1)+" Pivot = "+ F[2]);
        for(int i=0; i<nElem; i++) System.out.print(F[i]+" "); System.out.println("");

        int pos;
        pos = partition(F);
        System.out.println("Pivot pos = "+ pos);
        for(int i=0; i<nElem; i++) System.out.print(F[i]+" "); System.out.println("");
   }
}
