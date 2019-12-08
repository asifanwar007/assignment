// Do not Modify this file. It will be replaced.

interface Table_<K>  {

    // Implements an extendible generic array.
    // The initial Length is 7

    void setLength(int newlength); // Change capacity
    void set(int index, K k);	   // Change the value at index i
    K get(int index);	           // Fetch the value at index i
    int length();		   // Fetch the current capacity
}
