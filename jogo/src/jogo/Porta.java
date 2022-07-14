package jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Porta {

	private Boolean trancada;
	private String portaVizinha;
	private Long salaVizinha;
	private Integer codigoChave;
	private List<Sala> salas = new ArrayList<Sala>();

	public Porta() {
		this.setTrancada(ThreadLocalRandom.current().nextBoolean());
	}

	public void trancar() {
		this.trancada = true;

	}

	public void detrancar() {
		this.trancada = false;
	}

	public String imprimir() {
		String s = "";
		s += "\nPorta " + getTipo() + "\nTrancada: " + trancada + "\nPorta Vizinha " + portaVizinha + "\nSala vizinha "
				+ salaVizinha;
		return s;
	}

	public String getTipo() {
		return "a";
	}

	public Boolean getTrancada() {
		return trancada;
	}

	public void setTrancada(Boolean trancada) {
		this.trancada = trancada;
	}

	public Integer getCodigoChave() {
		return codigoChave;
	}

	public void setCodigoChave(Integer codigoChave) {
		this.codigoChave = codigoChave;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public String getPortaVizinha() {
		return portaVizinha;
	}

	public void setPortaVizinha(String portaVizinha) {
		this.portaVizinha = portaVizinha;
	}

	public Long getSalaVizinha() {
		return salaVizinha;
	}

	public void setSalaVizinha(Long salaVizinha) {
		this.salaVizinha = salaVizinha;
	}
}
