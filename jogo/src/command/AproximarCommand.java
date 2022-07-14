package command;

import jogo.Item;
import jogo.JogadorSingleton;
import jogo.Porta;

public class AproximarCommand implements Command {
	String tipo = null;
	Boolean tipoPorta = false;
	public AproximarCommand(String s) {
		this.tipo = s;
	}

	public AproximarCommand(String s, Boolean b) {
		this.tipo = s;
		this.tipoPorta = b;
	}

	public void execute(Object[] obj) {
		Boolean aproximacao = false;
		if (this.tipoPorta == true) {
			for (Porta p : JogadorSingleton.getSalaAtual().getPortas()) {
				if (p.getTipo().compareTo(tipo) == 0) {
					JogadorSingleton.setProximoAoItem(p.getTipo());
					aproximacao = true;
					break;
				}
			}
		} else {
			for (Item i : JogadorSingleton.getSalaAtual().getItens()) {
				if (i.getTipoItem().compareTo(tipo) == 0) {
					JogadorSingleton.setProximoAoItem(tipo);
					aproximacao = true;
				}
			}
		}
		if (aproximacao == false) {
			System.out.println("Não foi possível se aproximar");
		}
		else {
			System.out.println("Aproximação realizada");
		}
	}

}
