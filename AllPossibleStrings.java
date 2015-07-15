import java.util.Scanner;

public class AllPossibleStrings {
	Character[][] arr = new Character[10][3];
	String num;
	
	AllPossibleStrings(String num){
		this.num = num;
		arr[0][0] = 'a'; arr[0][1] = 'b'; arr[0][2] = 'c';
		arr[1][0] = 'd'; arr[1][1] = 'e'; arr[1][2] = 'f';
		arr[2][0] = 'g'; arr[2][1] = 'h'; arr[2][2] = 'i';
		arr[3][0] = 'j'; arr[3][1] = 'k'; arr[3][2] = 'l';
		arr[4][0] = 'm'; arr[4][1] = 'n'; arr[4][2] = 'o';
		arr[5][0] = 'p'; arr[5][1] = 'q'; arr[5][2] = 'r';
		arr[6][0] = 's'; arr[6][1] = 't'; arr[6][2] = 'u';
		arr[7][0] = 'v'; arr[7][1] = 'w'; arr[7][2] = 'x';
		arr[8][0] = 'y'; arr[8][1] = 'z'; arr[8][2] = '~';
		arr[9][0] = '!'; arr[9][1] = '@'; arr[9][2] = '#';
	}
	
	void getStrings(int curr, StringBuffer charList){
		int digit = Character.getNumericValue(num.charAt(curr));
		// for each possible character that digit brings
		for (int i=0; i<3; i++){
			StringBuffer temp = new StringBuffer(charList);
			temp.append(arr[digit][i]);			// add character
			
			// if base case, print
			if (curr == num.length() - 1){
				System.out.println(temp.toString());
			}
			// recurse down
			else getStrings(curr + 1, temp);
		}
	}
	
	int getStringNum(int curr){
		if (curr == num.length() - 1) return 3;
		int ans = 0;
		for(int i=0; i<3; i++){
			ans += getStringNum(curr + 1);
		}
		return ans;
	}
	
	void run(){
		getStrings(0, new StringBuffer());
		System.out.println(getStringNum(0));
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new AllPossibleStrings(sc.next())).run();
		sc.close();

	}
}