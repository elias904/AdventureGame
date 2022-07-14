package jogo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import command.ComandoInvalido;
import command.ServerSingleton;

public class Jogo {
	static ServerSingleton server = new ServerSingleton();
	private static Object[] data = new Object[10];
	private static Jogo jogo;
	static Instant tpartida;
	static Long tmax;

	public synchronized static Jogo instance(String acao, Long tempoMax) throws SAXException, ParserConfigurationException {
		tmax = tempoMax;
		tpartida = Instant.now();
		tpartida = tpartida.plusSeconds(tmax);
		if (jogo == null) {
			jogo = new Jogo();
			manipuladorClasses(acao);
		}
		return jogo;
	}

	public static long getT_max() {
		return tmax;
	}

	public static void setT_max(long t_max) {
		Jogo.tmax = t_max;

	}

	public static void manipuladorClasses(String acao) throws SAXException, ParserConfigurationException {

		while (PartidaSingleton.getPartidaEmAndamento() == true) {
			if (tpartida.isBefore(Instant.now())) {
				System.out.println("VOCÊ PERDEU. FIM DE JOGO");
				acao = "fim";
			}
			try {
				server.service(acao, data);
				if (PartidaSingleton.getPartidaEmAndamento() == false)
					break;
			} catch (ComandoInvalido e) {
				System.out.println("\nErro: Comando Inválido.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}

			System.out.println("\n------------------------------\nExecute uma acao:\n");
			System.out.println(
					"-> mapa\n-> sala\n-> aproximar(Z)\n-> abrir(porta)\n-> passarPor(porta)\n-> pegar(X)\n-> largar(Y) \n-> arremessar(Machado)\n-> inventario\n-> bloquear(K) \n-> fim");
			System.out.println("\n------------------------------\n");
			System.out.println("OBS.:\n   porta = A ou B ou C \n   X = Machado ou Pocao ou Ouro ou Diamante ou Chave\n "
					+ "  Y = Machado ou Pocao ou Chave\n   Z= X ou porta\n");

			acao = Console.readLine();

		}
	}

	public static void delete() {
		jogo = null;
	}

}
