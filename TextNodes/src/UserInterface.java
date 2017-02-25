import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * My idea here is that this where all the code goes to get the string that the
 * game looks at.
 * 
 * @author Max
 *
 */
public class UserInterface extends Application {

	TextField textField = new TextField();
	GridPane grid = new GridPane();

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

		

		primaryStage.setTitle("Hello World!");
		// assuming you have defined a StringProperty called "valueProperty"
		Label myLabel = new Label("Start");
		myLabel.textProperty().bind(inputProperty());
		setInput("this somehow works?");

		// StackPane root = new StackPane();
		// root.getChildren().add(grid);
		
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
	//	grid.gridLinesVisibleProperty().set(true);
		GridPane.setHalignment(myLabel, HPos.CENTER);
		GridPane.setValignment(myLabel, VPos.CENTER);
		
		grid.add(textField, 0,0);
		grid.add(myLabel, 0, 1);
	//	System.out.println(grid.getStyle() + "fuck you gumby"); THIS IS NULL
	//	System.out.println(grid.getStylesheets().get(0)); ALSO NULL
	//	grid.getStylesheets().add("testStyle.css");
	
	//	grid.setStyle("-fx-grid-lines-visible: true");
	//	grid.getStyleClass().add("grid-pane");
		System.out.println(grid.getStyle() + "fuck you gumby"); 
		
		Scene scene = new Scene(grid, 300, 300, Color.BLACK);
	//	System.out.println(scene.getStylesheets().add("testStyle.css"));
		scene.getStylesheets().add("testStyle.css");
		// GridPane.setConstraints(textField, (int) root.getWidth()/20, 0);
		
		//GridPane.setConstraints(myLabel, 0, 20);
		

		primaryStage.setScene(scene);
		primaryStage.show();

		/**
		 * here is where I learne sort of how the propery system works in fx.
		 * I'm now just using the text field.
		 */
		// textField.textProperty().addListener((observable, oldValue, newValue)
		// -> {
		// // System.out.println("textfield changed from " + oldValue + " to "
		// // + newValue);
		// input.set(newValue);
		// });

	}

}
