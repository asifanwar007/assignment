package Trie;
import java.util.ArrayList;
import java.util.List;

public class Trie<T> implements TrieInterface {
    public TrieNode<T> root = new TrieNode<T>();


    public boolean insert(String word, Object value) {
    	if(search(word) != null){return false; }
    	TrieNode<T> trav = root;
        int i = 0;
 		Character char_to_inset = word.charAt(0);
        while (i < word.length() && trav.children != null && trav.children.size() > 0) {
        	char_to_inset = word.charAt(i);
	        int position_on_node = -1;
	        boolean word_present = false;
            for(TrieNode<T> c : trav.children){
            	position_on_node++;
	            if(c.character.equals(char_to_inset)){
	            	word_present = true;
	            	break;
	            }
            }	
        	// System.out.println(trav.children.get(position_on_node).string_char);
            String label = "";
            if(word_present){
		        label = trav.children.get(position_on_node).string_char;
		    } else{
		    	break;
		    }
		    int j = 0;
            while (j < label.length() && i < word.length() && label.charAt(j) == word.charAt(i)) {
                ++i;
                ++j;
            }
 
            if (j == label.length()) {
                trav = trav.children.get(position_on_node);
            } else {
                if (i == word.length()) {   // inserting a prefix of exisiting word
                    TrieNode<T> existingChild = trav.children.get(position_on_node);
                    TrieNode<T> newChild = new TrieNode<T>();
                    String remainingLabel = strCopy(label, j);
                     
                    label = label.substring(0, j); // making "faceboook" as "face"
                    // System.out.println(label + " hfdaljksdfhlkah");
                    newChild.character = label.charAt(0);
                    newChild.string_char = label;


                    newChild.value = (T) value;
                    newChild.isWord = true;
                    // newChild.character = char_to_inset;
                    // System.out.println(label + " label");
                    // System.out.println(remainingLabel + " remlabel");
                    // System.out.println(remainingWord + " remword");
                    // System.out.println("-------------end--------------------");
                
                    // System.out.println(existingChild.value.toString());
                    existingChild.character = remainingLabel.charAt(0);
                    existingChild.string_char = remainingLabel;
                    newChild.children.add(existingChild);
                    // System.out.println(newChild.children.size() + "heldofjd broa at");
                    // trav.children.remove(position_on_node);
                    // trav.children.add(newChild);
                    trav.children.set(position_on_node, newChild);    // new TrieNode<T> for "face"
                    // System.out.println(trav.children.get(position_on_node).children.get(0).string_char);
                    // newChild.children[remainingLabel.charAt(0) - CASE] = remainingLabel;
                } else {     // inserting word which has a partial match with existing word
                	// System.out.println("-----------start-----------");
                    TrieNode<T> existingChild = new TrieNode<T>();
                    String remainingLabel = strCopy(label, j);
                    TrieNode<T> newChild = new TrieNode<T>();
                    String remainingWord = strCopy(word, i);
                    TrieNode<T> temp = trav.children.get(position_on_node);
                    existingChild.value = temp.value;
                    existingChild.character = remainingLabel.charAt(0);
                    existingChild.isWord = true;
                    existingChild.string_char = remainingLabel;
                    existingChild.children = temp.children;
                     // System.out.println(label + " total labae");
                    label = label.substring(0, j);
                    newChild.value = (T) value;
                    newChild.isWord = true;
                    newChild.character = remainingWord.charAt(0);
                    newChild.string_char = remainingWord;
                    temp.value = null;
                    temp.isWord = false;
                    temp.children = new ArrayList<TrieNode<T>>();
                    temp.string_char = label;
                    // System.out.println(temp.character);
                    temp.children.add(newChild);
                    temp.children.add(existingChild);
                    // System.out.println(label + " label");
                    // System.out.println(remainingLabel + " remlabel");
                    // System.out.println(remainingWord + " remword");
                    // System.out.println("-------------end--------------------");
                }
 
                return true;
            }
        }
 
        if (i < word.length()) {    // inserting new node for new word
        	TrieNode<T> new_node = new TrieNode<T>();

            new_node.string_char = strCopy(word, i);
        	// System.out.println(strCopy(word, i) + " heloo dfadfj;");
            new_node.character = char_to_inset;
            new_node.value = (T) value;
            new_node.isWord = true;

            trav.children.add(new_node);// = new Node(true);
        } 
        return true;
    }

    public String strCopy(String str, int index) {
        String result = "";
         
        while (index != str.length()) {
            result = result + Character.toString(str.charAt(index++));
        }
         
        return result;
    }

