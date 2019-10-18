package Trie;


import java.util.ArrayList;
import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
   
	public T value;
	public ArrayList<TrieNode<T>> children;
    public boolean isWord = false;
    public Character character;
    public String string_char;
    // ArrayList<String> levelstore;
 	// ArrayList<T> child;
	
	public TrieNode(){
		this.children = new ArrayList<TrieNode<T>>();
	}
	
 
    public T getValue() {
        return value;
    }


}