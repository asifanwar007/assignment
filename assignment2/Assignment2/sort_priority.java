public class sort_priority<V>{
	NodeBase<V>[] q;
	NodeBase<V>[] q1;
	public sort_priority(NodeBase<V>[] q){
		this.q = q;
		this.q1 = new NodeBase[q.length];
	}
	public NodeBase<V>[] getSort(){
		for (NodeBase<V> c: q){
			if(c.getPriority() < q.length){
				q1[c.getPriority()] = c;

			} else{
				System.out.println("Error in sort_priority.java");
			}

		}
		return q1;
	}
}