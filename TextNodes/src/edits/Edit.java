package edits;

public interface Edit {

	public void undo();
	public void redo();
	public String getUndoRedoName();
}
