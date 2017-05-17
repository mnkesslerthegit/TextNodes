package commands;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import game.Node;

public class addQuestionEdit extends AbstractUndoableEdit {

	Node myNode;
	String data;	
	
	
	public addQuestionEdit(Node target, String data) {
		myNode = target;
		
		this.data = data;
		
	}

	public void undo() throws CannotUndoException {
		
		myNode.questionProperty().setValue("");
		
	}

	public void redo() throws CannotRedoException {
		myNode.questionProperty().set(data);
	}

	public boolean canUndo() {
		return true;
	}

	public boolean canRedo() {
		return true;
	}

}
