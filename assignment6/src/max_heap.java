boolean delete(T v) {
   /* 26 marks TODO: MODIFY here.  Write method delete
    *    "<=" elements are on the left of a node and ">" on the right
    *    Remove the top-most node with the value v, if it exists
    */
    int no_d = this.find(v);
    if(no_d ==0){
        return false;
    }
    BSTNode<T> temp = root();
    BSTNode<T> tmep_p = root();
    
    while(temp != null){
        // System.out.println(temp.value());
        
        if(temp.value().compareTo(v) > 0){
            temp_p = temp;
            temp= temp.left();
        }else if((temp.value().compareTo(v)) < 0){
            temp_p = temp;
            temp = temp.right();
        }else if(temp.value().compareTo(v) ==0){
            //here deleting
            if(temp.left() == null && temp.right() ==null){
                temp = null;
            }else if(temp.left() == null && temp.right() != null){
                if(temp_p.value().compareTo(v) > 0){
                    temp_p.setleft(temp.right());
                    temp = temp.right();
                }else if((temp_p.value().compareTo(v)) =< 0){
                    temp_p.setright(temp.right());
                    temp = temp.right();
            //till here
                }
        
            } else if(temp.right == null && temp.left() != null){
                if(temp_p.value().compareTo(v) > 0){
                    temp_p.setleft(temp.left());
                    temp = temp.right;
                }else
                    if((temp_p.value().compareTo(v)) =< 0){
                    temp_p.settight(temp.left());
                    temp = temp.right();
            //till here
                temp= temp.left();
                
            } else{
                BSTNode<T> node_t = minValue(temp.left());
                delete(node_t);
                node_t.setleft(temp.left());
                node_t.setright(temp.right());
                temp = node_t;
                
            }
        }
    }
    
    
  }
  return true
  }
  public BSTNode<T> minValue(BSTNode<T> t){
      BSTNode<T> ans = t;
      while(t.left() != null){
          ans = t.left();
          t = t.left();
      }
      return t;
  }