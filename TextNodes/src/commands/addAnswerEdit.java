package commands;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import game.Node;

public class addAnswerEdit extends AbstractUndoableEdit {

	Node myNode;
	String data;	
	int target;
	
	
	public addAnswerEdit(Node parent, int target, String data) {
		myNode = parent;
		this.target = target;
		this.data = data;
		
	}

	public void undo() throws CannotUndoException {
		
		myNode.choices.remove(target);
		
	}

	public void redo() throws CannotRedoException {
		myNode.choices.add(new Node(data));
	}

	public boolean canUndo() {
		return true;
	}

	public boolean canRedo() {
		return true;
	}

}
