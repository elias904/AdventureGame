package command;

import java.util.List;
import jogo.Item;
import jogo.JogadorSingleton;
import jogo.Porta;

public class AbrirPortaCommand implements Command {
	String tipo;

	public AbrirPortaCommand(String s) {
		tipo = s;
	}

	public void execute(Object[] obj) {
		Boolean abriu = false;
		for (Porta p : (List<Porta>) JogadorSingleton.getSalaAtual().getPortas()) {
			if (p.getTipo().compareTo(tipo) == 0) {
				System.out.println();
				if (JogadorSingleton.getProximoAoItem().compareTo(tipo) == 0) {
					if (p.getTrancada() == false) {
						System.out.println("Sala já está aberta");
						abriu = true;
						break;
					} else {
						for (Item i : JogadorSingleton.getItens()) {
							if (i.getTipo().compareTo(tipo) == 0) {
								p.detrancar();
								System.out.println("Porta destrancada com sucesso " + p.getTipo());
								abriu = true;
								break;
							}
						}
					}
				} else {

					System.out.println("Aproxime-se.");

				}

			}
		}

		if (abriu == false)
			System.out.println("Não foi possivel abrir a porta");
	}
}
