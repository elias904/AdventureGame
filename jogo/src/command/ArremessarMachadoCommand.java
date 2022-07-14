package command;

import java.util.List;

import jogo.Item;
import jogo.JogadorSingleton;
import jogo.Sala;
import jogo.Troll;

public class ArremessarMachadoCommand implements Command {
	public void execute(Object[] data) {
		Sala sala = JogadorSingleton.getSalaAtual();
		Item item = null;
		Boolean achouMachado = false;
		List<Troll> t = sala.getTrolls();
		if (sala.getTrolls().size() > 0) {
			for (Item i : JogadorSingleton.getItens()) {
				if (i.getTipoItem().compareTo("Machado") == 0) {
					achouMachado = true;
					item = i;
					break;
				}
			}
			if (achouMachado == true) {
				sala.removerTroll(t.get(0));
				JogadorSingleton.removerItem(item);
				System.out.println("Troll eliminado com sucesso	");

			} else {
				System.out.println("Você não possui nenhum machado");
			}
		} else {
			System.out.println("Não há trolls na sala");
		}
	}
}
