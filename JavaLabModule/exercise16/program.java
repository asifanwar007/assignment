import java.util.*;
public class program
{
	public String[] test(String fileNames[])
	{
		/*
		Exercise 16: Java files- You have been given the list of the names
		of the files in a directory.
		You have to select Java files from them.
		A file is a Java file if it’s name ends with ”.java”.
		For e.g. ”File-Names.java” is a Java file, ”FileNames.java.pdf” is not.
		If the input is {”can.java”,”nca.doc”,”and.java”,”dan.txt”,”can.java”,”andjava.pdf”} 
		the expected output is {”can.java”,”and.java”,”can.java”}
		*/
		int no_of_java_files = 0;
		int[] postion_of_java_files = new int[fileNames.length];
		int position = -1;
		for (String ele : fileNames){
			position++;
			int lenght_of_input_file_name = ele.length();
			String check = "";
			for(int i = 0; i < 5; i++){
				check += ele.charAt(lenght_of_input_file_name-5+i);

			}
			// System.out.println(check);
			if(check.equals(".java")){
				postion_of_java_files[no_of_java_files] = position;
				no_of_java_files++;
			}

		}
		// System.out.println(Arrays.toString(postion_of_java_files));
		// System.out.println(no_of_java_files);
		String javaFiles[] = new String[no_of_java_files];
		for(int i = 0; i < no_of_java_files; i++){
			int a = postion_of_java_files[i];
			javaFiles[i] = fileNames[a];
		}
		// System.out.println(Arrays.toString(javaFiles));
		return javaFiles;
	}
}
