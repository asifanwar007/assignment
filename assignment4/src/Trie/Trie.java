package Trie;

import java.util.ArrayList;
public class Trie<T> implements TrieInterface {
    public TrieNode<T> root_node; 

    public ArrayList<String> getSorted(ArrayList<String> sortvar){
        int size = sortvar.size();
        for(int i = 0; i<size-1; i++) {
         for (int j = i+1; j<size; j++) {
            if(sortvar.get(i).compareTo(sortvar.get(j))>0) {
               String temp = sortvar.get(i);
               sortvar.add(i, sortvar.get(j));
               sortvar.add(j, temp);
            }
         }
      }
      return sortvar;
  }

    
    public ArrayList<String> ans_arr;
    public void printTrieHelper(TrieNode<T> trieNode){
        if(trieNode != null){
            ArrayList<Pair<TrieNode<T>, Character>> g = trieNode.char_store_array;
            for(int i = 0; i<g.size(); i++){
                Pair<TrieNode<T>, Character> e = g.get(i);
                if(e.pair_node != null){
                    printTrieHelper(e.pair_node);
                }
                if(e.pair_node.getValue() != null && (ans_arr.contains(e.pair_node.getValue().toString()) != true)){
                    ans_arr.add(e.pair_node.getValue().toString());
                }
            }
        }

    } 
    public void printTrie(TrieNode trieNode) {
        ans_arr = new ArrayList<String>();
        this.printTrieHelper(trieNode);
        this.getSorted(ans_arr);
        for(String e: ans_arr){
            // System.out.println("helo");
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
        TrieNode<T> head_node = root_node.getCharacterNode(word.charAt(0));
        if(head_node == null){
            // System.out.println("if root_node is null");
            return null;
        } else if(root_node.getCharacterNode(word.charAt(0)) == null) {
            // System.out.println("If char doesn't match in root_node");
            return null;
        } else{
            head_node = root_node.getCharacterNode(word.charAt(0));
            // System.out.println("If char is matched so changing the root_node");
            if(len_word == 1 && root_node.isWord()){
                // root_node.setWord();
                // root_node.setValue(value);
                return head_node;
            }
        }
        for(int i = 1; i < word.length(); i++){
            Character char_to_check = word.charAt(i);
            // System.out.println("inside search");
            if((head_node == null || i == len_word-1) && (head_node.getCharacterNode(char_to_check) != null) && head_node.isWord()){
                // System.out.println("finally Character matched");
                return head_node;
            } else if(head_node.getCharacterNode(char_to_check) == null){
                // System.out.println("charchter didn't matched");
                return null;
            } else{
                // System.out.println(i + " the time in this");
                // System.out.println(head_node.getCharacterNode(char_to_check) != null);
                head_node = head_node.getCharacterNode(char_to_check);
            }
        }
        return null;
    }

    @Override
    public TrieNode startsWith(String prefix) {
        int len_prefix = prefix.length();
        if(root_node == null){
            return null;
        }
        TrieNode<T> head_node = root_node.getCharacterNode(prefix.charAt(0));
        if(head_node == null){
            // System.out.println("if root_node is null");
            return null;
        } else if(root_node.getCharacterNode(prefix.charAt(0)) == null) {
            // System.out.println("If char doesn't match in root_node");
            return null;
        } else{
            head_node = root_node.getCharacterNode(prefix.charAt(0));
            // System.out.println("If char is matched so changing the root_node");
            if(len_prefix == 1){
                // root_node.setprefix();
                // root_node.setValue(value);
                return head_node;
            }
        }
        for(int i = 1; i < prefix.length(); i++){
            Character char_to_check = prefix.charAt(i);
            // System.out.println("inside search");
            if((head_node == null || i == len_prefix-1) && (head_node.getCharacterNode(char_to_check) != null)){
                // System.out.println("finally Character matched");
                return head_node;
            } else if(head_node.getCharacterNode(char_to_check) == null){
                // System.out.println("charchter didn't matched");
                return null;
            } else{
                // System.out.println(i + " the time in this");
                // System.out.println(head_node.getCharacterNode(char_to_check) != null);
                head_node = head_node.getCharacterNode(char_to_check);
            }
        }
        return null;
    }

    @Override
    public boolean insert(String word, Object value) {

        
        boolean t = true;
        TrieNode<T> temp = new TrieNode<T>();
        if(root_node == null){
            TrieNode<T> temp1 = new TrieNode<T>();
            temp.addValueToCharacterArray(temp1, word.charAt(0));
            root_node = temp;
            if(1 == word.length()){
                root_node.setWord();
                root_node.setValue(value);
            }
            // head_node = temp;
            // System.out.println("why I am getting null pointer Exception");
            // root_node = temp;
            // head_node = temp;
            // if(word.length() > 1)
            // {head_node = head_node.node;}
            t = true;
        } else if(root_node.getCharacterNode(word.charAt(0)) == null) {
            root_node.addValueToCharacterArray(root_node, word.charAt(0));
            if(1 == word.length()){
                root_node.setWord();
                root_node.setValue(value);
            }
            t = true;
        } else{t = false;}
        TrieNode<T> head_node = root_node.getCharacterNode(word.charAt(0));
        for(int i = 1; i < word.length(); i++){
            Character char_to_insert = word.charAt(i);
             
            // System.out.println("value of char: " + char_to_insert +" value of char outside the systme: " + i + " " + head_node.char_store_array.get(0).pair_character_value);
            TrieNode<T> temp2 = new TrieNode<T>();
            if(head_node == null){
                // System.out.println("inside first if");
                head_node.addValueToCharacterArray(temp2, char_to_insert);
                if(i == word.length() - 1){
                    head_node.setWord();
                    head_node.setValue(value);
                }
                head_node = temp2;
                // head_node = temp1;
            // System.out.println("value of char: " + head_node.char_store_array.get(i).pair_character_value);

                t = true;
            } else if(head_node.getCharacterNode(char_to_insert) == null){
                // System.out.println("inside 2nd if");
                // TrieNode<T> temp_trie_node = head_node.getCharacterNode(char_to_insert);
                head_node.addValueToCharacterArray(temp2, char_to_insert);
                head_node.addValueToCharacterArray(temp2, char_to_insert);
                if(i == word.length() - 1){
                    head_node.setWord();
                    head_node.setValue(value);
                }
                head_node = temp2;
            // System.out.println("value of char: " + head_node.char_store_array.get(i).pair_character_value);
                // head_node = temp1;
                t = true;
            } else{
                // System.out.println("inside equal statemnet:- ");
                head_node = head_node.getCharacterNode(char_to_insert);
                // if(i == word.length() - 1){
                //     head_node.setWord();
                //     head_node.setValue(value);
                // }
                // head_node = temp_trie_node;
            // System.out.println("value of char: " + head_node.char_store_array.get(i).pair_character_value);
                t = false;
            }

            

        }
        
        // System.out.println("ending of for loop--------");
        // System.out.println(t);
        if(t){
            //     head_node.setWord();
            // // System.out.println("helo");
            //     head_node.setValue(value);
                return t;
        }        return false;
    }

    @Override
    public void printLevel(int level) {

    }

    @Override
    public void print() {

    }
    public static void main(String[] args) {
        Person p = new Person("Asif", "123456");
        Person p1 = new Person("Asifa", "2123456");
        Person p2 = new Person("Asifn", "1223456");
        Trie<Person> tr = new Trie<Person>();
        System.out.println(tr.insert(p.getName(), p));
        System.out.println("---------2nd insert starts here-------");
        System.out.println(tr.insert(p.getName(), p));
        System.out.println("---------3rd insert starts here-------");
        System.out.println(tr.insert(p1.getName(), p1));
        System.out.println(tr.insert(p2.getName(), p2));
        // tr.print();
        Object as = tr.search("helo");
        if (as == null){
                System.out.println("False");
        } else{System.out.println("True");}
        // System.out.println("Delete starts here--------");
        System.out.println(tr.delete(p.getName()));
        System.out.println("search asif " );
        System.out.println(p.getName());
        Object s = tr.search(p.getName());
        System.out.println("search end");
        if (s == null){
                System.out.println("False");
        } else{System.out.println("True");}
        // System.out.println(tr.insert(p2.getName(), p2));
    }
}