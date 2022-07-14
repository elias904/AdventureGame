package jogo;

public class Machado extends Item {
	private String tipo;

	public Machado() {
		
		this.tipo = "Machado";
	}

	public String getTipoItem() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String imprimir() {
		String s = "";
		s += getTipoItem();
		return s;
	}
}