package command;

import java.util.List;

import jogo.Item;
import jogo.JogadorSingleton;
import jogo.Sala;

public class LargarItemCommand implements Command {
	String tipo;
	
	public LargarItemCommand(String s) {
		this.tipo = s;
	}

	@Override
	public void execute(Object[] obj) {
		

		Sala sala = JogadorSingleton.getSalaAtual();
		
		
		Boolean achouItem = false;
		if ((JogadorSingleton.getQuantItens() >0) || this.tipo.compareTo("Diamante") != 0
				|| this.tipo.compareTo("Ouro") != 0) 
		{
			
	
			for (Item i : JogadorSingleton.getItens()) 
			{
				if (i.getTipoItem().compareTo(this.tipo) == 0) 
				{
					achouItem = true;
					sala.retornaItemSala(tipo, i);
					JogadorSingleton.removerItem(i);
					break;
				}
			}
		}
		if(achouItem == false)
		{
			System.out.println("Não é possível remover");
		}
			
		
		
	}

	

}
