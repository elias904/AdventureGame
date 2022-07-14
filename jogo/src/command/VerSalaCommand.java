package command;

import jogo.JogadorSingleton;

public class VerSalaCommand implements Command {

	@Override
	public void execute(Object[] obj) {
		System.out.println(JogadorSingleton.getSalaAtual().imprimir());
	}

}
