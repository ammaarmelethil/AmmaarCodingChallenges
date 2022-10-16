package basicjava;


public class CCStringsIfAndWhile {

	public static boolean isDigit(char c) {
		if (Character.isDigit(c)) {
			return true;
		} else {
			return false;
		}
	}

	public static int count(String str, String chars) {
		
		int instances = 0;
		
		// converts arguments to lower case to remove ambiguity
		str = str.toLowerCase();
		chars = chars.toLowerCase();
		
		// iterates through each argument to count matched pairs
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < chars.length(); j++) {
				if (str.charAt(i) == chars.charAt(j)) {
					instances++;
				}
			}
		}
		
		
		
		return instances;
	}

	public static int smallestDigit(int i) {
		
		// initializes smallest number to 10 so condition in while loop can hold true for digits
		int smallestNumber = 10;
		int lastDigit = 0;
		
		
		// checks and/or corrects argument if i<0 or i == 0
		if (i < 0) {
			i *= -1;
		} else if (i==0) {
			smallestNumber = i;
		}
		
		// iterates through each digit and resets smallestNumber if condition holds true
		while (i > 0) {
			lastDigit = i % 10;
			
			if (lastDigit < smallestNumber) {
				smallestNumber = lastDigit;
			}
			
			i /= 10;
		}
		
		return smallestNumber;
	}

}
