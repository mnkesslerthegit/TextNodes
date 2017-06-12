package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;

/**
 * Represents one piece of dialogue, and its correspoinding responses
 * Each node holds a question, and the answer that was given to get there. 
 * @author Max
 *
 */
public class Node extends TreeItem<String> implements Serializable {
	

public Node(String data){
	this.setValue(data);
	
}

public void setExpanded(){
	
}
	

}
