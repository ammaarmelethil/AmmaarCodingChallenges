package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class GradeController {

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoiceBox;

    @FXML
    private TextField projectGradeTextfield;

    @FXML
    private Slider quizSlider;

    @FXML
    private ChoiceBox<Integer> requiredCodingChallengesChoicebox;

    @FXML
    private Label courseGradeLabel;
    
    @FXML 
    private Label projectErrorLabel;
    
    @FXML
    void calculateGrade(ActionEvent event) {
    	double courseGrade = 0.0;
    	String projectGrade = projectGradeTextfield.getText();
    	courseGrade = Double.parseDouble(projectGrade)*0.5;
    	System.out.println("Project grade: " + projectGrade + " Course grade so far: " + courseGrade);
    	
    	double quizGrade = quizSlider.getValue();
    	courseGrade += (quizGrade*(100/10))*0.15;
    	System.out.println("Quiz grade: " + quizGrade + " Course grade so far: " + courseGrade);
    	
    	int requiredCodingChallengesPassed = requiredCodingChallengesChoicebox.getValue(); 
    	int optionalCodingChallengesPassed = optionalCodingChallengesChoiceBox.getValue();
    	int codingChallengesPassed = requiredCodingChallengesPassed + optionalCodingChallengesPassed;
    	courseGrade += (codingChallengesPassed*(100/20))*0.35;
    	System.out.println("Coding Challenges Passed: " + codingChallengesPassed + " Course grade so far: " + courseGrade);

    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    }

}