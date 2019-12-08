// Do not modify this class. This will be replaced for grading.
// This is for information.

class BSTNode<T> extends Node2<T> {
   // For information. This should not be modified.

   // Constructor with value, left and right child references
   BSTNode(T value) { super(value, null, null); }

   // Get value, left and right child references
   public T value()   { return super.value(); }
   public BSTNode<T> left()  { return (BSTNode<T>)first(); }
   public BSTNode<T> right() { return (BSTNode<T>)second(); }

   // Set value and left and right child references
   public void setvalue(T v) { super.setvalue(v); }
   public void setleft(BSTNode<T> n) { setfirst(n); }
   public void setright(BSTNode<T> n) { setsecond(n); }
}
