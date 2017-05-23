package commands;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import game.Node;

public class removeQuestionEdit extends AbstractUndoableEdit {

	Node myNode;
	String data;	
	
	boolean question; //true if its a question, false if its an answer

	public removeQuestionEdit(Node target, String data) {
		myNode = target;
		
		this.data = data;
		
	}

	public void undo() throws CannotUndoException {
		
		myNode.questionProperty().setValue(data);
		
	}

	public void redo() throws CannotRedoException {
		myNode.questionProperty().set("");
	}

	public boolean canUndo() {
		return true;
	}

	public boolean canRedo() {
		return true;
	}

}
