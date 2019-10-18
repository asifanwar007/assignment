package Trie;
import java.util.ArrayList;
import java.util.List;

public class Trie<T> implements TrieInterface {

	
	public String word;
    public T value;
    public TrieNode<T> root;
    
 //   TrieNode<T> storenode;
    
   
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
		  if(temp.children[index] == null)
	{

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
		  if(temp.children[index]==null)
				   {return null;}
		  
			  temp = temp.children[index];}
	  if(temp.value==null) {
	   return null;
   }
   
	   return temp;
	}
   
 public boolean delete(String word) {
	 
	 TrieNode temp= deletenode(root,0,word);
	  
	   
	 return temp.ref==false;
	}
 public boolean childfinder(TrieNode temp) {
	 for (int i = 0; i < temp.children.length; i++)                             //Returns true if root has no children, else false
     {if ((temp.children[i]) != null)
             return false;
     }
     return true;
 }
public TrieNode deletenode(TrieNode temp, int branch,String word) {
	String s[]=word.split(" ",3);
	   String Name=s[0]+" "+s[1]+" ";
	int index = Name.charAt(branch)-0;                                                                       
	//If tree is empty
if (temp == null){return null;}

if (Name.length()-1==branch)                                                           //If last char of word
{//System.out.println(temp.level);
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
 		  if(temp.children[index]==null)
 				   {return null;}
 		  
        temp = temp.children[index];}
    	return temp;
    }


   
    public void printLevel(int level) {
    	String[]x;
    	int j=0;
    	int i=0;
    	String  store;
    	
		for(i=0;i<root.levelstore.size();i++) {
			store=root.levelstore.get(i).split(" ", 3)[1];
			if(store.equals(String.valueOf(level))) {
				System.out.println(root.levelstore.get(i));
			}
    	
    	} 
    		
    		
    	
    	}
    

    
    public void print() {
    	int j=0;
    	int i=0;
    	String  store;
    	for(i=0;i<root.levelstore.size();i++) {
    		store=root.levelstore.get(i);
    		System.out.println(root.levelstore.get(i));
    	}
    }
	
	public void printTrie(TrieNode trieNode) {
		int j=0;
		while(j<trieNode.child.size()) {
	 		 System.out.println(trieNode.child.get(j));
	 		 j++;
	 	 }
		
	}
}