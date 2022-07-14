package command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public interface Command {

	public void execute(Object[] obj) throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException;

}
