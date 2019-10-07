package Trie;


import Util.NodeInterface;
import java.util.ArrayList;

class Pair<T, K>{
	public T pair_node;
	public K pair_character_value;
	Pair(T node, K chara){
		this.pair_node = node;
		this.pair_character_value = chara;
	}

}


public class TrieNode<T> implements NodeInterface<T> {

	// public TrieNode<T> node;
	// public TrieNode<T> left_node;
	private T obj;
	// public Pair<TrieNode<T>, Character> pair;
	public ArrayList<Pair<TrieNode<T>, Character>> char_store_array;
	// public Character char_value;
	private boolean complete_word = false;
	public TrieNode(){
		char_store_array = new ArrayList<Pair<TrieNode<T>, Character>>();
	}

	public void addValueToCharacterArray(TrieNode<T> a, Character c){
		Pair<TrieNode<T>, Character> temp = new Pair<TrieNode<T>, Character>(a, c);
		char_store_array.add(temp);
	}
	public void remCharacterNode(Character a){
		for(int i = 0; i < char_store_array.size(); i++){
			if((char_store_array.get(i).pair_character_value).equals(a)){
				char_store_array.get(i).pair_node = null;
				// char_store_array.get(i).pair_character_value = null;
			}

		}
	}

	public TrieNode<T> getCharacterNode(Character a){
		for(int i = 0; i < char_store_array.size(); i++){
			if((char_store_array.get(i).pair_character_value).equals(a)){
				return char_store_array.get(i).pair_node;
			}

		}
		return null;
	}
	// public boolean charContains(ArrayList<TrieNode<T>> char_store_array, Character a){
	// 	for(int i = 0; i < char_store_array.size(); i++){
	// 		if(char_store_array.get(i).char_value == a){
	// 			return true;
	// 		}

	// 	}
	// 	return false;
	// }
	public  void remWord(){
		this.complete_word = false;
		this.obj = null;
	}
	public void setWord(){
		this.complete_word = true;
	}
	public boolean isWord(){
		return this.complete_word;
	}

	public void setValue(Object value){
		this.obj = (T) value;
	}
    public T getValue() {
        return this.obj;
    }


// public static void main(String[] args) {
// 	System.out.println("chcking trienode");
// 	TrieNode<Person> tre = new TrieNode();
// 	tre.newCharStore('a');
// 	tre.newCharStore('b');
// 	tre.newCharStore('c');
// }

}