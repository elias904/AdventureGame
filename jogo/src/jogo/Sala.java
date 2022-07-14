package jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sala {
	private Long codSala;
	private boolean entrada;
	private boolean saida;
	private List<Item> itens = new ArrayList<>();
	private List<Machado> machados = new ArrayList<Machado>();
	private List<PocaoMagica> pocoes = new ArrayList<PocaoMagica>();
	private List<Ouro> ouros = new ArrayList<Ouro>();
	private List<Diamante> diamantes = new ArrayList<Diamante>();
	private List<Chave> chaves = new ArrayList<Chave>();
	private List<Troll> trolls = new ArrayList<Troll>();
	private List<Porta> portas = new ArrayList<>();
	private Random random = null;
	private Boolean presencaJogador;

	public Sala() {
		this.incluirTroll();
		this.incluirItem("Machado");
		this.incluirItem("Ouro");
		this.incluirItem("Diamante");
		this.incluirItem("Pocao");
		this.presencaJogador = false;
	}

	public void construirSala(Long codSala, boolean entrada, boolean saida, List<Porta> portas) {
		this.setCodSala(codSala);
		this.setEntrada(entrada);
		this.setSaida(saida);
		this.portas = portas;
	}

	public String imprimir() {
		String s = "\n-----------------------------\n";
		String sPortas = "";
		for (Porta p : portas) {
			sPortas += "\n" + p.imprimir();
		}
		s += "Sala: " + getCodSala() + "\nPresenca do jogador: " + presencaJogador + "\nPossui Entrada: " + entrada
				+ "\nPossui Saida:" + saida + "\n\nPortas: " + sPortas;
		s += "\n" + quantidadeDeElementosEItens();
		s += "\n-----------------------------\n";
		return s;
	}

	public String quantidadeDeElementosEItens() {
		String s = "\nQuantidade de:\n     Machados: " + machados.size() + "\n     Pocoes: " + pocoes.size()
				+ "\n     Chaves: " + chaves.size() + " Tipo de chave: " + this.getTipoChaves() + "\n     Trolls: "
				+ trolls.size() + "\n     Ouros: " + ouros.size() + "\n     Diamantes: " + diamantes.size() + "\n";
		return s;
	}

	private String getTipoChaves() {
		String s = "[ ";
		for (Chave c : this.chaves) {
			s += " " + c.getTipo();
		}
		s += " ]";

		return s;
	}

	public void incluirPorta(Porta porta) {
		portas.add(porta);

	}

	public void incluirTroll() {
		Troll troll = null;
		random = new Random();
		for (int i = 0; i < random.nextInt(3); i++) {
			troll = new Troll(i,this);
			this.trolls.add(troll);
		}
	}
	
	public void entradaDoTroll(Troll t) {
			this.trolls.add(t);
		}
	

	public void incluirChave(Chave c) {
		chaves.add(c);
		itens.add(c);
	}
	
	
	public void retornaItemSala(String tipo,Item x) 
	{
		if(tipo.compareTo("Machado")== 0)
		{
			machados.add((Machado) x);
		}
		else if(tipo.compareTo("Chave") == 0) 
		{
			chaves.add((Chave) x);
		}
		else if(tipo.compareTo("Pocao") == 0) 
		{
			pocoes.add((PocaoMagica) x);
		}
		itens.add(x);
	}
	public void incluirItem(String tipo) {
		Item x = null;
		random = new Random();
		for (int i = 0; i < random.nextInt(10); i++) {
			if (tipo.compareTo("Ouro") == 0) {
				x = new Ouro();
				this.ouros.add((Ouro) x);
			} else if (tipo.compareTo("Machado") == 0) {
				x = new Machado();
				this.machados.add((Machado) x);
			} else if (tipo.compareTo("Pocao") == 0) {
				x = new PocaoMagica();
				this.pocoes.add((PocaoMagica) x);
			} else if (tipo.compareTo("Diamante") == 0) {
				x = new Diamante();
				this.diamantes.add((Diamante) x);
			}
			this.itens.add(x);
		}

	}

	public void removerItem(Item x, String tipo) {
		if (tipo.compareTo("Ouro") == 0)
			this.ouros.remove(x);
		else if (tipo.compareTo("Diamante") == 0)
			this.diamantes.remove(x);
		else if (tipo.compareTo("Machado") == 0)
			this.machados.remove(x);
		else if (tipo.compareTo("Pocao") == 0)
			this.pocoes.remove(x);
		else if (tipo.compareTo("Chave") == 0)
			this.chaves.remove(x);
		this.itens.remove(x);

	}

	public void removerTroll(Troll t) {
		this.trolls.remove(t);
	}

	public Long getCodSala() {
		return codSala;
	}

	public void setCodSala(Long k2) {
		this.codSala = k2;
	}

	public List<Machado> getMachados() {
		return machados;
	}

	public List<PocaoMagica> getPocoes() {
		return pocoes;
	}

	public List<Ouro> getOuros() {
		return ouros;
	}

	public List<Diamante> getDiamantes() {
		return diamantes;
	}

	public List<Chave> getChaves() {
		return chaves;
	}

	public List<Troll> getTrolls() {
		return trolls;
	}

	public Boolean getPresencaJogador() {
		return presencaJogador;
	}

	public void setPresencaJogador(Boolean presencaJogador) {
		this.presencaJogador = presencaJogador;
	}

	public boolean getEntrada() {
		return this.entrada;
	}

	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}

	public void setSaida(boolean saida) {
		this.saida = saida;
	}

	public Boolean getSaida() {
		return this.saida;
	}

	public List<Item> getItens() {
		return itens;
	}

	public List<Porta> getPortas() {
		return portas;
	}

}
