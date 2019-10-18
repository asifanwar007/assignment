package Trie;

import java.util.ArrayList;
public class Trie<T> implements TrieInterface {
    public TrieNode<T> root_node; 
    public int length_of_trie = 0;
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

    
    public ArrayList<String> ans_arr;
    public void printTrieHelper(TrieNode<T> trieNode){
        if(trieNode != null){
            ArrayList<Pair<TrieNode<T>, Character, T>> g = trieNode.char_store_array;
            ArrayList<T> temp_obj = trieNode.getValuehelp();
            if(temp_obj != null){
                for(T e: temp_obj){
                    // System.out.println(ans_arr.toString());
                    if(ans_arr.contains(e.toString()) != true){ans_arr.add(e.toString()); }
                }
            } 
            for(int i = 0; i<g.size(); i++){
                Pair<TrieNode<T>, Character, T> e = g.get(i);
                if(e != null) {
                    if(e.next_node != null){printTrieHelper(e.next_node); }
                    if(e.next_node != null){
                        ArrayList<T> temp_obj1 = e.next_node.getValuehelp();
                        if(temp_obj != null){
                            System.out.println(temp_obj1.toString());
                            for(T f: temp_obj1){
                                if(ans_arr.contains(f.toString()) != true){ans_arr.add(f.toString()); }
                            }
                        } 

                    } 
                }
            }
        }

    } 
    public void printTrie(TrieNode trieNode) {

        ans_arr = new ArrayList<String>();
        this.printTrieHelper(trieNode);
            // System.out.println(ans_arr.toString());
        this.getSorted(ans_arr);
            // System.out.println("helo");
        for(String e: ans_arr){
            System.out.println(e);
        }


        

    }
    @Override
    public boolean delete(String word) {
        if (this.search(word) != null){
            int len_word = word.length();
            TrieNode<T> head_node = root_node.getCharacterNode(word.charAt(0));
            TrieNode<T> temp_node_del = root_node.getCharacterNode(word.charAt(0));
            Character temp_char = word.charAt(0);
            if(head_node == null){
                // System.out.println("if root_node is null");
                return false;
            } else if(root_node.getCharacterNode(word.charAt(0)) == null) {
                // System.out.println("If char doesn't match in root_node");
                return false;
            } else{
                head_node = root_node.getCharacterNode(word.charAt(0));
                // System.out.println("If char is matched so changing the root_node");
                if(len_word == 1 && root_node.isWord()){
                    root_node.remWord();
                    if(head_node == null){
                        root_node.remCharacterNode(word.charAt(0));
                    }
                    return true;
                }
            }
            for(int i = 1; i < word.length(); i++){
                // System.out.println("helo");
                Character char_to_check = word.charAt(i);
                // System.out.println(char_to_check);
                if((head_node == null || i == len_word-1) && (head_node.getCharacterNode(char_to_check) != null) && head_node.isWord()){
                    if(head_node.char_store_array.size() > 1){
                    // System.out.println("finally Character matched");
                        temp_node_del = head_node;
                        temp_char = word.charAt(i);
                    }

                    if(i == len_word - 1 && root_node.isWord()){
                        root_node.remWord();
                        if(head_node.getCharacterNode(char_to_check) != null){
                            return true;
                        }
                    }
                } else if(head_node.getCharacterNode(char_to_check) == null){
                    // System.out.println("charchter didn't matched");
                    return false;
                } else{
                    // System.out.println(i + " the time in this");
                    // System.out.println(head_node.getCharacterNode(char_to_check) != null);
                    // System.out.println("hehe");
                    if(head_node.char_store_array.size() > 1){
                        temp_node_del = head_node;
                        temp_char = word.charAt(i);
                    }
                    if(i == len_word - 1 && root_node.isWord()){
                        root_node.remWord();
                        if(head_node.getCharacterNode(char_to_check) != null){
                            return true;
                        }
                    }
                    head_node = head_node.getCharacterNode(char_to_check);

                }
            }
            temp_node_del.remCharacterNode(temp_char);
            return true;
        }
        return false;
    }


    @Override
    public TrieNode search(String word) {
        int len_word = word.length();
        if(root_node == null){
            return null;
        }
        TrieNode<T> head_node = root_node;
        
        for(int i = 0; i < word.length(); i++){
            Character char_to_check = word.charAt(i);
            Pair<TrieNode<T>, Character, T> head_node_pair = head_node.getCharacterNodeInsert(char_to_check);
            if(head_node_pair == null){
                return null;
            } else{
                if(len_word == i + 1 && head_node.isWord()){return head_node; }
            }
            if(head_node_pair != null){head_node = head_node_pair.next_node; }
        }
        return null;
    }

    @Override
    public TrieNode startsWith(String prefix) {
        int len_word = prefix.length();
        String word = prefix;
        if(root_node == null){return null; }
        TrieNode<T> head_node = root_node;
        for(int i = 0; i < word.length(); i++){
            Character char_to_check = word.charAt(i);
            Pair<TrieNode<T>, Character, T> head_node_pair = head_node.getCharacterNodeInsert(char_to_check);
            if(head_node_pair == null){
                return null;
            } else{
                if(len_word == i + 1){return head_node; }
            }
            if(head_node_pair != null){head_node = head_node_pair.next_node; }
        }
        return null;
    }

