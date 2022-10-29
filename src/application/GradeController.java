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
import java.util.List;

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
    
    
    
    void removeSmallest(ArrayList<TextField> textFieldArrayList) {
    	int smallestNumberKey = 0;
    	for (int i = 0; i < textFieldArrayList.size()-1; i++) {
    		
    		if (Double.parseDouble(textFieldArrayList.get(i).getText()) < Double.parseDouble(textFieldArrayList.get(i+1).getText())) {
    			smallestNumberKey = i;
    		} else if (Double.parseDouble(textFieldArrayList.get(i).getText()) > Double.parseDouble(textFieldArrayList.get(i+1).getText())) {
    			smallestNumberKey = i+1;
    		}
    	}
    	
    	textFieldArrayList.remove(smallestNumberKey);
    }
    
    
    
    
    double averageofRequiredQuizGrades = 0.0;
    Label requiredQuizErrorlabel = new Label();
    
    void calculateAverageRequiredQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	requiredQuizErrorlabel.setText("");
    
    	// Assume that each quiz has equal weight
    	double weightPerQuiz = 1.0/15;
  
    	// Made sure to reset to zero, in case it contains a  previously computed value
    	averageofRequiredQuizGrades = 0.0;
    	boolean errorInQuizGrade = false;
    	
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
    		Grade quizGrade = new Grade(0, 10, weightPerQuiz);
    		String errorMessage = quizGrade.setValue(quizGradeTextfield.getText());
    		if (!errorMessage.equals("")) {
    			errorInQuizGrade = true;
    			requiredQuizErrorlabel.setText(errorMessage);
    		}
    		averageofRequiredQuizGrades += quizGrade.getWeightedPercentageGrade();
    	}
    	
    	if (!errorInQuizGrade) {
    		applicationStage.setScene(mainScene);
    		averageRequiredQuizGradeLabel.setText(String.format("Average required quiz grade: %.2f%%", averageofRequiredQuizGrades));
    	} 
    	
    	
    	

    	
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
    	

    	quizGradeContainer.getChildren().add(requiredQuizErrorlabel);
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
    }
    
    
    double averageofOptionalQuizGrades = 0.0;
    Label optionalQuizErrorlabel = new Label();

    void calculateAverageOptionalQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	 	
    	while (quizGradeTextfields.size() > 5) {
    		removeSmallest(quizGradeTextfields);
    	}
    	
    	
    	optionalQuizErrorlabel.setText("");
        
    	// Assume that each quiz has equal weight
    	double weightPerQuiz = 1.0/5;
  
    	// Made sure to reset to zero, in case it contains a  previously computed value
    	averageofOptionalQuizGrades = 0.0;
    	boolean errorInQuizGrade = false;
    	
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
    		Grade quizGrade = new Grade(0, 10, weightPerQuiz);
    		String errorMessage = quizGrade.setValue(quizGradeTextfield.getText());
    		if (!errorMessage.equals("")) {
    			errorInQuizGrade = true;
    			optionalQuizErrorlabel.setText(errorMessage);
    		}
    		averageofOptionalQuizGrades += quizGrade.getWeightedPercentageGrade();
    	}
    	
    	if (!errorInQuizGrade) {
    		applicationStage.setScene(mainScene);
    		averageOptionalQuizGradeLabel.setText(String.format("Average required quiz grade: %.2f%%", averageofOptionalQuizGrades));
    	} 
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
    	quizGradeContainer.getChildren().add(optionalQuizErrorlabel);
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
    }
    
    @FXML
    void calculateGrade(ActionEvent event) {
    	// Clear all error messages
    	projectErrorLabel.setText("");
    	
    	// Project Grade
    	Grade myProjectGrade = new Grade(0, 100, 0.5);
    	projectErrorLabel.setText(myProjectGrade.setValue(projectGradeTextfield.getText()));

    	Grade optionalQuizGrade = new Grade(averageofOptionalQuizGrades/10.0, 10, 0.0625);
    	Grade requiredQuizGrade = new Grade(averageofRequiredQuizGrades/10.0, 10, 0.1875);
    	Grade optionalCCGrade = new Grade(optionalCodingChallengesChoiceBox.getValue(), 5, 0.0625);
    	Grade requiredCCGrade = new Grade(requiredCodingChallengesChoicebox.getValue(), 15, 0.1875);

    	double courseGrade = myProjectGrade.getWeightedPercentageGrade()
    			+ optionalQuizGrade.getWeightedPercentageGrade()
    			+ requiredQuizGrade.getWeightedPercentageGrade()
    			+ optionalCCGrade.getWeightedPercentageGrade()
    			+ requiredCCGrade.getWeightedPercentageGrade();
    	
    	
    	// Display result of calculation to user in the window
    	courseGradeLabel.setText(String.format("Your course grade is %.2f%%", courseGrade));
    	
    }

}