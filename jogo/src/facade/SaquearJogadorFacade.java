package facade;

import java.util.List;

import jogo.Item;
import jogo.JogadorSingleton;
import jogo.Machado;
import jogo.PocaoMagica;
import jogo.Sala;
import jogo.Troll;

public class SaquearJogadorFacade {
	public static void LancarMachado() {
		Sala sala = JogadorSingleton.getSalaAtual();
		PocaoMagica pocao = null;
		List<Item> itens = JogadorSingleton.getItens();
		if (!sala.getMachados().isEmpty() && !sala.getTrolls().isEmpty()) {
			Troll troll = sala.getTrolls().get(0);
			Machado machado = sala.getMachados().get(0);
			boolean haPocao = false;
			troll.pegarMachado(machado);
			for (Item x : itens) {
				if (x.getTipoItem() == "Pocao") {
					pocao = (PocaoMagica) x;
					haPocao = true;
				}
			}
			if (haPocao) {
				JogadorSingleton.removerItem(pocao);
				System.out.println("Defesa mágica contra machados ativada. -1 Poção.");
			} else {
				JogadorSingleton.removerOuro();
				System.out.println("Você foi golpeado e o seu ouro foi saqueado");
			}
			sala.removerItem(machado, "Machado");
		}
	}
}
