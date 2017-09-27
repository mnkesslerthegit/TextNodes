package edits;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import game.TextNode;

public class removeAnswerEdit implements Edit {

	TextNode myNode;
	String data;	
	int target;
	
	
	public removeAnswerEdit(TextNode parent, int target, String data) {
		myNode = parent;
		this.target = target;
		this.data = data;
		
	}

	public void undo() throws CannotUndoException {
		
		myNode.getChildren().add(new TextNode(data));
		
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
