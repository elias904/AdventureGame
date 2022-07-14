package command;

import jogo.LabirintoSingleton;

public class VerMapaCommand implements Command {

	public void execute(Object[] obj) {

	 
		String mapa =  LabirintoSingleton.imprimir();
		System.out.println(mapa);
	}

}
