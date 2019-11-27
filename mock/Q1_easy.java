/*
Merge Linked Lists
******************

Given two sorted Linked Lists, merge them and return the resultant Linked List. 
The resulting LinkedList should also be sorted. 

Sample Test Case:
================
Input-
    L1: 1->2->3->5
    L2: 4
Output-
    L: 1->2->3->4->5


DO NOT UNCOMMENT THIS CODE

LinkedList and Node class Definitions

class Node { 
    int data; 
    Node next; 
    Node(int d) { 
        data = d;
        next = null;
    } 
    Node() { 
        data = 0;
        next = null;
    } 
} 

class LinkedList { 
    Node head;
    
    LinkedList(){
        head = null;
    }
}

DO NOT UNCOMMENT THIS CODE
*
*/

class Node { 
    public int data; 
    public Node next; 
    Node(int d) { 
        data = d;
        next = null;
    } 
    Node() { 
        data = 0;
        next = null;
    } 
} 
class LinkedList { 
    Node head;
    
    LinkedList(){
        head = null;
    }
}

public class Q1_easy {
    /*
     * Complete the following method
     * You can create new members or methods as per your need.
     */


    public static LinkedList mergeLists(LinkedList l1, LinkedList l2) {
        LinkedList ans = new LinkedList();
        while(l1 != null || l2 != null){
            if(l1 != null && l2 != null){
                if(l1.data >= l2.data){
                    ans.data = l2.data;
                    ans = ans.next;
                    l2 = l2.next;
                } else{
                    ans.data = l1.data;
                    ans = ans.next;
                    l1 = l1.next;
                }
            }else if(l1 != null){
                ans.data = l1.data;
                l1 = l1.next;
            }else{
                ans.data = l2.data;
                l2 = l2.next;
            }
        } 
        return ans;
    }
}
