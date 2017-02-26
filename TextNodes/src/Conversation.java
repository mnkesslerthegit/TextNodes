import javafx.beans.property.StringProperty;

public class Conversation {
	StringProperty myInput;

	public Conversation(StringProperty input) {
		myInput = input;
		myInput.addListener((observable, oldValue, newValue) -> {
			 System.out.println("textfield changed from " + oldValue + " to "
			 + newValue);
			input.set(newValue);
		});

	}

}
