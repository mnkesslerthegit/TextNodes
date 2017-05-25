package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edits.addAnswerEdit;
import edits.addQuestionEdit;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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

	/**
	 * undo system elements
	 */
	UndoCollector undoManager_; // history list

	// I don't know how undosupport was supposed to work.
	// UndoableEditSupport undoSupport_; // event support

	public static void main(String[] args) {

		launch(args);

	}

	/**
	 * I'm not sure if these methods should be here. I put them here before I
	 * made the node class: I needed a place to put a string property when I was
	 * experimenting with listeners
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

		Label myLabel = new Label("Start");

		/**
		 * I tried to make it so the string contained in myNode shows up on in
		 * the GUI. It didn't work. It stayed with the original node.
		 */
		// nodeData.textProperty().bind(myNode.dataProperty());

		myNode.setQuestionData("Begin Conversation");

		// setInput("this somehow works?");

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
	//	grid.borderProperty().set(10);
		GridPane.setHalignment(myLabel, HPos.CENTER);
		GridPane.setValignment(myLabel, VPos.CENTER);
		grid.add(questions, 0, 0);
	//	grid.add(myLabel, 0, 1);
		grid.add(questionDisplay, 0, 2);
		grid.add(answerDisplay, 0, 3);
		grid.add(answers, 0, 4);
		// grid.add(new TextField(), 0, 3);
		Scene scene = new Scene(grid, 300, 300, Color.BLACK);
		scene.getStylesheets().add("testStyle.css");

		/**
		 * Add an answer
		 */
		addAnswerAction addAnswer = new addAnswerAction();
		answers.addEventHandler(ActionEvent.ACTION, addAnswer);
		
		/**
		 * add questions
		 */
		addQuestionAction addQuestion = new addQuestionAction();
		questions.addEventHandler(ActionEvent.ACTION,addQuestion);

		undoManager_ = UndoCollector.INSTANCE;
		
		/**
		 * I couln't figure out how undoSupport was supposed to work, 
		 * so I'm just going have my commands register themselves with the undo manager directly. 
		 */
		//undoSupport_ = new UndoableEditSupport();
		//undoSupport_.
		// undoSupport_.addUndoableEditListener(new UndoAdapter());

		
		
		/*
		 * this didn't work, because the accelerator map wasn't getting the
		 * events. I needed to use an event filter instead, and that didn't end
		 * up using the accelerator map. Something's dumb here.
		 * 
		 * scene.getAccelerators().put(new KeyCodeCombination(KeyCode.A,
		 * KeyCombination.CONTROL_ANY), new Runnable() {
		 * 
		 * @Override public void run() { // TODO have this not break command
		 * design pattern undoManager_.undo(); System.out.println(
		 * "At least it works");
		 * 
		 * } });
		 * 
		 */

		/*
		 * // answers.setOnAction((new EventHandler<ActionEvent>() {
		 * // @Override // public void handle(ActionEvent e) { // //
		 * label.setText("Accepted"); // System.out.println("I DID IT"); //
		 * addNode(answers.textProperty().getValue()); // updateDisplay(); // }
		 * // // public String toString() { // return
		 * "I got this to print, to see if I could."; // } // }));
		 * 
		 */

		/*
		 * 
		 * This is to test my understanding of listeners, and can eventually be
		 * developed to show the user the answers they can type in
		 */
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

		/*
		 * The following is pointless, and is me experimenting with event
		 * handlers
		 * 
		 * questions.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent e) { //
		 * label.setText("Accepted"); System.out.println("I DID IT"); }
		 * 
		 * public String toString() { return
		 * "I got this to print, to see if I could."; } });
		 * 
		 */

		/**
		 * This method returns the instance of the anonymous class! Praise the
		 * pointless!
		 */
		//
		// System.out.println(questions.getOnAction());

		/**
		 * Here I handle commands that aren't related to any textfield, but
		 * allow the user to navigate between nodes
		 * 
		 * I changed this from event handler to event filter so that it always
		 * gets notified. Textfields apparently consume key events, but not all
		 * of them. Maybe only key pressed events? I don't know. Cause if they
		 * consumed all the events, it wouldn't have worked at all.
		 */
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				// System.out.println("Thigns are going ok");
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

				KeyCodeCombination undoCombo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
				if (undoCombo.match(event)) {
					System.out.println("yeah, you can undo things maybe");
					undoManager_.undo();
				}

				updateDisplay();

			}

		});
		primaryStage.setScene(scene);
		primaryStage.show();
		// primaryStage.requestFocus();

	}

	/**
	 * These inner classes are the commands. The should be able to attach
	 * themselves to the buttons and stuff. They create edit objects, like
	 * addAnswer edit, which holds a record that the action occured. I think in
	 * javafx, they should be event handlers? At first ,I tried making them
	 * abstract actions, but I didnt' want to use swing, because I heard it was
	 * old or something.
	 */
	private class addAnswerAction implements EventHandler<ActionEvent> {
		// @Override
		// public void actionPerformed(ActionEvent evt) {
		// always add to the end of the JList
		// int NumOfElements = elementModel_.getSize();
		// however, give the the element is ID number
		// Object element = new String("Foo " + _lastElementID);
		//
		//
		// record the effect
		// UndoableEdit edit = new addAnswerEdit(elementModel_,
		// element, NumOfElements );
		// perform the operation
		// elementModel_.addElement( element );
		//
		// notify the listeners
		// undoSupport_.postEdit( edit );
		//
		// increment the ID
		// _lastElementID ++ ;
		// System.out.println("The event is: " + evt);
		// }

		@Override
		public void handle(ActionEvent event) {
			// record the effect
			addAnswerEdit edit = new addAnswerEdit(myNode, myNode.choices.size(), answers.getText());
			System.out.println("addAnswer action was TRIGGERED: " + answers.getText());

			// perform the operation
			myNode.addChild(answers.getText());

			// put the effect in the list with listeners? couldn't figure this
			// out
			// undoSupport_.postEdit(edit);

			// instead I just add the edit right to the undo manager
			undoManager_.add(edit);

		}

	}

	class addQuestionAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			addQuestionEdit edit = new addQuestionEdit(myNode, questions.getText());
			System.out.println("addQuestion action happened: " + questions.getText());

			// perform the operation
			myNode.questionProperty().set(questions.getText());

			undoManager_.add(edit);

		}

	}

	// private class UndoAdapter {
	// // public void undoableEditHappened(UndoableEditEvent evt) {
	// // UndoableEdit edit = evt.getEdit();
	// // undoManager_.addEdit(edit);
	// // // refreshUndoRedo();
	// // }
	//
	// }

	private void addNode(String message) {
		myNode.addChild(message);
		System.out.println("Added node. Current number of children: " + myNode.choices.size());

	}

	public void updateDisplay() {
		String answers = "";
		for (Node n : myNode.choices) {
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
		} else {
			System.out.println("Could not find parent");
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
