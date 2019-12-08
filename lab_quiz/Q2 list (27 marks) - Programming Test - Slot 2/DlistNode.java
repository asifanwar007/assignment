// Do not Modify this file. This file will be replaced.

class DlistNode<T> extends Node2<T> {

   // Constructor
   DlistNode(T value) { super(value, null, null); }

   // Get value, previous and next references
   public T value()           { return super.value(); }
   public DlistNode<T> prev() { return (DlistNode<T>)first(); }
   public DlistNode<T> next() { return (DlistNode<T>)second(); }

   // Set previous and next references
   public void setprev(DlistNode<T> n) { setfirst(n); }
   public void setnext(DlistNode<T> n) { setsecond(n); }
}
