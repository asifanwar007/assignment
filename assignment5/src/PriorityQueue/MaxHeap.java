package PriorityQueue;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {
	public ArrayList<ArrayList<T>> binHeap;

	public MaxHeap(){
		this.binHeap = new ArrayList<ArrayList<T>>();
		binHeap.add(null);
	}

    @Override
    public void insert(T element) {
    	ArrayList<T> arr = new ArrayList<T>();
    	boolean same_ele = false;
    	boolean root_check = false;
    	if(binHeap.get(0) == null){
    		arr.add(element);
    		binHeap.set(0, arr);
    		binHeap.add(null);
    		// System.out.println(binHeap.toString());
    		root_check = true;
    	} 
    	//duplicate check
    	int p = 0;
    	while(binHeap.get(p) != null && !root_check){
    		if(binHeap.get(p) != null && (binHeap.get(p).get(0).compareTo(element) == 0)){
    			binHeap.get(p).add(element);
    			same_ele = true;
    			break;
    		} else{
    			p++;
    		}
    	}
    	int i = 0;
    	while(binHeap.get(i) != null && !root_check && !same_ele){
    		int left_node = 2*i + 1;
    		int right_node = 2*i + 2;
    		while(binHeap.size() <= left_node + 1 || binHeap.size() <= right_node + 1){
    			binHeap.add(null);
    		}
    		// System.out.println(binHeap.get(i).toString());
    		// System.out.println(binHeap.get(i).get(0).compareTo(element));
    		// System.out.print("element: ");
    		// System.out.println(element.toString());
    	// 	if(binHeap.get(i) != null && (binHeap.get(i).get(0).compareTo(element) == 0)){
					// binHeap.get(i).add(element);
					// System.out.println("-----------");
					// System.out.println(binHeap.get(i).toString());
					// same_ele = true;
					// break;
    	// 	}
    		if (!same_ele){
    			// System.out.println(i + " " + binHeap.size() + " " +right_node);
	    		if(binHeap.get(left_node) == null){
	    			i = left_node;
    		// System.out.println("helo");
	    		} else if(binHeap.get(right_node) == null){
	    			// System.out.println("helo 1");
	    			i = right_node;
	    		} else{
	    			i++;
	    		}
	    	} else{
	    		break;
	    	}
    	}
    	// System.out.println(i);
    	// System.out.println(same_ele);

    	if(!same_ele && !root_check){
	    	arr.add(element);
	    	if(binHeap.size() > i){
		    	binHeap.set(i, arr);
		    	swap(i, binHeap);
	    	} else{
	    		// System.out.println(binHeap.size() + " sdf;");
	    		while(binHeap.size() <= i+1){
	    			binHeap.add(null);
	    		}
	    		// System.out.println(i + " " + binHeap.size());
	    		binHeap.set(i, arr);
	    		swap(i, binHeap);
	    	}
	    }

	    // System.out.println(binHeap.toString());
	    // System.out.println("element insert end");
	    // System.out.println();
    }
    public void swap(int i, ArrayList<ArrayList<T>> heap_array){
    	while(i > 0){
	    	int parent_node = (i - 1) / 2;
    		ArrayList<T> temp_arr = binHeap.get(i);
    		ArrayList<T> parent_arr = binHeap.get(parent_node);
			int len_of_arr = temp_arr.size();
			// System.out.println(temp_arr.get(0).compareTo(parent_arr.get(0)) + " :adkfkdfj");
			if(temp_arr.get(0).compareTo(parent_arr.get(0)) > 0 ){
				ArrayList<T> temp_child_value = temp_arr;
				binHeap.set(i, parent_arr);
				binHeap.set(parent_node, temp_child_value);
				i = parent_node;
			} else{
				break;
			}
    	}
    }

    @Override
    public T extractMax() {
    		// System.out.println(binHeap.toString());
    	if(binHeap.get(0) == null || binHeap.size() < 0){
    		return null;
    	}
    	T ans = binHeap.get(0).get(0);
    	if(binHeap.get(0).size() > 1){
    		binHeap.get(0).remove(0);
    		// System.out.println("hell");
    	} else{
    		int i = 0;
    		while(binHeap.get(i) != null){
	    		int left_child_node = 2*i+1;
	    		int right_child_node = 2*i+2;
	    		while(binHeap.size() <= left_child_node + 1 || binHeap.size() <= right_child_node + 1){
    			binHeap.add(null);
    		}
	    		int k = i + 1;
	    		if(binHeap.get(left_child_node) == null && binHeap.get(k) != null){
	    			i++;
	    			break;
	    		} else if (binHeap.get(left_child_node) == null){
	    			break;	
	    		} else if(binHeap.get(right_child_node) == null){
	    			i = left_child_node;
	    			break;
	    		}else{
	    			i++;
	    		}
    		}
    		// System.out.println(i + "-----------------");
    		// if(i != 0){
    		ArrayList<T> last_node = binHeap.get(i);
    		binHeap.set(0, last_node);
    		binHeap.remove(i);
    		// System.out.println(binHeap.toString());
    		// System.out.println();
    		swapMax(binHeap);
    	}
        return ans;
    }
    public void swapMax(ArrayList<ArrayList<T>> heap_array){
    	int i = 0;
    	while(binHeap.get(i)!= null){
    		int left_child = 2*i+1;
    		int right_child = 2*i+2;
    		int len = binHeap.size();
    		if(left_child >= len){
    			break;
    		}
    		ArrayList<T> parent_node = binHeap.get(i);
    		if(binHeap.get(left_child) == null){
    			break;
    		} else if(right_child < len){
    			if(binHeap.get(right_child) == null){
		    		ArrayList<T> left_child_node = binHeap.get(left_child);
    		    	if(left_child_node.get(0).compareTo(parent_node.get(0)) > 0){
    					binHeap.set(left_child, parent_node);
    					binHeap.set(i, left_child_node);
    					i = left_child;
    				} else{
    					break;
    				}
    			} else{
		    		ArrayList<T> left_child_node = binHeap.get(left_child);
		    		ArrayList<T> right_child_node = binHeap.get(right_child);
		    		if(left_child_node.get(0).compareTo(right_child_node.get(0)) > 0){
			    		if(left_child_node.get(0).compareTo(parent_node.get(0)) > 0){
	    					binHeap.set(left_child, parent_node);
	    					binHeap.set(i, left_child_node);
	    					i = left_child;
	    				} else{
	    					break;
	    				}
	    			} else if(right_child_node.get(0).compareTo(parent_node.get(0)) > 0){
	    				binHeap.set(right_child, parent_node);
    					binHeap.set(i, right_child_node);
    					i = right_child;
	    			} else{
	    				break;
	    			}
    			}
    		} else{
	    		ArrayList<T> left_child_node = binHeap.get(left_child);
	    		if(left_child_node.get(0).compareTo(parent_node.get(0)) > 0){
    					binHeap.set(left_child, parent_node);
    					binHeap.set(i, left_child_node);
    					i = left_child;
    				} else{
    					break;
    				}

    		}
	    	
		}
    }
}