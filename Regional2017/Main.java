/*
 * Kai Wang
 */
package gameOfThrones;  // Delete this line when submit
import java.util.Scanner;
	
class Main {
			
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int totalChild = sc.nextInt();
			int numInput = sc.nextInt();
			int[] eggPath = new int[numInput + 1];
			int index = 0;
			eggPath[0] = 0;
			for(int i = 0; i < numInput; i ++) {
				if(sc.hasNextInt()) {
					int numThrow = sc.nextInt();
					if(numThrow < 0)
						eggPath[index + 1] = (totalChild + eggPath[index] - ((numThrow * -1) % totalChild)) % totalChild;
					else
						eggPath[index + 1] = (eggPath[index] + numThrow) % totalChild;
					index ++;
				}
				else{
					sc.next();
					index =index - sc.nextInt();
				}
			} 
			System.out.println(eggPath[index]);
		}
		sc.close();
	}
}
