package jogo;

public class PocaoMagica extends Item {
	private String tipo;

	public PocaoMagica() {

		this.tipo = "Pocao";
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
