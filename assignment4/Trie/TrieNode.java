package Trie;


import java.util.ArrayList;

import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
   
	T value;
	
	TrieNode<T>[] children;
    Boolean ref;
    int size;
    char ch;
    int level;
    ArrayList<String> levelstore;
    

 ArrayList<T> child;
	
	public TrieNode(T value,char ch){
		this.ch=ch;
		this.value=value;
		ref=false;
		this.size=127;
		 this.children = new TrieNode[size];
		 this.child=new ArrayList<T>();
		 this.levelstore=new ArrayList<String>();
		 for (int i=0;i<size;i++) 
             this.children[i] = null;
//		 for (int i=0;i<size;i++) 
//             this.levelstore.add("");
	}
	
 
    public T getValue() {
        return value;
    }


}