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
		
		return 0;
	}

}
