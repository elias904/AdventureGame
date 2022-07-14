package jogo;

public class Troll {
	private Sala salaAtual;
	private Machado machado = null;
	private Integer codigoTroll;
	
	public Troll(Integer x, Sala sala) {
		this.setCodigoTroll(x);
		this.setSalaAtual(sala);
	}

	public Sala getSalaAtual() {
		return salaAtual;
	}

	public void setSalaAtual(Sala salaAtual) {
		this.salaAtual = salaAtual;
	}

	public Machado getMachado() {
		return machado;
	}

	public void pegarMachado(Machado machado) {
		this.machado = machado;
	}

	public void removerMachado() {
		this.machado = null;
	}

	public Integer getCodigoTroll() {
		return codigoTroll;
	}

	public void setCodigoTroll(Integer codigoTroll) {
		this.codigoTroll = codigoTroll;
	}

}