    public TrieNode<T> search(String word) {
    	int i = 0;
        TrieNode<T> trav = root;
        if(trav == null){
        	return null;
        }
 
        while (i < word.length() && trav.children != null && trav.children.size() > 0) {
            int j = 0;
            Character char_to_inset = word.charAt(i);
            int position_on_node = -1;
	        boolean word_present = false;
            for(TrieNode<T> c : trav.children){
            	position_on_node++;
            	// System.out.println(trav.children.get(position_on_node).string_char);
            	// System.out.println(trav.children.get(position_on_node).character);
            	// System.out.println(char_to_inset);
            	// System.out.println(c.character.equals(char_to_inset));

	            if(c.character.equals(char_to_inset)){
	            	word_present = true;
	            	break;
	            }
            }
            String label = "";
            if(word_present){
		        label = trav.children.get(position_on_node).string_char;
		    } else{
		    	return null;
		    }
 			// System.out.println(label + " inside delete statement");
            while (i < word.length() && j < label.length()) {
                if (word.charAt(i) != label.charAt(j)) {
                    
                   // System.out.println(" character mismatch");
                    return null;   
                }
                 
                ++i;
                ++j;
            }
 			// System.out.println();
            if (j == label.length() && i <= word.length()) {
            	// System.out.println("hello " + i);
                trav = trav.children.get(position_on_node);    // traverse further
                // System.out.println(trav.string_char);
                // System.out.println(trav.children.size());
                // System.out.println("------------__Strar-----------");
                // if(trav.children.size() > 0){
                // 	for(int l = 0 ; l < trav.children.size(); l++){
                // 		System.out.println(trav.children.get(l).string_char);
                // 	}
                // }
                // System.out.println("---------------end----------------");
            } else {
                // edge label is larger than target word
                // searching for "face" when tree has "facebook"
                return null;
            }
        }
         // System.out.println("      ----------" + i + " " + trav.children.size());
        // target word fully traversed and current node is a word ending
        if(trav != null && trav.isWord && i == word.length()){
        	return trav;
        }
        return null;
	   
    }


    @Override
    public TrieNode startsWith(String prefix) {
    	String word = prefix;
    	int i = 0;
        TrieNode<T> trav = root;
        if(trav == null){
        	return null;
        }
 
        while (i < word.length() && trav.children != null && trav.children.size() > 0) {
            int j = 0;
            Character char_to_inset = word.charAt(i);
            int position_on_node = -1;
	        boolean word_present = false;
            for(TrieNode<T> c : trav.children){
            	position_on_node++;
            	// System.out.println(trav.children.get(position_on_node).string_char);
            	// System.out.println(trav.children.get(position_on_node).character);
            	// System.out.println(char_to_inset);
            	// System.out.println(c.character.equals(char_to_inset));

	            if(c.character.equals(char_to_inset)){
	            	word_present = true;
	            	break;
	            }
            }
            String label = "";
            if(word_present){
		        label = trav.children.get(position_on_node).string_char;
		    } else{
		    	return null;
		    }
 			
            while (i < word.length() && j < label.length()) {
                if (word.charAt(i) != label.charAt(j)) {
                    return null;   // character mismatch
                }
                 
                ++i;
                ++j;
            }
 
            if (j == label.length() && i <= word.length()) {
                trav = trav.children.get(position_on_node);    // traverse further
            } else {
                return trav.children.get(position_on_node);
            }
        }
        if(trav != null){
        	return trav;
        }
        return null;
    }

    @Override
    public void printTrie(TrieNode trieNode) {
    	ans_arr = new ArrayList<String>();
        this.printTrieHelper(trieNode);
            // System.out.println(ans_arr.toString());
        this.getSorted(ans_arr);
            // System.out.println("helo" );
        for(String e: ans_arr){
            System.out.println(e);
        }


    }

    public ArrayList<String> ans_arr;
    public void printTrieHelper(TrieNode<T> trieNode){
        if(trieNode != null){
            ArrayList<TrieNode<T>> g = trieNode.children;
            T temp_obj = trieNode.getValue();
            if(temp_obj != null){
                // System.out.println(ans_arr.toString());
                if(ans_arr.contains(temp_obj.toString()) != true){ans_arr.add(temp_obj.toString()); }
            } 
            // System.out.print(g.size());
            for(int i = 0; i<g.size(); i++){

                TrieNode<T> e = g.get(i);
                if(e.isWord) {
                	temp_obj = e.getValue();
                	if(ans_arr.contains(temp_obj.toString()) != true){ans_arr.add(temp_obj.toString()); }
                } 
                if(e.children != null){
                	printTrieHelper(e);
                }
                
            }
        }

    } 

    public ArrayList<String> getSorted(ArrayList<String> sortvar){
        int size = sortvar.size();
        for(int i = 0; i<size-1; i++) {
         for (int j = i+1; j<size; j++) {
            if(sortvar.get(i).compareTo(sortvar.get(j))>0) {
               String temp = sortvar.get(i);
               sortvar.set(i, sortvar.get(j));
               sortvar.set(j, temp);
               // System.out.println(sortvar.toString());
            }
         }
      }
      return sortvar;
  }

