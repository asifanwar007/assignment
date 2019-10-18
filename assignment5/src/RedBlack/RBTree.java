package RedBlack;

// import Trie.Person;

public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E> {
	public RedBlackNode<T, E> root;

	@Override
	public void insert(T key, E value) {
		RedBlackNode<T, E> node = new RedBlackNode<T, E>(key, value, true);
		RedBlackNode<T, E> temp = root;
		RedBlackNode<T, E> temp_father = root;
		boolean count = true;
		// if(root.pn == null){System.out.println("helo");}
		if (temp == null) {
			root = node;
			root.makeBlack();
			// node.pn = null;
			count = false;
			// System.out.println("ohbj");
		} else {
			while (temp != null) {
				// System.out.println(temp.compareTo(key) + " comparing value " + key +" "+
				// temp.key);
				if (temp.compareTo(key) > 0) {
					temp_father = temp;
					temp = temp.ln;
					// System.out.println(key+ " greater than");
					// System.out.println(temp_father.key+ " greater than");

				} else if (temp.compareTo(key) == 0) {
					temp.value_arr.add(value);
					// System.out.println("helo");
					break;
				} else {
					temp_father = temp;
					// System.out.println(key+ " less than");
					// System.out.println(temp_father.key+ " less than");
					temp = temp.rn;
				}
			}
		}
		if (temp == null && count) {
			// System.out.println(temp_father.isRed);
			// System.out.println(temp_father.key);
			if (temp_father.isRed != true) {
				if (temp_father.compareTo(key) > 0) {
					temp_father.ln = node;
					node.pn = temp_father;
				} else {
					temp_father.rn = node;
					node.pn = temp_father;
				}
				// System.out.println("helo");

			} else {
				if (temp_father.compareTo(key) > 0) {
					temp_father.ln = node;
					node.pn = temp_father;
					remedyDoubleRed(node, key);
				} else {
					// System.out.println(key + " inside 3rd node ");
					temp_father.rn = node;
					node.pn = temp_father;
					remedyDoubleRed(node, key);
				}

			}
		}
	}

	public RedBlackNode<T, E> remedyDoubleRed(RedBlackNode<T, E> node_x, T key) {
		// RedBlackNode<T, E> temp_root_node = root;
		if (node_x != null && node_x.compareTo(root.key) == 0) {
			node_x.makeBlack();
			return null;
		}
		if (node_x.pn != null && node_x.pn.compareTo(root.key) == 0) {
			node_x.pn.makeBlack();
			return null;
		}
		if (node_x == null || node_x.pn == null || node_x.pn.pn == null || (node_x.pn.isRed != true)) {
			return null;
		}
		String cases = "Uncle ";
		// while(node_x.pn != null){
		RedBlackNode<T, E> p = node_x.pn;
		RedBlackNode<T, E> g = p.pn;
		// System.out.println(p.key + "------");
		// System.out.println(node_x.key);
		// System.out.println(p.ln.key);
		// System.out.println(p.rn.key);
		if (g.compareTo(key) > 0) { // L
			RedBlackNode<T, E> u = g.rn;
			if (u != null && u.isRed) {
				cases = cases + "Red";
			} else {
				cases = cases + "Black L";
			}
		} else {
			RedBlackNode<T, E> u = g.ln;
			if (u != null && u.isRed) {
				cases = cases + "Red";
			} else {
				cases = cases + "Black R";
			}
		}
		if (p.compareTo(key) > 0 && !cases.equals("Uncle Red")) {
			cases = cases + "L";
		} else if (!cases.equals("Uncle Red")) {
			cases = cases + "R";
		}
		// System.out.println(cases);
		if (cases.equals("Uncle Red")) {

			return remedyDoubleRed(reconstructNode(g, cases), key);

		} else {
			// System.out.println(g.key);
			// System.out.println(p.key);
			// System.out.println(key);
			if (g.pn != null) {
				RedBlackNode<T, E> temp_grand_p = g.pn;
				g = reconstructNode(g, cases);
				// System.out.println(temp_grand_p != null);
				// System.out.println(temp_grand_p.key);
				if (temp_grand_p != null && temp_grand_p.compareTo(key) > 0) {
					temp_grand_p.ln = g;
				} else if (temp_grand_p != null) {
					temp_grand_p.rn = g;
				}
			}
		}
		return root;
	}

	public RedBlackNode<T, E> reconstructNode(RedBlackNode<T, E> g, String recoloring) {
		RedBlackNode<T, E> p = g;
		// RedBlackNode<T, E> u = g;
		RedBlackNode<T, E> x = g;
		RedBlackNode<T, E> t3 = g;
		switch (recoloring) {
		case "Uncle Red":
			g.makeRed();
			g.rn.makeBlack();
			g.ln.makeBlack();
			break;
		case "Uncle Black LL":
			// System.out.println("LL");
			p = g.ln;
			if (p.rn != null) {
				t3 = p.rn;
				g.ln = t3;
				t3.pn = g;
			}
			p.rn = g;
			// pointer for parent node
			g.pn = p;
			p.pn = null;
			p.makeBlack();
			x.makeRed();
			g.makeRed();
			g = p;
			break;
		case "Uncle Black LR":
			// System.out.println("LR");
			p = g.ln;
			x = p.rn;
			if (x.ln != null) {
				RedBlackNode<T, E> t2 = x.ln;
				p.rn = t2;
				t2.pn = p;
			}
			x.ln = p;
			g.ln = x;
			p.pn = x;
			x.pn = g;
			g.pn = null;
			RedBlackNode<T, E> temp1_g = reconstructNode(g, "Uncle Black LL");
			g = temp1_g;
			break;
		case "Uncle Black RR":
			// System.out.println("RR");
			p = g.rn;
			if (p.ln != null) {
				t3 = p.ln;
				g.rn = t3;
				t3.pn = g;
			}
			p.ln = g;
			// pointer for parent node
			g.pn = p;
			p.pn = null;
			p.makeBlack();
			x.makeRed();
			g.makeRed();
			g = p;
			break;
		case "Uncle Black RL":
			p = g.rn;
			x = p.ln;
			if (x.rn != null) {
				RedBlackNode<T, E> t4 = x.rn;
				p.ln = t4;
				t4.pn = p;
			}
			x.rn = p;
			g.rn = x;
			p.pn = x;
			x.pn = g;
			g.pn = null;
			RedBlackNode<T, E> temp_g = reconstructNode(g, "Uncle Black RR");
			g = temp_g;
			break;

		}
		return g;
	}

	@Override
	public RedBlackNode<T, E> search(T key) {
		RedBlackNode<T, E> temp = root;
		RedBlackNode<T, E> temp_null = root;

		// E value;
		// RedBlackNode<T, E> null_node = new RedBlackNode(key, value, true);
		if (temp == null) {
			temp_null.value_arr = null;
			return temp_null;
		} else {
			while (temp != null) {
				// System.out.println(temp.compareTo(key) + " comparing value");
				// System.out.println(temp.key + " " + key);
				if (temp.compareTo(key) > 0) {
					temp = temp.ln;
				} else if (temp.compareTo(key) == 0) {
					return temp;
				} else {
					temp = temp.rn;
				}
			}
		}
		// System.out.println("ejf");
		temp_null.value_arr = null;
		return temp_null;
	}

	// public static void main(String[] args) {
	// // System.out.println("Bhavesh".compareTo())
	// Person person = new Person("Diljeet Singh", "+91987654321");
	// Person person1 = new Person("Bhavesh Kumar", "+91987654321");
	// Person person2= new Person("Chayan Malhotra", "+91987654321");
	// Person person3 = new Person("Asif Anwar", "+91987654321");
	// Person person4 = new Person("faiz anwar", "+91987654321");
	// Person person5 = new Person("Diljeet Singh", "+91987654321");
	// Person person6 = new Person("system for", "+91987654321");
	// RBTree<String, Person> rbt = new RBTree();
	// rbt.insert(person.getName(), person);
	// rbt.insert(person1.getName(), person1);
	// rbt.insert(person2.getName(), person2);
	// rbt.insert(person3.getName(), person3);
	// rbt.insert(person4.getName(), person4);
	// rbt.insert(person5.getName(), person5);
	// rbt.insert(person6.getName(), person6);

	// RedBlackNode search = rbt.search("Diljeet Singh");
	// if (search.getValues() != null) {
	// for (Object person11 : search.getValues()) {
	// System.out.println(person11);
	// }
	// }

	// }
}