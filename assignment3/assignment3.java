import java.io.*;
public class assignment3{
	private static void Query(int t, String hashtable_type, String inputFile){
		try {
			File file = new File(inputFile);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String st;
				String[] store_value;
				Student[] obj_arr = new Student[t];

				if (hashtable_type.equals("DH")){
					MyHashTable_dh<Pair, Student> dh = new MyHashTable_dh(t, obj_arr);
					while ((st = br.readLine()) != null){
						// System.out.println(st);
						store_value = st.split(" ");
						String q = store_value[0];
						Pair<String, String> name = new Pair(store_value[1], store_value[2]);
						// if (q.equals("insert") || q.equals("update")){
						// }
						if (q.equals("insert")){
							Student obj = new Student(name, store_value[3], store_value[4], store_value[5]);
							if(dh.contains(name)){System.out.println("E");}
							// int n = dh.insert(name, obj);
							// if(n >= t){System.out.println("E");}
							else{int n = dh.insert(name, obj); System.out.println(n);}
						} else if(q.equals("update")){
							Student obj = new Student(name, store_value[3], store_value[4], store_value[5]);
							if(dh.contains(name)){
								int n = dh.update(name, obj);
								System.out.println(n);
							}
							// if (n >= t && (dh.contains(name) != true)){
							 else {System.out.println("E");}
						} else if (q.equals("delete")){
							if(dh.contains(name)){
								int n = dh.delete(name);
								System.out.println(n);
							}
							// int n = dh.delete(name);
							// if (n>=t){System.out.println("E");}
							else{System.out.println("E");}
						} else if(q.equals("contains")){
							if(dh.contains(name)){
								System.out.println("T");
							} else {System.out.println("F");}
						} else if (q.equals("get")){
							if(dh.contains(name))
							{Student temp = dh.get(name);
								System.out.println(temp.fname() + " " + temp.lname() + " " + temp.hostel() + " " + temp.department() + " " + temp.cgpa());

							}
							else{System.out.println("E");}
						} else{
							if (dh.contains(name))
							{System.out.println(dh.address(name));}
							else{System.out.println("E");}
						}
					}		
				} else{
					MyHashTable_sc<Pair<String, String>, Student> dh = new MyHashTable_sc(t);
					while ((st = br.readLine()) != null){
						// System.out.println(st);
						store_value = st.split(" ");
						String q = store_value[0];
						Pair<String, String> name = new Pair(store_value[1], store_value[2]);
						// Pair name = new Pair(store_value[1], store_value[2]);
						// if (q.equals("insert") || q.equals("update")){
						// }
						if (q.equals("insert")){
							Student obj = new Student(name, store_value[3], store_value[4], store_value[5]);
							if(dh.contains(name)){System.out.println("E");}
							else{
							int n = dh.insert(name, obj);
							// if(n == -1){}
							// System.out.println("------");
							System.out.println(n);}
							// System.out.println("----");
						} else if(q.equals("update")){
							Student obj = new Student(name, store_value[3], store_value[4], store_value[5]);
							
							// System.out.println("------");
							// System.out.println(n);
							// System.out.println("----");
							if (dh.contains(name) != true){
								System.out.println("E");
							} else {int n = dh.update(name, obj); System.out.println(n);}
						} else if (q.equals("delete")){
							
							if(dh.contains(name) ) {int n = dh.delete(name); System.out.println(n);}
							else{System.out.println("E");}
						} else if(q.equals("contains")){
							if(dh.contains(name)){
								System.out.println("T");
							} else {System.out.println("F");}
						} else if (q.equals("get")){
							if(dh.contains(name))
							{Student temp = dh.get(name);
								System.out.println(temp.fname() + " " + temp.lname() + " " + temp.hostel() + " " + temp.department() + " " + temp.cgpa());

							}
							else{System.out.println("E");}
						// } else if(q.equals("checkf")){
						// 	System.out.println("asf");
						// 	Node<Pair_sc, Student> n = dh.checkf(name);
						// 	System.out.println(n.v.fname());
						}
						else{
							if (dh.contains(name))
							{System.out.println(dh.address(name));}
							else{System.out.println("E");}
						}
					}
				}
			} catch(Exception e){
				System.out.println(e);}
	}
	public static void main(String[] arg) throws NotFoundException{
		// String t = arg[0];
		int t = Integer.parseInt(arg[0]);
		String hashtable_type = arg[1];
		String query = arg[2];
		assignment3 assign = new assignment3();
		assign.Query(t, hashtable_type, query);
	}
}