package Trie;


import Util.NodeInterface;
import java.util.ArrayList;

class Pair<T, K, L>{
	public T next_node;
	public K pair_character_value;
	public L  pair_obj;
	public boolean word_of_this_char = false;
	Pair(T node, K chara){
		this.next_node = node;
		this.pair_character_value = chara;
		// this.word_of_this_char = false;
	}

}


public class TrieNode<T> implements NodeInterface<T> {

	public Character last_character_word;
	private T obj;
	public ArrayList<Pair<TrieNode<T>, Character, T>> char_store_array;
	private boolean complete_word = false;
	public TrieNode(){
		char_store_array = new ArrayList<Pair<TrieNode<T>, Character, T>>();
	}

	
	public void addValueToCharacterArray(TrieNode<T> a, Character c){
		Pair<TrieNode<T>, Character, T> temp = new Pair<TrieNode<T>, Character, T>(null, c);
		char_store_array.add(temp);
	}
	public void remCharacterNode(Character a){
		for(int i = 0; i < char_store_array.size(); i++){
			if((char_store_array.get(i).pair_character_value).equals(a)){
				char_store_array.get(i).next_node = null;
			}
		}
	}
	public TrieNode<T> getCharacterNode(Character a){
		for(int i = 0; i < char_store_array.size(); i++){
			if((char_store_array.get(i).pair_character_value).equals(a)){
				return char_store_array.get(i).next_node;
			}
		}
		return null;
	}
	public Pair<TrieNode<T>, Character, T> getCharacterNodeInsert(Character a){
		for(int i = 0; i < char_store_array.size(); i++){
			if((char_store_array.get(i).pair_character_value).equals(a)){
				return char_store_array.get(i);
			}
		}
		return null;
	}
	public void setChar(Character a){
		this.last_character_word = a;
	}
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
		for(int i = 0; i < this.char_store_array.size(); i++){
			Pair<TrieNode<T>, Character, T> pa = this.char_store_array.get(i);
			if(pa.pair_character_value != null && pa.pair_character_value.equals(last_character_word)){
				pa.pair_obj = (T) value;
				pa.word_of_this_char = true;
			}
		}
	}
	public ArrayList<T> getValuehelp(){
		ArrayList<T> answer = new ArrayList<T>();
		for(int i = 0; i < this.char_store_array.size(); i++){
			Pair<TrieNode<T>, Character, T> pa = this.char_store_array.get(i);
			if(pa.word_of_this_char){
				this.last_character_word = pa.pair_character_value;
				answer.add(this.getValue());
			}
		}
		return answer;
	}
    public T getValue() {
    	for(int i = 0; i < this.char_store_array.size(); i++){
			Pair<TrieNode<T>, Character, T> pa = this.char_store_array.get(i);
			if(pa.pair_character_value != null && pa.pair_obj != null && pa.pair_character_value.equals(last_character_word)){
				this.obj = pa.pair_obj;
			}
		}
        return this.obj;
    }
}