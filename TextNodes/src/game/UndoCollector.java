package game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import edits.Edit;

/**
 * 
 * 
 * I took most of the code from here: http://torgen-engineering.blogspot.com/2016/05/how-to-bring-undoredo-features-to-your.html
 * @author Max
 *
 */
public class UndoCollector {
	/** The default undo/redo collector. */
	public static final UndoCollector INSTANCE = new UndoCollector();

	/** Contains the undoable objects. */
	private final Deque<Edit> undo;

	/** Contains the redoable objects. */
	private final Deque<Edit> redo;

	/** The maximal number of undo. */
	private int sizeMax;

	private UndoCollector() {
		super();
		undo 	 = new ArrayDeque<>();
		redo 	 = new ArrayDeque<>();
		sizeMax  = 30;
	}

	/**
	 * Adds an undoable object to the collector.
	 * @param undoable The undoable object to add.
	 */
	public void add(final Edit undoable) {
		if(undoable!=null && sizeMax>0) {
			if(undo.size()==sizeMax) {
				undo.removeLast();
			}
			System.out.println("Added an edit");
			undo.push(undoable);
			redo.clear(); /* The redoable objects must be removed. */
		}
	}

	/**
	 * Undoes the last undoable object.
	 */
	public void undo() {
		if(!undo.isEmpty()) {
			final Edit undoable = undo.pop();
			undoable.undo();
			redo.push(undoable);
			System.out.println("tried to undo");
		}
		System.out.println("Undo is empty");
	}

	/**
	 * Redoes the last undoable object.
	 */
	public void redo() {
		if(!redo.isEmpty()) {
			final Edit undoable = redo.pop();
			undoable.redo();
			undo.push(undoable);
		}
	}

	/**
	 * @return The last undoable object name or "".
	 */
	public String getLastUndoMessage() {
		return undo.isEmpty() ? "" : undo.peek().getUndoRedoName();
	}

	/**
	 * @return The last redoable object name or "".
	 */
	public String getLastRedoMessage() {
		return redo.isEmpty() ? "" : redo.peek().getUndoRedoName();
	}

	/**
	 * @return The last undoable object or null.
	 */
	public Edit getLastUndo() {
		return undo.isEmpty() ? null : undo.peek();
	}

	/**
	 * @return The last redoable object or null.
	 */
	public Edit getLastRedo() {
		return redo.isEmpty() ? null : redo.peek();
	}

	/**
	 * @param max The max number of saved undoable objects. Must be great than 0.
	 */
	public void setSizeMax(final int max) {
		if(max>=0) {
			for(int i=0, nb=undo.size()-max; i<nb; i++) {
				undo.removeLast();
			}
			this.sizeMax = max;
		}
	}

}