    @Override
    public boolean delete(String word) {
    	if(this.search(word) == null){return false;}
    	int i = 0;
    	TrieNode<T> fateher_trav = root;
        TrieNode<T> trav = root;
        if(trav == null){
        	return false;
        }
 		int position_on_node = 0;
        while (i < word.length() && trav.children != null && trav.children.size() > 0) {
            int j = 0;
            Character char_to_inset = word.charAt(i);
            position_on_node = -1;
	        boolean word_present = false;
            for(TrieNode<T> c : trav.children){
            	position_on_node++;
            	// System.out.println(trav.children.get(position_on_node).string_char);
            	// System.out.println(trav.children.get(position_on_node).character);
            	// System.out.println(char_to_inset);
            	// System.out.println(c.character.equals(char_to_inset));

	            if(c.character.equals(char_to_inset)){
	            	word_present = true;
	            	break;
	            }
            }
            String label = "";
            if(word_present){
		        label = trav.children.get(position_on_node).string_char;
		    } else{
		    	return false;
		    }
 			// System.out.println(label);
            while (i < word.length() && j < label.length()) {
                if (word.charAt(i) != label.charAt(j)) {
                    
                   // System.out.println(" character mismatch");
                    return false;   
                }
                 
                ++i;
                ++j;
            }
 			// System.out.println();
            if (j == label.length() && i <= word.length()) {
            	// System.out.println("hello");
            	fateher_trav = trav;
                trav = trav.children.get(position_on_node);    // traverse further
            } else {
                // edge label is larger than target word
                // searching for "face" when tree has "facebook"
                return false;
            }
        }
         
        // target word fully traversed and current node is a word ending
        if(trav != null && trav.isWord ){
        	if(trav.children.size() == 0){
        		trav = null;
        		fateher_trav.children.remove(position_on_node);
        		if(fateher_trav.children.size() == 1){

        		}
        	} else{
        		trav.value = null;
        		trav.isWord = false;
        	}
        	return true;
        }
        // return null;

    	return false;
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
    public ArrayList<Character> level_char_arr;
    public void printLevelHelper(TrieNode<T> trieNode, int level){
        if(trieNode != null && level > 0){
            ArrayList<TrieNode<T>> g = trieNode.children;
            String temp_obj = trieNode.string_char;
        	Character k = temp_obj.charAt(0);
            if(temp_obj.length() >= level){
            	k = temp_obj.charAt(level -1);
            	if(!k.equals(' ')){level_char_arr.add(temp_obj.charAt(level-1)); }

            } else if(g.size() > 0){
            	// System.out.println("yahan par");
                level = level - temp_obj.length();
	            for(int i = 0; i<g.size(); i++){
	                TrieNode<T> e = g.get(i);
                    printLevelHelper(e, level);
	            } 
            
	        }

        }
    }
    @Override
    public void printLevel(int level) {
        // level_arr = new ArrayList<String>();
        level_char_arr = new ArrayList<Character>();
        ArrayList<Character> answer = new ArrayList<Character>();
        for(int i = 0; i<root.children.size(); i++){
        	// this.printTrieHelper(root.children.get(i));
	        this.printLevelHelper(root.children.get(i), level);
        }
        // ArrayList<String> name_array = new ArrayList<String>();
        // for(String c : level_arr){
        // 	String sub_word = c.substring(7, c.length());
        // 	// System.out.println(sub_word + " word to pringt");
        // 	name_array.add(sub_word.split(",")[0].trim());
            
        // }
        // for(String c:name_array){
        // 	if(c.length() > level-1 ){
        // 		Character k = c.charAt(level-1);
        // 		if(!k.equals(' ')){answer.add(c.charAt(level-1));}
        // 	}
        // }
        this.getSortedChar(level_char_arr);
        // System.out.println(name_array.toString());
        String answer_st = "Level " + level + ": ";
        // System.out.print("Level " + level + ": ");
        for (int i = 0; i < level_char_arr.size(); i++){
            answer_st = answer_st + level_char_arr.get(i) + ",";
        }
        // System.out.println(answer_st);
        System.out.println(answer_st.substring(0, answer_st.length()-1));

    }

    @Override
    public void print() {
    	System.out.println("-------------");
    	System.out.println("Printing Trie");
    	int level = 1;
    	ans_arr = new ArrayList<String>();
        ArrayList<Character> answer = new ArrayList<Character>();
        this.printTrieHelper(root);
        ArrayList<String> name_array = new ArrayList<String>();
        for(String c : ans_arr){
        	String sub_word = c.substring(7, c.length());
        	name_array.add(sub_word.split(",")[0].trim());
            
        }
        for(String c:name_array){
        	if(c.length() > level){
        		level = c.length();
        	}
        }
        int i = 1;
        for(i = 1; i<= level; i++){
        	this.printLevel(i);
        }
        System.out.println("Level "+ i + ": " );
    	System.out.println("-------------");
        // System.out.println(answer_st.substring(0, answer_st.length()-1));

    }
}