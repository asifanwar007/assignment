#make
compile:
	javac *.java
#make run
run:
	java assignment3 5 DH input.txt
	java assignment3 5 SCBST input1.txt
#make clean
clean:
	rm *.class
#make handin
handin:	
# 	@mkdir src
	@cp assignment3.java src/
	@cp BST.java src/
	@cp MyHashTable_dh.java src/
	@cp MyHashTable_sc.java src/	
	@zip -r 2017TT10922_assignment3.zip src/
	@base64 2017TT10922_assignment3.zip > 2017TT10922_assignment3.zip.b64
	#@rm $(ENTRY)_assignment2.zip
