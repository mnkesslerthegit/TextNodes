package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 * Represents one piece of dialogue, and its correspoinding responses
 * Each node holds a question, and the answer that was given to get there. 
 * @author Max
 *
 */
public class Node implements Serializable {

	// public static boolean running = true;
	Node parent;

	private StringProperty question = new SimpleStringProperty();
	private StringProperty answer = new SimpleStringProperty();

	public StringProperty questionProperty() {

		return question;
	}

	public String getQuestionData() {
		return question.getValue();
	}

	public void setQuestionData(String value) {
		question.set(value);
	}

	public StringProperty answerProperty() {

		return answer;
	}

	public String getAnswerData() {
		return answer.getValue();
	}

	public void setAnswerData(String value) {
		answer.set(value);
	}

	public ArrayList<Node> choices = new ArrayList<Node>();

	/**
	 * Adds a child to the list of choices
	 * 
	 * @param message
	 */
	public void addChild(String message) {
		Node child = new Node();
		child.setAnswerData(message);
		child.parent = this;
		choices.add(child);

	}

}
