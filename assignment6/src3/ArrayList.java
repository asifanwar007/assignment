public class ArrayList<T>{
    public Object array[]; 
    private int count; 
    private int size; 
  
    public ArrayList() {
        array = new Object[1]; 
        count = 0; 
        size = 1; 
    } 
    public void add(T data){
        if (count == size) { 
            growSize(); 
        }
        array[count] = data; 
        count++; 
    } 
  
    public void growSize() {
  
        Object temp[] = null; 
        if (count == size) { 
  
            temp = new Object[size * 2]; 
            { 
                for (int i = 0; i < size; i++) { 
                    temp[i] = array[i]; 
                } 
            } 
        } 
        array = temp; 
        size = size * 2; 
    } 
    // public void shrinkSize() {
    //     Object temp[] = null; 
    //     if (count > 0) { 
  
    //         temp = new Object[count]; 
    //         for (int i = 0; i < count; i++) { 
  
    //             temp[i] = array[i]; 
    //         } 
  
    //         size = count; 
    //         array = temp; 
    //     } 
    // } 
  
    public void add(int index, T data) {
        if (count == size) { 
            growSize(); 
        } 
  
        for (int i = count - 1; i >= index; i--) { 
  
            array[i + 1] = array[i]; 
        } 
        array[index] = data; 
        count++; 
    } 
  
    public void remove() {
        if (count > 0) { 
            array[count - 1] = null; 
            count--; 
        } 
    } 
  	
  	public int size(){
  		return this.count;
  	}
    public void remove(int index) {
        if (count > 0) { 
            for (int i = index; i < count - 1; i++) { 
                array[i] = array[i + 1]; 
            } 
            array[count - 1] = 0; 
            count--; 
        } 
    }
    public T get(int index){
    	return (T) array[index];
    }
}