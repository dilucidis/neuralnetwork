# README
How to make use Generator
-------------------------
1) Place all input cases as 18-digit binary numbers on single lines in "in.txt" on the next empty line.
2) Run DataProcessor.java
3) CTRL+X the contents of "in.txt" (except "in") to the end of "inDB.txt"
4) CTRL+X the contents of "out.txt" (except "out") to the end of "outDB.txt"

How to convert from text to IO
------------------------------
1) IOConverter converter = new IOConverter("inDB.txt","outDB.txt");
2) converter.get() -> returns IO[]
