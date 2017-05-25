package edits;


import game.Node;

public class removeQuestionEdit implements Edit {

	Node myNode;
	String data;	
	
	boolean question; //true if its a question, false if its an answer

	public removeQuestionEdit(Node target, String data) {
		myNode = target;
		
		this.data = data;
		
	}

	


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public String getUndoRedoName() {
		// TODO Auto-generated method stub
		return null;
	}

}
