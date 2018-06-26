/*
 * Kai Wang
 */

package dRMmessage; // Delete this line when submit

import java.util.Scanner;

class Main {
	
	public static void main(String args[]) {
		Main d = new Main();
		for(String s : args)
			System.out.println(d.decryption(s));
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine())
			System.out.println(d.decryption(scanner.nextLine()));
	}
	
	/*
	 * Division Process
	 */
	public String[] divide(String input) {
		String[] s = new String[2];
		StringBuilder firstHalf = new StringBuilder();
		StringBuilder secondHalf = new StringBuilder();
		int i = 0;
		while(i < input.length() / 2) {
			firstHalf.append(input.charAt(i));
			i ++;
		}
		while(i < input.length()) {
			secondHalf.append(input.charAt(i));
			i ++;
		}
		
		s[0] = firstHalf.toString();
		s[1] = secondHalf.toString();
		return s;
	}
	
	/*
	 * Rotation Process
	 */
	public String rotate(String input, int rotValue) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < input.length(); i ++) {
			int newChar = input.charAt(i) + (rotValue % 26);
			if(newChar > 90)
				newChar = newChar % 90 + 64;
			s.append((char)newChar);
		}
		return s.toString();
	}
	
	/*
	 * Decryption Process
	 */
	public String decryption(String input) {
		String output = new String();
		String[] s = divide(input);
		int firstHalfRotValue = 0;
		int secondHalfRotValue = 0;
		for(int i = 0; i < input.length() / 2; i ++) {
			firstHalfRotValue += s[0].charAt(i) - 65;
			secondHalfRotValue += s[1].charAt(i) - 65;
		}
		s[0] = rotate(s[0], firstHalfRotValue);
		s[1] = rotate(s[1], secondHalfRotValue);
		for(int i = 0; i < input.length() / 2; i ++) {
			output += rotate(Character.toString(s[0].charAt(i)), s[1].charAt(i) - 65);
		}
		
		return output;
	}
	

}
