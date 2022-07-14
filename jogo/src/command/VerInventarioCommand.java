package command;

import jogo.Item;
import jogo.JogadorSingleton;

public class VerInventarioCommand implements Command {

	@Override
	public void execute(Object[] obj) {
		System.out.println("___________Inventário_________________");
		Integer quantMachados = 0;
		Integer quantPocoes = 0;
		String tipoChave = "Chaves:\n ";
		for (Item i : JogadorSingleton.getItens()) {
			if (i.getTipoItem().compareTo("Chave") == 0)
				tipoChave += "    - " + i.imprimir();
			else if (i.getTipoItem().compareTo("Machado") == 0) {
				quantMachados++;
			} else if (i.getTipoItem().compareTo("Pocao") == 0) {
				quantPocoes++;
			}
		}
		System.out.println("\n Ouro: " + JogadorSingleton.getOuros().size());
		System.out.println("\n Diamantes: " + JogadorSingleton.getDiamantes().size());
		System.out.println("\n Machados: " + quantMachados);
		System.out.println("\n Pocoes: " + quantPocoes);
		System.out.println("\n " + tipoChave);

	}

}
