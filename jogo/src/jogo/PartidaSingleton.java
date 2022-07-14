package jogo;

public class PartidaSingleton {
	private static PartidaSingleton partida;
	private static Boolean partidaEmAndamento;

	private PartidaSingleton() {
		setPartidaEmAndamento(true);
	}

	public synchronized static PartidaSingleton instance() {
		if (partida == null) {
			partida = new PartidaSingleton();
		}
		return partida;
	}

	public static void fimPartida() {
		setPartidaEmAndamento(false);
		partida = null;
	}

	public static Boolean getPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public static void setPartidaEmAndamento(Boolean p) {
		partidaEmAndamento = p;
	}

}
