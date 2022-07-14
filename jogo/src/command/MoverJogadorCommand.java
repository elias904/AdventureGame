package command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import facade.MoverTrollFacade;
import facade.SaquearJogadorFacade;
import jogo.JogadorSingleton;
import jogo.LabirintoSingleton;
import jogo.PartidaSingleton;
import jogo.Porta;
import jogo.Sala;
import jogo.Troll;

public class MoverJogadorCommand implements Command {
	private String tipo = null;

	public MoverJogadorCommand(String s) {
		this.tipo = s;
	}

	public void execute(Object[] data) throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException {
		Boolean acaoExecutada = false;
		for (Porta p : JogadorSingleton.getSalaAtual().getPortas()) {
			if (p.getTipo().compareTo(tipo) == 0) {
				if (JogadorSingleton.getProximoAoItem().compareTo(tipo) == 0) {
					if (p.getTrancada() == false) {
						acaoExecutada = true;
						if (p.getPortaVizinha().compareTo("saida") == 0) {
							vencerJogo();
							break;
						}
						Integer Id = Math.toIntExact(p.getSalaVizinha());
						Sala salaVizinha = LabirintoSingleton.getConjuntoSalas().get(Id - 1);
						System.out.println("Movimento realizado da sala " + JogadorSingleton.getSalaAtual().getCodSala()
								+ " para a sala " + salaVizinha.getCodSala());
						JogadorSingleton.getSalaAtual().setPresencaJogador(false);
						JogadorSingleton.setSalaAtual(salaVizinha);
						JogadorSingleton.getSalaAtual().setPresencaJogador(true);
						MoverTrollFacade.moverTroll();
						SaquearJogadorFacade.LancarMachado();
					} else {
						System.out.println("Porta está trancada");
					}
					break;
				} else {
					System.out.println("Aproxime-se");
				}
			}
		}
		if (acaoExecutada == false)
			System.out.println("Não foi possível mudar de sala");
	}

	public void vencerJogo() throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException {
		System.out.println("__________________ Você venceu! _____________________");
		ServerSingleton server = new ServerSingleton();
		Object[] data = new Object[10];
		try {
			server.service("inventario", data);
			server.service("fim", data);
		} catch (ComandoInvalido e) {
			System.out.println("\nErro: Comando Inválido.");
		}

	}

}
