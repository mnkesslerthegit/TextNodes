package edits;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import game.Node;

public class removeAnswerEdit implements Edit {

	Node myNode;
	String data;	
	int target;
	
	
	public removeAnswerEdit(Node parent, int target, String data) {
		myNode = parent;
		this.target = target;
		this.data = data;
		
	}

	public void undo() throws CannotUndoException {
		
		myNode.getChildren().add(new Node(data));
		
	}

	public void redo() throws CannotRedoException {
		
		myNode.getChildren().remove(target);
	}

	public boolean canUndo() {
		return true;
	}

	public boolean canRedo() {
		return true;
	}

	@Override
	public String getUndoRedoName() {
		// TODO Auto-generated method stub
		return null;
	}

}
