package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Current Goal: traverse nodes
 * 
 * @author Max
 *
 */
public class Ask extends Application {

	TextField questions = new TextField();
	TextField answers = new TextField();
	GridPane grid = new GridPane();
	Label answerDisplay = new Label();
	Label questionDisplay = new Label();
	private static Node myNode = new Node();
	private static int childNumber = 0;

	public static void main(String[] args) {

		launch(args);

	}

	/**
	 * I'm not sure if these methods should be here.
	 * 
	 * @param value
	 */
	private void setInput(String value) {
		inputProperty().set(value);
	}

	public String getInput() {
		return inputProperty().get();
	}

	public StringProperty inputProperty() {

		return questions.textProperty();
	}

	@Override
	public void start(Stage primaryStage) {

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
		
		/**
		 * I tried to make it so the string contained in myNode shows up on in
		 * the GUI. It didn't work. It stayed with the original node. 
		 */
	//	nodeData.textProperty().bind(myNode.dataProperty());

		myNode.setQuestionData("Begin Conversation");

		setInput("this somehow works?");

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		GridPane.setHalignment(myLabel, HPos.CENTER);
		GridPane.setValignment(myLabel, VPos.CENTER);
		grid.add(questions, 0, 0);
		grid.add(myLabel, 0, 1);		
		grid.add(questionDisplay, 0, 2);
		grid.add(answerDisplay, 0, 3);
		grid.add(answers, 0, 4);
		// grid.add(new TextField(), 0, 3);
		Scene scene = new Scene(grid, 300, 300, Color.BLACK);
		System.out.println(scene.getStylesheets().add("testStyle.css"));

		questions.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("IMA HANDEL IT");
				updateDisplay();
				switch (event.getCode()) {
				case UP:
					up();
					break;
				case DOWN:
					down();
					break;
				// case RIGHT:
				// right();
				// break;
				// case LEFT:
				// left();
				// break;
				default:
					break;

				}
			}

		});

		answers.setOnAction((new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// label.setText("Accepted");
				System.out.println("I DID IT");
				addNode(answers.textProperty().getValue());
				updateDisplay();
			}

			public String toString() {
				return "I got this to print, to see if I could.";
			}
		}));

		questions.textProperty().addListener((observable, oldValue, newValue) -> {
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
			 * Also, the listener we are adding here is an instance of an
			 * anonymous class. It's generated using a lambda expression
			 * 
			 */
			myLabel.textProperty().set(newValue);
			updateDisplay();
			// System.out.println(num);
			// wtf(num);

		});

		/**
		 * The following is pointless, and is me experimenting with event
		 * handlers
		 */
		// questions.setOnAction(new EventHandler<ActionEvent>() {
		// @Override
		// public void handle(ActionEvent e) {
		// // label.setText("Accepted");
		// System.out.println("I DID IT");
		// }
		//
		// public String toString() {
		// return "I got this to print, to see if I could.";
		// }
		// });

		/**
		 * This method returns the instance of the anonymous class! Praise the
		 * pointless!
		 */
		//
		// System.out.println(questions.getOnAction());

		return scene;

	}

	private void addNode(String message) {
		myNode.addChild(message);
		System.out.println("Added node. Current number of children: " + myNode.choices.size());		

	}
	
	public void updateDisplay(){		
		String answers = "";
		for(Node n : myNode.choices){
			answers += n.getAnswerData();
			answers += "     ";
		}
		answerDisplay.setText(answers);
		questionDisplay.setText(myNode.getQuestionData());
	}

	private void left() {
		if (childNumber != 0) {
			childNumber--;
		}

	}

	private void right() {
		childNumber++;

	}

	/**
	 * Move into a child node. childNumber is set to 0.
	 */
	public void down() {
		if (childNumber < myNode.choices.size()) {
			myNode = myNode.choices.get(childNumber);
			System.out.println("Set node to child");
		
		} else {
			System.out.println("Could not find child");
		}
		childNumber = 0;

	}	

	public void up() {
		if (myNode.parent != null) {
			myNode = myNode.parent;
			System.out.println("set node to parent");
		}

		if (myNode.choices.size() > childNumber) {
			childNumber = myNode.choices.size() - 1;
		}
	}

	/**
	 * currently loads a single node containing dialogue from a text file
	 */
	public static final void load() {

		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream("f.txt"));
			Node s = (Node) in.readObject();
			// System.out.println(s.chocies.get(0).num);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * save the current node
	 */
	public static final void save() {
		Node s1 = new Node();
		Node other = new Node();
		// other.num = 8;
		// other.next = s1;
		s1.choices.add(other);
		FileOutputStream fout;
		try {
			fout = new FileOutputStream("f.txt");

			// TODO Auto-generated catch block

			ObjectOutputStream out;

			out = new ObjectOutputStream(fout);

			out.writeObject(s1);
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
