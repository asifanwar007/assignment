// import java.util.*;
public class sort{
	String[][] sortvar;
	public sort(String[][] sortvar){
		this.sortvar = sortvar;
	}

	public String[][] getSorted(){
		int size = sortvar.length;
		for(int i = 0; i<size-1; i++) {
         for (int j = i+1; j<sortvar.length; j++) {
            if(sortvar[i][0].compareTo(sortvar[j][0])>0) {
               String[] temp = sortvar[i];
               sortvar[i] = sortvar[j];
               sortvar[j] = temp;
            }
         }
      }
      return sortvar;

	}
	// public static void main(String[] args) {
	// 	String[][] a = {{"Col106", "sad"}, {"COL207","dfadfs"}, {"APL207","dfadfs"}, {"BBL207","dfadfs"}};
	// 	sort n = new sort(a);

	// 	for(int i = 0; i < n.getSorted().length; i++){
	// 	System.out.println(Arrays.toString(n.getSorted()[i]));


	// 	}
	// }

}