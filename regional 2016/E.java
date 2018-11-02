import java.util.LinkedList;
import java.util.Scanner;

public class E{

	public static void main(String[] args) {
			Scanner s = new Scanner(System.in);
			while(s.hasNextLine()) {
				String str = s.nextLine();
				int min = str.length();
				LinkedList<String> list = new LinkedList<String>();
				for(int i = 0; i < str.length(); i ++) {
					for(int j = i + 2; j <= str.length(); j++) {
						list.add(str.substring(i, j));	
					}
				}
				for(String sub : list) {
					min = Integer.min(min, count(sub, str));
				}
				System.out.println(min);
			}
		}
	
	
	static int count(String sub, String str) {
		int num = 0;
		int i = 0;
		while(i <= str.length() - sub.length()) {
			if(isMatched(str.substring(i, i + sub.length()), sub)) {
				num ++;
				i += sub.length();
			}
			else
				i++;
		}
		return str.length() + sub.length() - (sub.length() - 1)*num;
	}
	
	static boolean isMatched(String a, String b) {
		for(int i = 0; i < a.length(); i ++) {
			if(a.charAt(i) != b.charAt(i)) {
				return false;
			}
		}
		return true;
	}

}
