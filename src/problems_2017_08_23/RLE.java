/*
Run Length Encoding
Given a string, Your task is to  complete the function encode that returns the run length encoded string for the given string.
eg if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6″.
You are required to complete the function encode that takes only one argument the string which is to be encoded and returns the encoded string.


Input (To be used only for expected output):
The first line contains T denoting no of test cases . Then T test cases follow . Each test case contains a string str which is to be encoded .

Output:
For each test case output in a single line the so encoded string .

Constraints:
1<=T<=100
1<=length of str<=100

Example:
Input
2
aaaabbbccc
abbbcdddd

Output
a4b3c3
a1b3c1d4

Algorithm:
a) Pick the first character from source string.
b) Append the picked character to the destination string.
c) Count the number of subsequent occurrences of the picked character and append the count to destination string.
d) Pick the next character and repeat steps b) c) and d) if end of string is NOT reached.
*/


class GfG
 {
	String encode(String str)
	{
          //Your code here
        StringBuilder sb=new StringBuilder();
        int count=1;
        for(int i=0;i<str.length();i++){
            if(i<str.length()-1 && str.charAt(i)==str.charAt(i+1))
                count++;
            else{
                sb.append(str.charAt(i));
                sb.append(count);
                count=1;
            }    
        }
        return sb.toString();
	}
	
 }