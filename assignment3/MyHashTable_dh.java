import java.lang.Math;


// class NotFoundException extends Exception{}

//Double hashing approach
public class MyHashTable_dh<K, T> implements MyHashTable_<K, T>{
	int t;//size of array which is given as argument
	T[] obj_array; //have to create an array
	String[] key_array;
	// T[] arr;

	public MyHashTable_dh(int t, T[] obj_array){
		this.t = t;
		this.obj_array = obj_array;
		this.key_array = new String[t];
		// this.arr = new T[t];
		//TODO create an array here
		// this.obj_array = clazz.cast(Array.newInstance(clazz.getComponentType(), length));
		// this.obj_array = clazz.cast(Array.newInstance(clazz.getComponentType(), t));
		// this.arr = arr;
	}
	//djb2 h1
	public static long djb2(String str, int hashtableSize) {
		long hash = 5381;
		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) + hash) + str.charAt(i);
		}
		return Math.abs(hash) % hashtableSize;
	}
	//sbdm h2
	public static long sdbm(String str, int hashtableSize) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
		}
		return Math.abs(hash) % (hashtableSize - 1) + 1;
	}
	public int insert(K key, T obj){
		int i = 0;
		try{
				String[] sv = key.toString().split(" ");
				String conc_name = sv[0] + sv[1];

				//TODO K type here Key then how to get fn and ln
				int h1 = (int) djb2(conc_name, this.t);
				int h2 = (int) sdbm(conc_name, this.t);
				while(true){
					int h = (h1 + i*h2)%this.t;
						// System.out.println(h+ key.toString());
					if (key_array[h] != null && key_array[h].equals(conc_name)){
						return t;
					}
					if (obj_array[h] == null){
						obj_array[h] = obj;
						key_array[h] = conc_name;
						i++;
						// System.out.println("-----------");
						// System.out.println(i,h);
						break;

					}
					i++;
				}
				return i;
			} catch (Exception e){
				System.out.println("E");
			}
		return i;
	}
	public int update(K key, T obj){
		//while calling this fuction remeber to print E if i == t
		//otherwise print i
		int i = 0;
		//TODO K type here Key then how to get fn and ln
		try{
			String[] sv = key.toString().split(" ");
			String conc_name = sv[0] + sv[1];
			int h1 = (int) djb2(conc_name, this.t);
			int h2 = (int) sdbm(conc_name, this.t);
				while(i <= this.t){
					int h = (h1 + i*h2)%this.t;
					String key_v = conc_name;
					if (key_v.equals(key_array[h])){
						obj_array[h] = obj;
						i++;
						break;
					}
					i++;
				}
				return i;
			} catch (Exception e){
				System.out.println("E");
			}
		return i;
	}
	public int delete(K key){
		//while calling this fuction remeber to print E if i == t
		//otherwise print i
		int i = 0;
		//TODO K type here Key then how to get fn and ln
		try{
			String[] sv = key.toString().split(" ");
			String conc_name = sv[0] + sv[1];
			int h1 = (int) djb2(conc_name, this.t);
			int h2 = (int) sdbm(conc_name, this.t);
				while(i <= this.t){
					int h = (h1 + i*h2)%this.t;
					String key_v = conc_name;
					if (key_v.equals(key_array[h])){
						obj_array[h] = null;
						key_array[h] = null;
						i++;
						break;
					}
					i++;
				}
				return i;
			}catch (Exception e){
				System.out.println("E");
			}
			return i;
	}
	public boolean contains(K key){
		int i = 0;
		//TODO K type here Key then how to get fn and ln
		try{
			String[] sv = key.toString().split(" ");
			String conc_name = sv[0] + sv[1];
			int h1 = (int) djb2(conc_name, this.t);
			int h2 = (int) sdbm(conc_name, this.t);
				while(i <= this.t){
					int h = (h1 + i*h2)%this.t;
					String key_v = conc_name;
					if (key_v.equals(key_array[h])){
						i++;
						return true;
					}
					i++;
				}
				return false;
			} catch (Exception e){
				System.out.println("E");
			}
			return false;
	}
	public T get(K key) throws NotFoundException{
		int i = 0;
		//TODO K type here Key then how to get fn and ln
		String[] sv = key.toString().split(" ");
		String conc_name = sv[0] + sv[1];
		int h1 = (int) djb2(conc_name, this.t);
		int h2 = (int) sdbm(conc_name, this.t);
		T temp = obj_array[h1];
		while(i <= this.t){
			int h = (h1 + i*h2)%this.t;
			String key_v = conc_name;
			if (key_v.equals(key_array[h])){
				i++;
				temp = obj_array[h];
				break;
			}
			i++;
		}
		return temp;
	}
	public String address(K key) throws NotFoundException{
		int i = 0;
		//TODO K type here Key then how to get fn and ln
		String[] sv = key.toString().split(" ");
		String conc_name = sv[0] + sv[1];
		int h1 = (int) djb2(conc_name, this.t);
		int h2 = (int) sdbm(conc_name, this.t);
		int h = h1;
		while(i <= this.t){
			h = (h1 + i*h2)%this.t;
			String key_v = conc_name;
			if (key_v.equals(key_array[h])){
				i++;
				break;
			}
			i++;
		}
		return h+"";
	}

	// public static void main(String[] args) throws NotFoundException{
	// 	Pair p = new Pair("Asif", "Anwar");
	// 	Pair p1 = new Pair("Asifa", "Anwar");
	// 	Pair p2 = new Pair("Asifb", "Anwar");
	// 	Pair p3 = new Pair("Asifc", "Anwar");
	// 	Pair p4 = new Pair("Asifd", "Anwar");
	// 	Pair p5 = new Pair("Asife", "Anwar");
	// 	Pair p6 = new Pair("Asiff", "Anwar");
	// 	Pair p7 = new Pair("Asifj", "Anwar");
	// 	Pair p8 = new Pair("Asifk", "Anwar");
	// 	Pair p9 = new Pair("Asifl", "Anwar");
	// 	Student student = new Student(p, "Karakoram", "TT", "7.0");
	// 	Student student1 = new Student(p1, "Karakoram1", "TT", "7.0");
	// 	Student student2 = new Student(p2, "Karakoram2", "TT", "7.0");
	// 	Student student3 = new Student(p3, "Karakoram3", "TT", "7.0");
	// 	Student student4 = new Student(p4, "Karakoram4", "TT", "7.0");
	// 	Student student5 = new Student(p5, "Karakoram5", "TT", "7.0");
	// 	Student student6 = new Student(p6, "Karakoram6", "TT", "7.0");
	// 	Student student7 = new Student(p7, "Karakoram7", "TT", "7.0");
	// 	Student student8 = new Student(p8, "Karakoram8", "TT", "7.0");
	// 	Student student9 = new Student(p9, "Karakoram9", "TT", "7.0");
	// 	Student[] arr = new Student[10];
	// 	MyHashTable_dh<Pair, Student> dh = new MyHashTable_dh<Pair, Student>(10, arr);
	// 	System.out.println(dh.insert(p, student));
	// 	System.out.println(dh.insert(p1, student1));
	// 	System.out.println(dh.insert(p2, student2));
	// 	System.out.println(dh.insert(p3, student3));
	// 	System.out.println(dh.insert(p4, student4));
	// 	System.out.println(dh.insert(p5, student5));
	// 	System.out.println(dh.insert(p6, student6));
	// 	System.out.println(dh.insert(p7, student7));
	// 	System.out.println(dh.insert(p8, student8));
	// 	System.out.println("----------------");
	// 	System.out.println(dh.insert(p9, student9));

	// 	System.out.println(dh.contains(p));
	// 	System.out.println(dh.contains(p1));
	// 	System.out.println(dh.contains(p2));
	// 	System.out.println(dh.contains(p3));
	// 	System.out.println(dh.contains(p4));
	// 	System.out.println(dh.contains(p5));
	// 	System.out.println(dh.contains(p6));
	// 	System.out.println(dh.contains(p7));
	// 	System.out.println(dh.contains(p8));
	// 	System.out.println(dh.contains(p9));
	// 	Pair n = new Pair("aldf", "sadf");
	// 	System.out.println(dh.contains(n));

	// 	System.out.println(dh.address(p));
	// 	System.out.println(dh.address(p1));
	// 	System.out.println(dh.address(p2));
	// 	System.out.println(dh.address(p3));
	// 	System.out.println(dh.address(p4));
	// 	System.out.println(dh.address(p5));
	// 	System.out.println(dh.address(p6));
	// 	System.out.println(dh.address(p7));
	// 	System.out.println(dh.address(p8));
	// 	System.out.println("---------");
	// 	if (dh.contains(n))
	// 	{System.out.println(dh.address(n));}
	// 	else{System.out.println("E");}
	// 	// if(dh.address(p9)< 10)
	// 	// {System.out.println(dh.address(p9));}
	// 	// else{System.out.println("E");}
	// 	// if(dh.address(n)< 10)
	// 	// {System.out.println(dh.address(n));}
	// 	// else{System.out.println("E");}
	// 	if (dh.contains(p))
	// 	{Student n_dh = dh.get(p);
	// 	System.out.println(n_dh.fname());
	// 	System.out.println(n_dh.lname());
	// 	System.out.println(n_dh.hostel());
	// 	System.out.println(n_dh.cgpa());}
	// 	else{System.out.println("E");}

		// System.out.println(dh.djb2("RamSingh", 11));
		

	// }
	
}