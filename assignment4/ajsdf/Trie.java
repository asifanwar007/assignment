package Trie;
import java.util.ArrayList;
import java.util.List;

public class Trie<T> implements TrieInterface {

	
	public String word;
    public T value;
    public TrieNode<T> root;
   
    public Trie(){
	   this.root=new TrieNode(value,'a');
	}
   
    public boolean insert(String word, Object value) {
	   String s[]=word.split(" ",3);
	   int j;
	   String Name=s[0]+" "+s[1]+" ";
	   int length=Name.length();
	   int i;
	   int z;
	   int index;
	   boolean check=false;
	   String  store;
	   String letter;	   
       TrieNode<T> temp=root;
	   //System.out.println(Name.length());
	   for(i=0;i<length;i++) {
		  index=Name.charAt(i)-0;
		  if(temp.children[index] == null) {

	       temp.children[index]= new TrieNode(null,Name.charAt(i));}
		 
	   temp.children[index].child.add((T) value);

	    for(j=0;j<root.levelstore.size();j++) {
			store=root.levelstore.get(j);
			letter=root.levelstore.get(j).split(" ",3)[2];

			if(i==0) {
				for(z=0;z<letter.length();z++) {
					if(letter.charAt(z)==Name.charAt(0)) {
						check=true;
					}
					
				}
				if(check==false) {
					store=store.concat(","+String.valueOf(Name.charAt(0)));
					root.levelstore.set(j, store);
					check=true;}
					
				
				
			}else
			if(temp.children[index].child.size()==1&&store.split(" ",3)[1].equals(String.valueOf(i+1))) {
				store=store.concat(","+String.valueOf(Name.charAt(i)));
				root.levelstore.set(j, store);
				check=true;
			}
			
			 
    	}
		  
		if(i+1>root.levelstore.size()) {
			root.levelstore.add("level"+" "+(i+1)+" "+String.valueOf(Name.charAt(i)));
		}	  
		  
			  
		temp.children[index].level=i+1;
       temp = temp.children[index];
       //temp.level=i+1;
	   }
	   //System.out.println(temp.level);
	   temp.value=(T)value;
       temp.ref=true;
	   return temp.ref==true;
    }

    public TrieNode<T> search(String word) {
	   String s[]=word.split(" ",3);
	   String Name=s[0]+" "+s[1]+" ";
	   int length=Name.length();
	   int i;
	   int index;
	   
	   TrieNode<T> temp=root;
	   for(i=0;i<length;i++) {
	       index=Name.charAt(i)-0;
		   if(temp.children[index]==null) {return null;}
		   temp = temp.children[index];}
	       if(temp.value==null) {return null; }
   
	   return temp;
	}
   
    public boolean delete(String word) {
        if(this.search(word) == null){
            return false;
        }
	   TrieNode temp= deletenode(root,0,word);
	   return temp.ref==false;
	}

    public boolean childfinder(TrieNode temp) {
        for (int i = 0; i < temp.children.length; i++){                             //Returns true if root has no children, else false {
        if ((temp.children[i]) != null){return false;}
        }
        return true;
    }


    public TrieNode deletenode(TrieNode temp, int branch,String word) {
	   String s[]=word.split(" ",3);
	   String Name=s[0]+" "+s[1]+" ";
	   int index = Name.charAt(branch)-0;                                                                       
	//If tree is empty
        if (temp == null){return null;}

        if (Name.length()-1==branch){                                                          //If last char of word
            if (temp.ref==true){temp.ref = false;}                                                              //if node is not last node alter ref
            if (!childfinder(temp)) {
            	temp.children[index].value=null;

            } 
            return temp;
        }
        temp.children[index] =deletenode(temp.children[index], branch + 1,word);
        return temp;
    }

    public TrieNode startsWith(String prefix) {
        int i;
    	
 	    int index;
 	    int length=prefix.length();
        TrieNode<T> temp=root;
 	    for(i=0;i<length;i++) {
            index=prefix.charAt(i)-0;
 		    if(temp.children[index]==null) {return null;}
            temp = temp.children[index];}
    	return temp;
    }



