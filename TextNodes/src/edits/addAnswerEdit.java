package edits;



import game.Node;

public class addAnswerEdit implements Edit {

	Node myNode;
	String data;	
	int target;
	
	/**
	 * 
	 * @param parent the node to which we are adding a child
	 * @param target the index of the of child 
	 * @param data the child's data
	 */
	public addAnswerEdit(Node parent, int target, String data) {
		myNode = parent;
		this.target = target;
		this.data = data;
		
	}

	public void undo() //throws CannotUndoException 
	{
		
		myNode.choices.remove(target);
		
	}

	//TODO: currently can only add node to end of list
	public void redo()// throws CannotRedoException 
	{
		myNode.choices.add(new Node(data));
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
