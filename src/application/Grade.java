package application;

public class Grade {
	double value;
	double maxValue;
	double weight;
	
	double getWeightedPercentageGrade() {
		return value * 100 * weight / maxValue;
	}
	
	Grade(double gradeValue, double maxPossibleValue, double weightTowardsCourseGrade) {
		value = gradeValue;
		maxValue = maxPossibleValue;
		weight = weightTowardsCourseGrade;
	}
	
	
	/**
     * This method will verify if the value entered is actually a valid grade value
     * If the value entered is not a valid grade value, this method will a specific error message and value of the grade will be set to 0.0 instead.  
     * 
     * @param valueAsString : a String that holds a value entered by the user intended to be a grade 
     * @return error message is "" (an empty string) if there are no errors found, otherwise a specific error message will be returned.
     */

	String setValue(String valueAsString) {
    	String errorMessage = "";
		// Check that the string entered by the user is a valid decimal number
    	boolean validProjectGrade = true;
    	int numberOfDecimalPoints = 0;
      
    	
    	for (char c : valueAsString.toCharArray()) {
    		
    		// Check if the character is a floating point value else if it has too many decimal points (.)
    		if (c =='.') {
    			numberOfDecimalPoints += 1;
    			if (numberOfDecimalPoints <= 1) {
    				validProjectGrade = true;
    			} else if (numberOfDecimalPoints > 1) {
    				validProjectGrade = false;
    				errorMessage = ("You used " + numberOfDecimalPoints + " decimal points in a grade. Make sure to enter one decimal. ");
    			} 
    		// Check if the character is a digit 	
    		} else if (!Character.isDigit(c)) {
    			validProjectGrade = false;
    			errorMessage = ("Do not use " + c + " in a grade. Make sure to enter a number between 0 and " + maxValue + "."); 
			
			
			}
        			
    	}		
    	
    	
    	// Convert the string entered by the user into a number
    	// Otherwise the project grade will default to zero 
    	
    	if (validProjectGrade) {
    		value = Double.parseDouble(valueAsString);
    	}
    	
    	// Check if the number entered by the user is a valid percentage grade
    	// If valid, include the provided number in the overall grade calculation    
    	if (value < 0 || value > maxValue) {
    		errorMessage = String.format("Grade should be between 0% and %d%%. ", maxValue);
    		value = 0;    	
    	}
    	
    	return errorMessage;
    	
    }
}
