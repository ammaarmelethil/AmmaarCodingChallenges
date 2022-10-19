package basicjava;

import java.util.ArrayList;

public class CCArrayList {

	public static int indexOfIgnoreCase(ArrayList<String> strs, String toFind) {
		
		int indexOfStringtoFind = -1;
		
		for (int i = 0; i < strs.size(); i++) {
			if (toFind.equalsIgnoreCase(strs.get(i))) {
				indexOfStringtoFind = i;
				i = strs.size();
				
			} 
		}
		
		return indexOfStringtoFind;
		
	}

	public static void insert(ArrayList<Double> nums, double numToInsert, int insertAtIndex) {
		if (nums.size() == 0) {
			;
		} else {
			nums.add(insertAtIndex, numToInsert);
		}
		
	}
	

}
