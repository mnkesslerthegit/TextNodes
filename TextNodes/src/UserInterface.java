import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * My idea here is that this where all the code goes to get the string that the game looks at. 
 * @author Max
 *
 */
public class UserInterface extends Application{


	TextField textField = new TextField();
    
	private void setInput(String value) {
		inputProperty().set(value);
	}

	public String getInput() {
		return inputProperty().get();
	}

	public StringProperty inputProperty() {
		
		return textField.textProperty();
	}

	@Override
	public void start(Stage primaryStage) {
		
	
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		primaryStage.setTitle("Hello World!");
		// assuming you have defined a StringProperty called "valueProperty"
		Label myLabel = new Label("Start");

		myLabel.textProperty().bind(inputProperty());
		setInput("this somehow works?");
		GridPane.setConstraints(textField, 0, 0);
		grid.getChildren().add(textField);
		GridPane.setConstraints(myLabel, 0, 20);
		grid.getChildren().add(myLabel);

		StackPane root = new StackPane();
		root.getChildren().add(grid);
		Scene scene = new Scene(root, 300, 300, Color.BLACK);
		
		
/**
 * here is where I learne sort of how the propery system works in fx. I'm now just using the text field. 
 */
//		textField.textProperty().addListener((observable, oldValue, newValue) -> {
//			// System.out.println("textfield changed from " + oldValue + " to "
//			// + newValue);
//			input.set(newValue);
//		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
		
	
}
