package command;

import jogo.Item;
import jogo.JogadorSingleton;
import jogo.Sala;

public class PegarItemCommand implements Command {
	String tipo;
	Boolean ouroProtegido = null;

	public PegarItemCommand(String s) {
		this.tipo = s;
		ouroProtegido = false;
	}

	public void execute(Object[] obj) {
		Sala sala = JogadorSingleton.getSalaAtual();
		Boolean achouItem = false;
		if (JogadorSingleton.getProximoAoItem().compareTo(tipo) == 0) {
			if (tipo.compareTo("Diamante") == 0 || tipo.compareTo("Ouro") == 0) {
				if (sala.getTrolls().size() > 0) {
					System.out.println("O ouro está protegido");
				} else {
					adicionarItem(sala, achouItem);
				}
			} else {
				adicionarItem(sala, achouItem);
			}
		} else {
			System.out.println("Aproxime-se.");
		}

	}

	public void adicionarItem(Sala sala, Boolean achouItem) {
		if (((JogadorSingleton.getQuantItens() < 5) || this.tipo.compareTo("Diamante") == 0
				|| this.tipo.compareTo("Ouro") == 0)) {
			for (Item i : sala.getItens()) {
				if (i.getTipoItem().compareTo(this.tipo) == 0) {
					achouItem = true;
					JogadorSingleton.adiconarItem(i, this.tipo);
					sala.removerItem(i, i.getTipoItem());
					System.out.println("Item adicionado ao inventário");
					break;
				}
			}
		} else {
			achouItem = true;
			System.out.println("O inventário está cheio");
		}
		if (achouItem == false)
			System.out.println("O Item nao está presente na sala");
	}

}
