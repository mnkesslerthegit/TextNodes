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
 * 
 * I tried to modify tree view to expand the way I wanted it to, but I failed. 
 * Now I'm trying to design my own tree view class that still uses tree items
 * 
 * @author Max
 *
 */
public class TextNode extends TreeItem<String> implements Serializable {
	

public TextNode(String data){
	this.setValue(data);
	
}


	

}
