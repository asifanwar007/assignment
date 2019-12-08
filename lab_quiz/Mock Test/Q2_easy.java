/* 
Insert at a position in Linked List
***********************************

Given the head of a linked list, position and value, 
insert the value at index position in the linked list pointed 
by the head. Return the head to the new Linked List.

Sample Test Case:
=================
Input-
  List: 1 2 3 5 6
  Position: 3
  Value: 4
Output:
  List: 1 2 3 4 5 6
*/

import java.util.*;
import java.io.*;
  
/* Linked list Node*/
class Node { 
    int data; 
    Node next; 
    Node(int d) { data = d; } 
    Node() { data = 0; } 
} 

class LinkedList { 
    Node head; // head of list 

//  DO NOT MODIFY ABOVE THIS POINT    
     
    public Node insert_at_position(Node head, int position, int value){
      
      // MODIFY HERE
      return head;
    }

//  DO NOT MODIFY BELOW THIS POINT
    public void create_from_file(String filepath){
      File file = new File(filepath); 
      try{
          Scanner sc = new Scanner(file);
          int n = sc.nextInt();
          head = new Node();
          Node it = head;          
          for (int i=0;i<n;i++){
            it.data = sc.nextInt();
            it.next = new Node();
            it = it.next;
          }
      }
      catch (Exception e){
          System.out.println("FILE NOT FOUND");
          return;
      }
    }

    public void print(){
      Node it=head;
      while(it!=null){
        System.out.print(it.data + " ");
        it = it.next;
      }
      System.out.println();
    }
}

public class Q2_easy{
    public static void main(String[] args){
        System.out.println("hello");
    }
}