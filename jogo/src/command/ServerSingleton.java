package command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class ServerSingleton {
	private static ServerSingleton service;
	private List<String> todosComando = new ArrayList<>();

	public synchronized static ServerSingleton instance() {
		if (service == null) {
			service = new ServerSingleton();
		}
		return service;
	}

	private Map<String, Command> comandos = new HashMap<>();

	public ServerSingleton() {
		criarComandos();
	}

	private void criarComandos() {

		Collections.addAll(this.todosComando, "mapa", "sala", "iniciar", "pegar(Machado)", "inventario", "pegar(Chave)",
				"pegar(Pocao)", "pegar(Ouro)", "pegar(Diamante)", "largarItem", "passarPor(A)", "passarPor(B)",
				"passarPor(C)", "largar(Pocao)", "largar(Chave)", "largar(Machado)", "abrir(A)", "abrir(B)", "abrir(C)",
				"arremessar(Machado)", "fim", "bloquear(A)", "bloquear(B)", "bloquear(C)", "aproximar(Chave)",
				"aproximar(Machado)", "aproximar(Pocao)", "aproximar(Ouro)", "aproximar(Diamante)", "aproximar(A)",
				"aproximar(B)", "aproximar(C)");
		comandos.put("iniciar", new IniciarPartidaCommand());
		comandos.put("mapa", new VerMapaCommand());
		comandos.put("sala", new VerSalaCommand());
		comandos.put("fim", new FinalizarPartidaCommand());
		comandos.put("abrir(A)", new AbrirPortaCommand("A"));
		comandos.put("abrir(B)", new AbrirPortaCommand("B"));
		comandos.put("abrir(C)", new AbrirPortaCommand("C"));
		comandos.put("pegar(Machado)", new PegarItemCommand("Machado"));
		comandos.put("pegar(Pocao)", new PegarItemCommand("Pocao"));
		comandos.put("pegar(Chave)", new PegarItemCommand("Chave"));
		comandos.put("pegar(Ouro)", new PegarItemCommand("Ouro"));
		comandos.put("pegar(Diamante)", new PegarItemCommand("Diamante"));
		comandos.put("largar(Pocao)", new LargarItemCommand("Pocao"));
		comandos.put("largar(Machado)", new LargarItemCommand("Machado"));
		comandos.put("largar(Chave)", new LargarItemCommand("Chave"));
		comandos.put("inventario", new VerInventarioCommand());
		comandos.put("bloquear(A)", new BloquearPortaCommand("A"));
		comandos.put("bloquear(B)", new BloquearPortaCommand("B"));
		comandos.put("bloquear(C)", new BloquearPortaCommand("C"));
		comandos.put("arremessar(Machado)", new ArremessarMachadoCommand());
		comandos.put("passarPor(A)", new MoverJogadorCommand("A"));
		comandos.put("passarPor(B)", new MoverJogadorCommand("B"));
		comandos.put("passarPor(C)", new MoverJogadorCommand("C"));
		comandos.put("aproximar(Chave)", new AproximarCommand("Chave"));
		comandos.put("aproximar(Machado)", new AproximarCommand("Machado"));
		comandos.put("aproximar(Pocao)", new AproximarCommand("Pocao"));
		comandos.put("aproximar(Diamante)", new AproximarCommand("Diamante"));
		comandos.put("aproximar(Ouro)", new AproximarCommand("Ouro"));
		comandos.put("aproximar(A)", new AproximarCommand("A", true));
		comandos.put("aproximar(B)", new AproximarCommand("B", true));
		comandos.put("aproximar(C)", new AproximarCommand("C", true));

	}

	public void service(String comando, Object[] objects) throws ComandoInvalido, FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException {
		if (this.todosComando.contains(comando) == false) {
			throw new ComandoInvalido();
		}
		Command c = comandos.get(comando);
		c.execute(objects);
	}

}
