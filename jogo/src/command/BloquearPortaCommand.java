package command;

import jogo.Item;
import jogo.JogadorSingleton;
import jogo.PocaoMagica;
import jogo.Porta;

public class BloquearPortaCommand implements Command {
	String tipo;

	public BloquearPortaCommand(String s) {
		this.tipo = s;
	}

	public void execute(Object[] obj) {
		boolean acaoRealizada = false;
		PocaoMagica pM = null;
		for (Item i : JogadorSingleton.getItens()) {
			if (i.getTipoItem().compareTo("Pocao") == 0) {
				pM = (PocaoMagica) i;
				for (Porta K : JogadorSingleton.getSalaAtual().getPortas()) {
					if (K.getTipo().compareTo(tipo) == 0 && K.getTrancada() == false) {
						if (JogadorSingleton.getProximoAoItem().compareTo(tipo) == 0) {
							if (tipo.compareTo("A") == 0) {
								K.trancar();
								acaoRealizada = true;
								break;
							} else if (tipo.compareTo("B") == 0) {
								K.trancar();
								acaoRealizada = true;
								break;
							} else if (tipo.compareTo("C") == 0) {
								K.trancar();
								acaoRealizada = true;
								break;
							}
						} else {
							System.out.println("Aproxime-se.");
						}
					}
				}
			}
		}
		if (acaoRealizada == true) {
			JogadorSingleton.removerItem(pM);
			System.out.println("\nPorta bloqueada com sucesso\n");
		} else {
			System.out.println("\nNão foi possivel bloquear porta\n");
		}
	}
}
