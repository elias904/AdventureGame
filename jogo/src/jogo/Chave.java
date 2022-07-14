package jogo;

public class Chave extends Item {

	public String getTipo() {
		return "";
	}

	public String getTipoItem() {
		return "Chave";
	}
	
	public String imprimir() {
		String s= "";
		s+= getTipoItem() +" " + getTipo();
		return s;
	}

}
