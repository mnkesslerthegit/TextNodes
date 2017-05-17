package commands;

public interface Command {

	 public abstract void execute();

	    /**
	     * This is called to undo last command.
	     */
	 public abstract void undo();
	
}
