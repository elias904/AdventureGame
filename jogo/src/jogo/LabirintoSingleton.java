package jogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LabirintoSingleton {

	private static LabirintoSingleton labirinto;
	private static List<Sala> conjuntoSalas = new ArrayList<Sala>();

	private LabirintoSingleton() {
	}

	public synchronized static LabirintoSingleton instance() {
		if (labirinto == null) {
			labirinto = new LabirintoSingleton();

		}
		return labirinto;
	}

	public static void criarLabirinto() throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException 
	{
		
		String endereco = "C:\\Users\\mende\\OneDrive\\Área de Trabalho\\Jogo-main\\jogo\\src\\jogo\\mapa.xml";
		String extensaoDoArquivo = FilenameUtils.getExtension(endereco);
		System.out.println("Criando Labirinto");
		
		
		if (extensaoDoArquivo.compareTo("xml") == 0) 
		{	
			Document docX;
			docX = leXml(endereco);
			cadastroEmXml(docX);
			ChaveA a = new ChaveA();
			getConjuntoSalas().get(0).incluirChave(a);
			ChaveB b = new ChaveB();
			getConjuntoSalas().get(5).incluirChave(b);
			ChaveC c = new ChaveC();
			getConjuntoSalas().get(11).incluirChave(c);
			
			
		} 
		else 
		{
			JSONObject docJ = new JSONObject();
			docJ =  leJSON(endereco);
			lerArquivo(docJ);
			ChaveA a = new ChaveA();
			getConjuntoSalas().get(0).incluirChave(a);
			ChaveB b = new ChaveB();
			getConjuntoSalas().get(5).incluirChave(b);
			ChaveC c = new ChaveC();
			getConjuntoSalas().get(11).incluirChave(c);
		}

		System.out.println("Labirinto Criado com sucesso");

	}

	public static void inserirJogador(JogadorSingleton jogador) {
		Sala sala = (LabirintoSingleton.getConjuntoSalas()).get(0);
		sala.setPresencaJogador(true);
		JogadorSingleton.setSalaAtual(sala);

	}

	public static List<Sala> getConjuntoSalas() {
		return conjuntoSalas;
	}

	public void setSalas(List<Sala> salas) {
		conjuntoSalas = salas;
	}

	public static void lerArquivo(JSONObject data) 
	{		
		JSONArray salas = (JSONArray) data.get("salas");
		for (int i = 0; i < 20; i++) {
			List<Porta> portas = new ArrayList<Porta>();
			JSONObject salaArquivo = (JSONObject) salas.get(i);
			JSONObject porta = (JSONObject) salaArquivo.get("A");
			Boolean existePortaA = (Boolean) porta.get("porta");
			PortaA pA = new PortaA();
			verifcaExistenciaPorta(existePortaA, porta, pA, portas);
			porta = (JSONObject) salaArquivo.get("B");
			Boolean existePortaB = (Boolean) porta.get("porta");
			PortaB pB = new PortaB();
			verifcaExistenciaPorta(existePortaB, porta, pB, portas);
			porta = (JSONObject) salaArquivo.get("C");
			Boolean existePortaC = (Boolean) porta.get("porta");
			PortaC pC = new PortaC();
			verifcaExistenciaPorta(existePortaC, porta, pC, portas);
			Long indiceSala = (Long) salaArquivo.get("indice");
			Boolean entrada = (Boolean) salaArquivo.get("entrada");
			Boolean saida = (Boolean) salaArquivo.get("saida");
			Sala novaSala = new Sala();
			novaSala.construirSala(indiceSala, entrada, saida, portas);
			conjuntoSalas.add(novaSala);

		}
	}

	public static List<Porta> verifcaExistenciaPorta(Boolean existencia, JSONObject p, Porta porta,
			List<Porta> portas) {
		if (existencia == true) {
			Long salaVizinha = null;
			String portaVizinha = null;
			if (Programa.tipoArquivo.compareTo("xml") == 0) {
				if (p.get("salaVizinha").toString().compareTo("") != 0)
					salaVizinha = (Long) p.get("salaVizinha");
				if (p.get("portaVizinha").toString().compareTo("") != 0)
					portaVizinha = (String) p.get("portaVizinha");
			} else {
				salaVizinha = (Long) p.get("salaVizinha");
				portaVizinha = (String) p.get("portaVizinha");

			}
			porta.setPortaVizinha(portaVizinha);
			porta.setSalaVizinha(salaVizinha);
			portas.add(porta);
		}
		return portas;

	}

	public static void lerArquivoXML(String endereco) {
		File xmlFile = new File(endereco);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc;
		try {
			doc = builder.parse(xmlFile);
			doc.getDocumentElement();
			cadastroEmXml(doc);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void cadastroEmXml(Document doc) {
		NodeList mapaNodes = doc.getElementsByTagName("salas");

		NodeList a = doc.getElementsByTagName("A");
		NodeList b = doc.getElementsByTagName("B");
		NodeList c = doc.getElementsByTagName("C");

		for (int i = 0; i < mapaNodes.getLength(); i++) {
			// for the room construction we will need the: codSala,enter,exit,ListOfDoors

			Node mapaNode = mapaNodes.item(i);
			Node portasA = a.item(i);
			Node portasB = b.item(i);
			Node portasC = c.item(i);

			if (mapaNode.getNodeType() == Node.ELEMENT_NODE) {
				Sala novaSala = new Sala();
				List<Porta> portas = new ArrayList<Porta>();
				Element mapaElement = (Element) mapaNode;

				Element aElement = (Element) portasA;
				Element bElement = (Element) portasB;
				Element cElement = (Element) portasC;

				String entrada = mapaElement.getElementsByTagName("entrada").item(0).getTextContent();
				boolean existeEntrada = Boolean.parseBoolean(entrada);

				String saida = mapaElement.getElementsByTagName("saida").item(0).getTextContent();
				boolean existeSaida = Boolean.parseBoolean(saida);

				// transformando indice em inteiro
				long ind = 0;
				String indice = mapaElement.getElementsByTagName("indice").item(0).getTextContent();
				ind = Integer.parseInt(indice);

				String portaA = aElement.getElementsByTagName("porta").item(0).getTextContent();
				boolean existeA = Boolean.parseBoolean(portaA);
				PortaA pA = new PortaA();
				verificaPortaXml(existeA, aElement, pA, portas);

				String portaB = bElement.getElementsByTagName("porta").item(0).getTextContent();
				boolean existeB = Boolean.parseBoolean(portaB);
				PortaB pB = new PortaB();
				verificaPortaXml(existeB, bElement, pB, portas);

				String portaC = cElement.getElementsByTagName("porta").item(0).getTextContent();
				boolean existeC = Boolean.parseBoolean(portaC);
				PortaC pC = new PortaC();
				verificaPortaXml(existeC, cElement, pC, portas);

				novaSala.construirSala(ind, existeEntrada, existeSaida, portas);
				conjuntoSalas.add(novaSala);
			}

		}

	}

	public static List<Porta> verificaPortaXml(Boolean existencia, Element salaPorta, Porta porta, List<Porta> portas) {
		if (existencia == true) {
			String salaVizinha = null;
			String portaVizinha = null;

			salaVizinha = salaPorta.getElementsByTagName("salaVizinha").item(0).getTextContent();
			if (salaVizinha != "") {
				Long salaV = (long) Integer.parseInt(salaVizinha);
				porta.setSalaVizinha(salaV);
			}
			portaVizinha = salaPorta.getElementsByTagName("portaVizinha").item(0).getTextContent();

			porta.setPortaVizinha(portaVizinha);
			portas.add(porta);

		}
		return portas;
	}

	public static String imprimir() {
		String s = "";
		for (Sala sl : getConjuntoSalas()) {
			s += sl.imprimir();
		}
		return s;
	}

	public static void delete() {
		LabirintoSingleton.getConjuntoSalas().clear();
		labirinto = null;
	}
	public static JSONObject leJSON(String endereco) throws FileNotFoundException, IOException, ParseException 
	{
		Object doc = new JSONParser().parse(new FileReader(endereco));
		
		JSONObject temp = (JSONObject) doc;
		
		return temp;
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

}
