package facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jogo.LabirintoSingleton;
import jogo.Porta;
import jogo.Sala;
import jogo.Troll;

public class MoverTrollFacade {
	private static MoverTrollFacade movimento;

	public synchronized static MoverTrollFacade instance() {
		if (movimento == null) {
			movimento = new MoverTrollFacade();
		}
		return movimento;
	}

	public static void moverTroll() {
		Random random = new Random();
		for (Sala sala : LabirintoSingleton.getConjuntoSalas()) {
			List<Porta> portas = sala.getPortas();
			for (Integer i = 0; i < sala.getTrolls().size(); i++) {
				random = new Random();
				Integer Id = 0;
				Id = random.nextInt(portas.size());
				if (sala.getTrolls().isEmpty()==false) {
					Troll troll = new Troll(0,sala);
					troll = sala.getTrolls().get(i);
					if (Id != troll.getSalaAtual().getCodSala() - 1) {
						Porta porta = new Porta();
						porta = portas.get(Id);
						if (porta.getTrancada() == false) {
							Id = porta.getSalaVizinha().intValue() - 1;
							Sala salaVizinha = new Sala();
							salaVizinha = LabirintoSingleton.getConjuntoSalas().get(Id);
							troll.getSalaAtual().entradaDoTroll(troll);
							troll.setSalaAtual(salaVizinha);
							sala.removerTroll(troll);
						}

					}
				}
			}
		}

	}

}
