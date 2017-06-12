package edits;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import game.Node;

public class addQuestionEdit implements Edit {

	Node myNode;
	String newData;	
	String oldData;
	
	
	public addQuestionEdit(Node target, String data) {
		myNode = target;
		oldData = myNode.getValue();
		newData = data;
		
	}

	public void undo() throws CannotUndoException {
		
		//myNode.questionProperty().setValue("");
		myNode.setValue(oldData);
		
	}

	public void redo() throws CannotRedoException {
		myNode.setValue(newData);
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
