package command;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import jogo.JogadorSingleton;
import jogo.Jogo;
import jogo.LabirintoSingleton;
import jogo.PartidaSingleton;

public class IniciarPartidaCommand implements Command {

	public void execute(Object[] data) throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException {
		PartidaSingleton.instance();
		JogadorSingleton jogador = JogadorSingleton.instance();
		LabirintoSingleton.instance();
		LabirintoSingleton.criarLabirinto();
		LabirintoSingleton.inserirJogador(jogador);
	}

}
