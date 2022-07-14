package jogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.io.FilenameUtils;
import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Programa {
	public static String tipoArquivo = null;

	static Instant t_inicio;

	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException,
			ParserConfigurationException, SAXException, TransformerException {
		
		String endereco = "C:\\Users\\mende\\OneDrive\\Área de Trabalho\\Jogo-main\\jogo\\src\\jogo\\mapa.xml";
		String extensaoDoArquivo = FilenameUtils.getExtension(endereco);
		
		Document doc = null;
		long tempo = 0;
		if (extensaoDoArquivo.compareTo("xml") == 0) 
		{
			tipoArquivo = "xml";
			doc = leXml(endereco);
			tempo = retornaTempoX(doc);
			
		} else 
		{
			tipoArquivo = "json";
			JSONObject docJ = null;
			docJ = leJSON(endereco);
			tempo  = retornaTempoJ(docJ);
		}
		
		Boolean emAndamento = true;
		
		while (emAndamento == true) {
			System.out.println("\n------------------------------\nExecute uma acao:\n");
			System.out.println("-> iniciar\n-> sair\n");
			System.out.println("\n------------------------------\n");
			String opcao = Console.readLine();
			if (opcao.compareTo("sair") == 0) {
				break;
			}
			if (opcao.compareTo("iniciar") == 0) {
				PartidaSingleton.instance();
				Jogo.instance("iniciar",tempo);
			}
		}
	}

	public static Document leXml(String endereco) throws SAXException, IOException, ParserConfigurationException {
		File xmlFile = new File(endereco);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		builder = factory.newDocumentBuilder();

		Document doc = builder.parse(xmlFile);
		doc.getDocumentElement();

		return doc;
	}
	public static long retornaTempoX(Document doc) 
	{
		NodeList root = doc.getElementsByTagName("root");
		Node rootNode = root.item(0);
		Element rootElement = (Element) rootNode;
		String tempoTemp = rootElement.getElementsByTagName("tempo").item(0).getTextContent();
		//convertendo tempo em inteiro
		long tempo = 0;
		tempo = (long) Integer.parseInt(tempoTemp);
		return tempo;
	}
	public static long retornaTempoJ(JSONObject doc) 
	{
		long tempo = 0;
		tempo = (long) doc.get("tempo");
		return tempo;
	}
	public static JSONObject leJSON(String endereco) throws FileNotFoundException, IOException, ParseException 
	{
		Object doc = new JSONParser().parse(new FileReader(endereco));
		
		JSONObject temp = (JSONObject) doc;
		
		return temp;
	}
}
