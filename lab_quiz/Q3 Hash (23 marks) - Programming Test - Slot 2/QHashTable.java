import java.util.Scanner;
import java.util.Random;

// Key_ is a key made of a number of parts. See Key_.java.
// HashTableAbstract extends Table and implements HashTable_ (some functions abstract)
// It also allows marking a slot deleted. (see HashTable_.java)
// It is also a Table_ of Key_ objects -- See Table_.java for Table interface
// This means you will need open addressing.
class HashTable<K extends Key_> extends HashTableAbstract<K> {

   HashTable(K noKey) { super(noKey); }

   public void add(K k) { 
  /* 10 marks TODO: MODIFY THIS. You may write helper functions in this file
   *       Adds Key k to the Hash Table. Assume the Key is unique.
   *       See Table_ to determine how to set and access the table indexes, and resize it
   */

    }

   public boolean has(K k) {
  /* 7 marks TODO: MODIFY THIS. You may write helper functions in this file
   *       Checks of Key k exists in the Hash Table (one Key appears only once).
   *       See Table_ to determine how to access the table indexes, and resize it
   */

      return false;

   }

   public boolean remove(K k) {
  /* 6 marks TODO: MODIFY THIS. You may write helper functions in this file
   *       Removes Key k from the Hash Table if it exists. Returns whether k was removed.
   *       See Table_ to determine how to set and access the table indexes, and resize it
   */

      return false;
   }
}

class main {

    static void doLine(HashTable<Key> ht, String line) {
       String[] cmd = line.split(" ", 2);
       if (cmd.length < 2) return;
       switch (cmd[0]) {
          case "add":   ht.add(new Key(cmd[1])); break;
          case "has":   System.out.println(ht.has(new Key(cmd[1]))); break;
          case "remove":System.out.println(ht.remove(new Key(cmd[1])));
       }
    }

   public static void main(String arg[]) {
      // Key implements interface Key_
      HashTable<Key> ht = new HashTable<Key>(Key.noKey());

      Scanner input = new Scanner(System.in);
      while(input.hasNext()) doLine(ht, input.nextLine());
      ht.print(false);
   }
}