    public ArrayList<Character> getSortedChar(ArrayList<Character> sortvar){
        int size = sortvar.size();
        for(int i = 0; i<size-1; i++) {
            for (int j = i+1; j<size; j++) {
                if(sortvar.get(i).compareTo(sortvar.get(j))>0) {
                Character temp = sortvar.get(i);
                 sortvar.set(i, sortvar.get(j));
                 sortvar.set(j, temp);
              }
           }
        }
        return sortvar;
    }
   
    public void printLevel(int level) {
    	String[]x;
    	int j=0;
    	int i=0;
    	String[]  store = root.levelstore.get(level-1).split(" ", 3);
        String store1 = store[2];
        // int t = store1.length() /2;
        ArrayList<Character> toSort = new ArrayList<Character>(); // store[2].split(",");
        for (int l = 0; l<store1.length(); l++){
            Character d = store1.charAt(l);
            if(!d.equals(',') && !d.equals(' ')){
                // System.out.print(d + " ");
                toSort.add(d);
            }
        }
        // System.out.println(toSort);
    	ArrayList<Character> sorted = getSortedChar(toSort);
        System.out.print("Level " + level + ": ");
        String ans = "";
        for(Character c: sorted){
            ans = ans + c + ",";
        }
        if(ans.length() > 0){
        System.out.println(ans.substring(0, ans.length()-1));
        } else {System.out.println();}
		// for(i=0;i<root.levelstore.size();i++) {
		// 	store=root.levelstore.get(i).split(" ", 3)[1];
		// 	if(store.equals(String.valueOf(level))) {
		// 		System.out.println(root.levelstore.get(i));
		// 	}
  //   	} 
	}

    ArrayList<Character> ans_arr;
    public void printLevelHelper(TrieNode<T> node, int level){
        int i;
        
        int index;
        int length=prefix.length();
        TrieNode<T> temp=root;
        for(i=0;i<length;i++) {
            index=prefix.charAt(i)-0;
            if(temp.children[index]==null) {return null;}
            temp = temp.children[index];}
        return temp;

        String s[]=word.split(" ",3);
       String Name=s[0]+" "+s[1]+" ";
       int length=level;
       int i;
       int index;
       
       TrieNode<T> temp=node;
       Character c = temp.ch;
       if(level == temp.level){
            ans_arr.add(c);
       }
       for(i=0;i<length;i++) {
           index=Name.charAt(i)-0;
           if(temp.children[index]==null) {return null;}
           temp = temp.children[index];}
           if(temp.value==null) {return null; }
   
       return temp;

    }
    

    
    public void print() {
    	int j=0;
    	int i=0;
    	String  store;
        for(i=0;i<root.levelstore.size();i++){
            this.printLevel(i + 1);
        }
        // System.out.println(root.levelstore.toString());
    	// for(i=0;i<root.levelstore.size();i++) {
    	// 	store=root.levelstore.get(i);
    	// 	System.out.println(root.levelstore.get(i));
    	// }
    }
	
	public ArrayList<String> getSortedStr(ArrayList<String> sortvar){
        int size = sortvar.size();
        for(int i = 0; i<size-1; i++) {
            for (int j = i+1; j<size; j++) {
                if(sortvar.get(i).compareTo(sortvar.get(j))>0) {
                String temp = sortvar.get(i);
                 sortvar.set(i, sortvar.get(j));
                 sortvar.set(j, temp);
              }
           }
        }
        return sortvar;
    }
	public void printTrie(TrieNode trieNode) {
		ArrayList<String> arr = new ArrayList<String>();
		int j=0;
		while(j<trieNode.child.size()) {
	 		arr.add(trieNode.child.get(j).toString());
	 		j++;
	 	 }
		arr = getSortedStr(arr);
        for(String c: arr){
            System.out.println(c);
        }
		
	}
}