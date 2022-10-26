package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class GradeController {
	Stage applicationStage;

	
	@FXML
	private Label averageOptionalQuizGradeLabel;
	
	@FXML
	private Label averageRequiredQuizGradeLabel;
	
    @FXML
    private TextField projectGradeTextfield;

    @FXML
    private ChoiceBox<Integer> requiredQuizzesCompletedChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> optionalQuizzesCompletedChoiceBox;

    @FXML
    private ChoiceBox<Integer> requiredCodingChallengesChoicebox;
    
    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoiceBox;

    @FXML
    private Label courseGradeLabel;
    
    @FXML 
    private Label projectErrorLabel;
    
    /**
     * Convert the value entered to a double value. This method will verify if the value entered is actually
     * a number and if the value is a valid percentage grade i.e equal to or between 0 and 100. If the value entered is 
     * not a valid percentage grade, this method will return 0.0 as the project grade instead.  
     * 
     * @param valueEntered : a String that holds a value entered by the user intended to be a project grade 
     * @return the project value entered by the user if it is a valid percentage grade and 0 otherwise 
     */
    double getProjectGrade(String valueEntered) {
    	// Check that the string entered by the user is a valid decimal number
    	
    	 
    	
    	boolean validProjectGrade = true;
    	int numberOfDecimalPoints = 0;
      
    	
    	for (char c : valueEntered.toCharArray()) {
    		
    		// Check if the character is a floating point value else if it has too many decimal points (.)
    		if (c =='.') {
    			numberOfDecimalPoints += 1;
    			if (numberOfDecimalPoints <= 1) {
    				validProjectGrade = true;
    			} else if (numberOfDecimalPoints > 1) {
    				validProjectGrade = false;
    				projectErrorLabel.setText("You used " + numberOfDecimalPoints + " decimal points in a project grade. Make sure to enter one decimal");
    			} 
    		// Check if the character is a digit 	
    		} else if (!Character.isDigit(c)) {
    			validProjectGrade = false;
    			projectErrorLabel.setText("Do not use " + c + " in a project grade. Make sure to enter a number between 0 and 100"); 
			
			
			}
        			
    	}		
    	
    	
    	// Convert the string entered by the user into a number
    	// Otherwise the project grade will default to zero 
    	
    	double projectGrade = 0;
    	if (validProjectGrade) {
    		projectGrade = Double.parseDouble(valueEntered);
    	}
    	
    	// Check if the number entered by the user is a valid percentage grade
    	// If valid, include the provided number in the overall grade calculation    
    	if (projectGrade < 0 || projectGrade > 100) {
    		projectErrorLabel.setText("Project grade should be between 0% and 100%");
    		projectGrade = 0;    	
    	}
    	
    	return projectGrade;
    	
    }
    
    double averageofRequiredQuizGrades = 0.0;
    // Calculates grade from required quizzes relative to overall grade
    void calculateAverageRequiredQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	// Made sure to reset to zero, in case it contains a  previously computed value
    	averageofRequiredQuizGrades = 0.0;
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
    		averageofRequiredQuizGrades += Double.parseDouble(quizGradeTextfield.getText());
    	}
    	
    	averageofRequiredQuizGrades = averageofRequiredQuizGrades / 150;
    	averageRequiredQuizGradeLabel.setText(String.format("Average required quiz grade: %.2f%%", averageofRequiredQuizGrades*100));

    	
    }
    
    double averageofOptionalQuizGrades = 0.0;
 // Calculates grade from optional quizzes relative to overall grade, uses the 5 highest grades
    void calculateAverageOptionalQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	// Made sure to reset to zero, in case it contains a  previously computed value
    	averageofOptionalQuizGrades = 0.0;
    	
    	// New ArrayList created of type double from type TextField to find 5 highest grades
    	ArrayList<Double> grades = new ArrayList<Double>();
		for (TextField quizGradeTextfield : quizGradeTextfields) {
			grades.add(Double.parseDouble(quizGradeTextfield.getText()));
		}

    	// Removes lowest grades if more than 5 optional quizzes are completed. 
    	if (quizGradeTextfields.size() == 7) {
    		grades.remove(grades.indexOf(Collections.min(grades)));
    		grades.remove(grades.indexOf(Collections.min(grades)));
		} else if (quizGradeTextfields.size() == 6) {
			grades.remove(grades.indexOf(Collections.min(grades)));
		}
    	
    	// Adds grades from Double ArrayList to total
    	for (double grade : grades) {
    		averageofOptionalQuizGrades += grade;
    	
    	}
    	
    	// Finds grade relative to overall grade
    	averageofOptionalQuizGrades = averageofOptionalQuizGrades / 50;
    	averageOptionalQuizGradeLabel.setText(String.format("Average optional quiz grade: %.2f%%", averageofOptionalQuizGrades*100));
    }
   
    
    @FXML
    void getRequiredQuizGrades(ActionEvent enterRequiredQuizGradeEvent) {
    	
    	
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfQuizzes = requiredQuizzesCompletedChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	Label quizType = new Label("Enter each of your required quiz grades out of 10:");
    	quizGradeContainer.getChildren().add(quizType);
    	
    	// Create a list that we'll put all the TextFields with quiz grades
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	while (rowsCreated < numberOfQuizzes) {
    		
    		HBox rowContainer = new HBox();
        	Label quizGradeLabel = new Label("Quiz Grade");
        	TextField quizGradeTextField = new TextField();
        	quizGradeTextfields.add(quizGradeTextField);
        	
        	rowContainer.getChildren().addAll(quizGradeLabel,quizGradeTextField);
        	rowsCreated++;
        	
        	quizGradeContainer.getChildren().add(rowContainer);
    		
    		
    	}
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> calculateAverageRequiredQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(doneButton);
    	
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
    }
    
    
    @FXML
    void getOptionalQuizGrade(ActionEvent enterOptionalQuizevent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfQuizzes = optionalQuizzesCompletedChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	Label quizType = new Label("Enter each of your optional quiz grades out of 10:");
    	quizGradeContainer.getChildren().add(quizType);
    	
    	// Create a list that we'll put all the TextFields with quiz grades
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	while (rowsCreated < numberOfQuizzes) {
    		
    		HBox rowContainer = new HBox();
        	Label quizGradeLabel = new Label("Quiz Grade");
        	TextField quizGradeTextField = new TextField();
        	
        	quizGradeTextfields.add(quizGradeTextField);
        	
        	rowContainer.getChildren().addAll(quizGradeLabel,quizGradeTextField);
        	rowsCreated++;
        	
        	quizGradeContainer.getChildren().add(rowContainer);
    		
    		
    	}
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> calculateAverageOptionalQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(doneButton);
    	
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
    }
    
    @FXML
    void calculateGrade(ActionEvent event) {
    	
    	// Clear all error messages
    	projectErrorLabel.setText("");
    	double courseGrade = 0.0;
    	
    	
    	// Assuming the project is worth 50% towards the course grade
    	String projectValueEntered = projectGradeTextfield.getText();
    	
    	double projectGrade = getProjectGrade(projectValueEntered);    	
    	
    	courseGrade += projectGrade*0.5;
    	
    	
    	System.out.println("Project grade entered: " + projectGrade + " Course grade so far: " + courseGrade);
    	
    	
    	// Number of quizzes passed
    	// assuming that quizzes are worth 25% towards the course grade
    	// assuming that there are 15 required quizzes and 7 optional

    	courseGrade += (averageofRequiredQuizGrades)*18.75;
    	courseGrade += (averageofOptionalQuizGrades)*6.25;
    	
    	
    	// Number of coding challenges passed
    	// assuming that coding challenges are worth 25% towards the course grade
    	// assuming that there are 20 coding challenges
    	int requiredCodingChallengesPassed = requiredCodingChallengesChoicebox.getValue(); 
    	int optionalCodingChallengesPassed = optionalCodingChallengesChoiceBox.getValue();
    	int codingChallengesPassed = requiredCodingChallengesPassed + optionalCodingChallengesPassed;
    	courseGrade += (codingChallengesPassed*(100/20))*0.25;
    	System.out.println("Coding Challenges Passed: " + codingChallengesPassed + " Course grade so far: " + courseGrade);

    	courseGradeLabel.setText(String.format("Your course grade is %.2f%%", courseGrade));
    }

}