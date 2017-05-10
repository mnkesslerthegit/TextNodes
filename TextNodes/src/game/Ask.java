package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.util.StringUtils;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ask extends Application {

	TextField textField = new TextField();
	GridPane grid = new GridPane();

	public static void main(String[] args) {

		launch(args);

	}

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

		StaxWriter configFile = new StaxWriter();
		configFile.setFile("config.xml");
		try {
			configFile.saveConfig();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		 StaXParser read = new StaXParser();
         List<Item> readConfig = read.readConfig("config.xml");
         for (Item item : readConfig) {
                 System.out.println(item);
         }

		primaryStage.setTitle("Hello World!");

		primaryStage.setScene(guiStuff());
		primaryStage.show();

	}

	/**
	 * Creates a label and adds it to the gird. Adds a style sheet to the scene
	 * Adds an event listener to our textField which sends its text into the
	 * label.
	 * 
	 * @return
	 */
	private Scene guiStuff() {

		Label myLabel = new Label("Start");

		setInput("this somehow works?");
		

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		GridPane.setHalignment(myLabel, HPos.CENTER);
		GridPane.setValignment(myLabel, VPos.CENTER);
		grid.add(textField, 0, 0);
		grid.add(myLabel, 0, 1);
		Scene scene = new Scene(grid, 300, 300, Color.BLACK);
		System.out.println(scene.getStylesheets().add("testStyle.css"));

		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			// System.out.println("textfield changed from " + oldValue + " to "
			// + newValue);

			/**
			 * The following line calls a final method from a local variable.
			 * The variable will be garbage collected shortly. Apparently, the
			 * method is lifted from it somehow. Additionally, I can pass local
			 * variables to a as parameters, but I can't change their values.
			 * This is because the add listener never sees those variables, only
			 * their values. Does the same logic apply to the final method?
			 * 
			 */
			myLabel.textProperty().set(newValue);
			// System.out.println(num);
			// wtf(num);

		});

		return scene;

	}

	private static List<String> readFile(String filename) throws Exception {
		String line = null;
		List<String> records = new ArrayList<String>();

		// wrap a BufferedReader around FileReader
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

		// use the readLine method of the BufferedReader to read one line at a
		// time.
		// the readLine method returns null when there is nothing else to read.
		while ((line = bufferedReader.readLine()) != null) {
			records.add(line);
		}

		// close the BufferedReader when we're done
		bufferedReader.close();
		return records;
	}

}
