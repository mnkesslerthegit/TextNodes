package game;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxWriter {
	private String configFile;

	public void setFile(String configFile) {
		this.configFile = configFile;
	//	System.out.println("THIS IS THE THING: " + configFile);
	}

	/**
	 * I changed this so that the start and end elements are named after the configFile
	 * @throws Exception
	 */
	public void saveConfig() throws Exception {
		// Create a XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		// Create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory
				.createXMLEventWriter(new FileOutputStream(configFile));
		// Create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		// Create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		String openAndEndTagName = configFile.substring(0, configFile.length()- 4);
		
		// Create config open tag
		StartElement configStartElement = eventFactory.createStartElement("",
				"", openAndEndTagName);
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		eventWriter.add(eventFactory.createStartElement("", "", "item"));
		eventWriter.add(eventFactory.createAttribute("date", "January 2009"));
		
		// Write the different nodes
		createNode(eventWriter, "mode", "13");
		createNode(eventWriter, "unit", "901");
		createNode(eventWriter, "current", "0");
		createNode(eventWriter, "interactive", "0");
	//	System.out.println(openAndEndTagName);
		eventWriter.add(eventFactory.createEndElement("", "",  openAndEndTagName));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, String name,
			String value) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// Create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// Create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// Create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);

	}

}