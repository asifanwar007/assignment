// Do not Modify this file. It will be replaced for grading.

interface HashTable_<K> {
   void markAbsent(int index);  // Mark a slot as deleted
   boolean isMarked(int index); // Check is a slot is null or marked deleted

   void add(K k);
   boolean has(K k);
   boolean remove(K k);
}