    @Override
    public boolean insert(String word, Object value) {
        if(length_of_trie < word.length()){length_of_trie = word.length();}
        if(root_node == null){
            TrieNode<T> temp1 = new TrieNode<T>();
            temp1.addValueToCharacterArray(root_node, word.charAt(0));
            root_node = temp1;
            Pair<TrieNode<T>, Character, T> head_node_pair1 = temp1.getCharacterNodeInsert(word.charAt(0));

            if(1 == word.length()){
                root_node.setWord();
                root_node.setChar(word.charAt(0));
                root_node.setValue(value);
                return true;
                // System.out.println("helo");
            }
        } else if(root_node.getCharacterNodeInsert(word.charAt(0)) == null) {
            root_node.addValueToCharacterArray(root_node, word.charAt(0));
            Pair<TrieNode<T>, Character, T> head_node_pair1 = root_node.getCharacterNodeInsert(word.charAt(0));
            if(1 == word.length()){
                root_node.setWord();
                root_node.setChar(word.charAt(0));
                root_node.setValue(value);
                return true;
            }
        } //else{t = false;}
        
        TrieNode<T> head_node = root_node;
        Pair<TrieNode<T>, Character, T> head_node_pair = head_node.getCharacterNodeInsert(word.charAt(0));
        for(int i = 1; i < word.length(); i++){
            Character char_to_insert = word.charAt(i);
            if(head_node_pair != null && head_node_pair.next_node == null){
                TrieNode<T> temp1 = new TrieNode<T>();
                temp1.addValueToCharacterArray(root_node, word.charAt(i));
                head_node_pair.next_node = temp1;
                if(i == word.length()-1 && !temp1.isWord()){
                    temp1.setWord();
                    temp1.setChar(char_to_insert);
                    temp1.setValue(value);
                    return true;
                }
                head_node_pair = temp1.getCharacterNodeInsert(char_to_insert);
            } else{
                TrieNode<T> temp1_head_node = head_node_pair.next_node;
                head_node_pair = temp1_head_node.getCharacterNodeInsert(char_to_insert);
                if(head_node_pair == null){
                    temp1_head_node.addValueToCharacterArray(root_node, char_to_insert);
                }
                head_node_pair = temp1_head_node.getCharacterNodeInsert(char_to_insert);
                if(i == word.length()-1 && !temp1_head_node.isWord()){
                    temp1_head_node.setWord();
                    temp1_head_node.setChar(char_to_insert);
                    temp1_head_node.setValue(value);
                    return true;
                }
            }
        }
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
    public ArrayList<ArrayList<Pair<TrieNode<T>, Character, T>>> level_arr;
    public ArrayList<ArrayList<Pair<TrieNode<T>, Character, T>>> printLevelHelper(TrieNode<T> node, int level){
        if (level <= 0){return level_arr;}
        if(level == 1){
            level_arr.add(node.char_store_array); 
            return level_arr;}
        for(int i = 0; i<node.char_store_array.size(); i++){
            TrieNode<T> temp = node.char_store_array.get(i).next_node;
            printLevelHelper(temp, level-1);
        }
        return level_arr;
    }
    @Override
    public void printLevel(int level) {
        level_arr = new ArrayList<ArrayList<Pair<TrieNode<T>, Character, T>>>();
        ArrayList<Character> answer = new ArrayList<Character>();
        printLevelHelper(root_node, level);
        for(int i = 0; i < level_arr.size(); i++){
            for(int j = 0; j < level_arr.get(i).size(); j++){
                Character a = (level_arr.get(i).get(j).pair_character_value);
                answer.add(a);
            }
        }
        this.getSortedChar(answer);
        String answer_st = "Level " + level + ": ";
        // System.out.print("Level " + level + ": ");
        for (int i = 0; i < answer.size(); i++){
            answer_st = answer_st + answer.get(i) + ",";
        }
        // System.out.println(answer_st);
        System.out.println(answer_st.substring(0, answer_st.length()-1));

    }

    @Override
    public void print() {
        System.out.println(length_of_trie);
        for(int i = 0; i < length_of_trie; i++){
            this.printLevel(i+1);
        }

    }
    // public static void main(String[] args) {
    //     Person p = new Person("Asif", "123456");
    //     Person p1 = new Person("Asifa", "2123456");
    //     Person p2 = new Person("Asifn", "1223456");
    //     Trie<Person> tr = new Trie<Person>();
    //     System.out.println(tr.insert(p.getName(), p));
    //     System.out.println("---------2nd insert starts here-------");
    //     System.out.println(tr.insert(p.getName(), p));
    //     System.out.println("---------3rd insert starts here-------");
    //     System.out.println(tr.insert(p1.getName(), p1));
    //     System.out.println(tr.insert(p2.getName(), p2));
    //     // tr.print();
    //     Object as = tr.search("helo");
    //     if (as == null){
    //             System.out.println("False");
    //     } else{System.out.println("True");}
    //     // System.out.println("Delete starts here--------");
    //     System.out.println(tr.delete(p.getName()));
    //     System.out.println("search asif " );
    //     System.out.println(p.getName());
    //     Object s = tr.search(p.getName());
    //     System.out.println("search end");
    //     if (s == null){
    //             System.out.println("False");
    //     } else{System.out.println("True");}
    //     // System.out.println(tr.insert(p2.getName(), p2));
    // }
}