package Trie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;


public class Trie<T> implements TrieInterface<T> {
	
	private TrieNode<T> root;
	
	//constructor
	public Trie() {
		root=new TrieNode<T>(' ');
	}

//printTrie
    public void printTrie(TrieNode trieNode) {
//    	System.out.println(trieNode.childList.size()==0);
    	if (trieNode.childList.size()==0) {
			System.out.println(trieNode.getValue().toString());				//edit needed
		}else {
			ArrayList<TrieNode<T>> arrr = trieNode.childList;
			for (int i = 0; i < trieNode.childList.size(); i++) {
				TrieNode<T> tempNode=arrr.get(i);
				printTrie(tempNode);
			}
		}
    	
    }
//delete
    @Override
    public boolean delete(String word) {
    	if (search(word) == null)
        {
            return false;
        }
        TrieNode<T> current = root;
        for (char ch : word.toCharArray()) 
        { 
            TrieNode<T> child = current.subNode(ch);
            if (child.count == 1) 
            {
                current.childList.remove(child);
                return true;
            } 
            else 
            {
                child.count--;
                current = child;
            }
        }
        current.isWord = false;
        return true;
    }
    
//search
    @Override
    public TrieNode<T> search(String word) {
    	{
            TrieNode<T> current = root;  
            for (char ch : word.toCharArray() )
            {
                if (current.subNode(ch) == null)
                    return null;
                else
                    current = current.subNode(ch);
            }      
            if (current.isWord == true) 
                return current;
            return null;
        }
    }
//startWith
    @Override
    public TrieNode startsWith(String prefix) {
    	{
            TrieNode<T> current = root;  
            for (char ch : prefix.toCharArray() )
            {
                if (current.subNode(ch) == null)
                    return null;
                else
                    current = current.subNode(ch);
            }      
            return current;
        }
    }
//insert
    @Override
    public boolean insert(String word, T value) {
    	if (search(word)!=null) 
    		return false;
    	
		TrieNode<T> currentNode = root;
		for (char ch : word.toCharArray()) {
			TrieNode<T> child = currentNode.subNode(ch);
			if (child!=null)
				currentNode=child;
			else {
				currentNode.childList.add(new TrieNode<T>(ch));
				currentNode=currentNode.subNode(ch);
			}
			currentNode.count++;
			
		}
		currentNode.isWord=true;
		currentNode.value=value;
		return true;
    }

//    ArrayList<Character> chararr;
//    private void recc_pl(TrieNode<T> node, int l) {
//    	if (node.childList.size()!=0) {
//    		if (l==1) {
//    			for (int i = 0; i < node.childList.size(); i++) {
//    				if ((node.childList.get(i)!=null)) {
//    												System.out.println(node.childList.get(i)==null);
//    					chararr.add(node.childList.get(i).content);
//    				}
//    				
//    			}
//    		}else {
//    			for (int i = 0; i < node.childList.size(); i++) {
//    				if (node.childList.get(i)!=null) {
//    					recc_pl(node.childList.get(i), l--);
//    				}
//    			}
//    		}
//		}
//		
//		
//	}
//print level  
    @Override
    public void printLevel(int level) {
    	System.out.print("Level "+level+": ");
    	pl(arange(levelPrint(root, level)));
    	System.out.println();
    	
    }
    
    public void pl(ArrayList<Character> arr) {
    	int l=arr.size();
    	ArrayList<Character> lll= new ArrayList<Character>();
    	
    	for (int i = 0; i < l; i++) {
    		if (arr.get(i)!=' ') {
    			
    			lll.add(arr.get(i));
    			lll.add(',');
			}
		}
    	for (int i = 0; i < lll.size()-1; i++) {
    		System.out.print(lll.get(i));
		}
    	
    }
    
    
//print
    @Override
    public void print() {
    	int l=1;
    	ArrayList<Character> arr = levelPrint(root, l);
    	System.out.println("-------------");
    	System.out.println("Printing Trie");
    	while (arr.size()!=0) {
    		System.out.print("Level "+l+": ");
    		arr = levelPrint(root, l++);
    		pl(arange(arr));
    		System.out.println();
    		
    		
    	}
    	System.out.println("-------------");
    }
    
    
    public ArrayList<Character> levelPrint(TrieNode<T> root, int level) {
		//empty que to store
    	Queue<TrieNode<T>> q = new ArrayDeque<TrieNode<T>>();
		//enque root node
		q.add(root);
		//char arr
		ArrayList<Character> arr = new ArrayList<Character>();
		int size = q.size();
		int n= 0;
		
		while ((!q.isEmpty())&&(n<=level)) {
			
			if (n==level) {
				
				for (int i = 0; i < size; i++) {
					TrieNode<T> eachNode = q.poll();
					arr.add(eachNode.content);
				}
				
				
			}else {
				for (int i = 0; i < size; i++) {
					TrieNode<T> eachNode = q.poll();
					int len= eachNode.childList.size();
					if (len==0) {
						continue;
					}else {
						for (int j = 0; j < len; j++) {
							q.add(eachNode.childList.get(j));
						}
					}
					
				}
				 n++;
				 size= q.size();
			}
			
		}
		return arr;
		
	}
    
    
    
    public ArrayList<Character> arange(ArrayList<Character> arr) {
		ArrayList<Character> newarr= new ArrayList<Character>();
		for (int i = 0; i < arr.size(); i++) {
			char temp1=arr.get(i);
			int n=0;
			for (int j = 0; j < newarr.size(); j++) {
				if (temp1>newarr.get(j)) {
					n++;
				}else {
					break;
				}
			}
			newarr.add(n, temp1);
		}
		
		
		return newarr;
	}
    
    
    
    
    
    
    
    
    
    
}