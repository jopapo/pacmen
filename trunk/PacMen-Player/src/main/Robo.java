package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import busca.AEstrela;
import busca.Estado;
import busca.Heuristica;
import busca.Nodo;

import components.Coordenada;

public class Robo implements Estado, Heuristica {

	/* Enumeração dos movimentos do fantasma */
	public enum Movimento {
		ESQUERDA, DIREITA, CIMA, BAIXO
	};

	/* Coordenada do PacMan */
	private Coordenada coo;

	/* PacManContol */
	static PacManControl pmc;

	/* Meta que o Fantasma tem que se focar */
	static Coordenada meta;

	/**
	 * Construtor da classe Atribui uma coordenada ao nosso "Robo" Fantasma
	 * 
	 * @param coo
	 */
	public Robo(Coordenada coo) {
		this.coo = coo;
	}

	/**
	 * Pega a distancia de Manhatan
	 * 
	 * @return distancia de Manhatan
	 */
	private int getManhatan() {
		return Math.abs(coo.getX() - meta.getX())
				+ Math.abs(coo.getY() - meta.getY());
	}

	/**
	 * Custo da geração deste estado
	 * 
	 * @return custo da geração do estado
	 */
	public int custo() {
		return 1;
	}

	/**
	 * Verifica se a coordenada é a meta
	 * 
	 * @return Se a coordenada é meta <code> true </code> senão
	 *         <code> false </code>
	 */
	public boolean ehMeta() {
		return coo.equals(meta);
	}

	/**
	 * Verifica se a coordenada do robo é igual a coordenada do objeto passado
	 * como parâmetro.
	 * 
	 * @return <code> true </code> se coordenada igual a do objeto, senão
	 *         <code> false </code>
	 */
	public boolean equals(Object obj) {
		return coo.equals(((Robo) obj).getCoordenada());
	}

	/**
	 * @return coordenada
	 */
	public Coordenada getCoordenada() {
		return coo;
	}

	/**
	 * Descrição do Problema Proposto
	 * 
	 * @return Uma descrição do Problema
	 * @see busca.Estado#getDescricao()
	 */
	public String getDescricao() {
		return "Robo Pac-Man";
	}

	/**
	 * Gera uma lista de sucessores do nodo
	 * 
	 * @return sucessor do nodo
	 */
	public List<Estado> sucessores() {
		List<Estado> suc = new LinkedList<Estado>();
		Random rd = new Random();
		ArrayList<Integer> li = new ArrayList<Integer>();
		li.add(0);
		li.add(1);
		li.add(2);
		li.add(3);
		Robo m = null;
		while (li.size() > 0) {
			int i = rd.nextInt(li.size());
			switch (li.get(i)) {
			case 0:
				m = movePraEsquerda();
				break;
			case 1:
				m = movePraDireita();
				break;
			case 2:
				m = movePraBaixo();
				break;
			case 3:
				m = movePraCima();
				break;
			}
			if (ehValido(m))
				suc.add(m);
			li.remove(i);
		}
		return suc;
	}

	/**
	 * Move o PacManRobo para Cima
	 * 
	 * @return Objeto de Robo, movido para cima
	 */
	private Robo movePraCima() {
		return new Robo(new Coordenada(coo.getX(), coo.getY() - 1));
	}

	/**
	 * Move o PacManRobo para Baixo
	 * 
	 * @return Objeto de Robo, movido para baixo
	 */
	private Robo movePraBaixo() {
		return new Robo(new Coordenada(coo.getX(), coo.getY() + 1));
	}

	/**
	 * Move o PacManRobo para Esquerda
	 * 
	 * @return Objeto de Robo, movido para esquerda
	 */
	private Robo movePraEsquerda() {
		return new Robo(new Coordenada(coo.getX() - 1, coo.getY()));
	}

	/**
	 * Move o PacManRobo para Direita
	 * 
	 * @return Objeto de Robo, movido para direita
	 */
	private Robo movePraDireita() {
		return new Robo(new Coordenada(coo.getX() + 1, coo.getY()));
	}

	/**
	 * Verifica se o movimento do robo é valido
	 * 
	 * @param r
	 * @return <code> true </code> se o robo pode se mover caso contrário
	 *         <code> false </code>
	 */
	private static boolean ehValido(Robo r) {
		return (r != null) && (r.getCoordenada().getX() >= 0)
				&& (r.getCoordenada().getY() >= 0)
				&& (r.getCoordenada().getX() < pmc.getWidth())
				&& (r.getCoordenada().getY() < pmc.getHeight())
				&& (pmc.estaLivre(r.getCoordenada()));
	}

	public String toString() {
		return coo.toString();
	}

	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * Controla a inteligencia do Fantasma
	 * 
	 * @param control
	 * @return
	 */
	public static Movimento ia(PacManControl control) {
		pmc = control;
		// Estado inicial
		meta = pmc.getPosPacMan();
		Estado ini = new Robo(pmc.getPosGhost());
		Nodo fim = new AEstrela().busca(ini);

		meta = pmc.getPosCozinha();
		Nodo cozinha = new AEstrela().busca(ini);

		if (pmc.getFome() > fomeMaxima(fim.getProfundidade(), cozinha.getProfundidade()))
			fim = cozinha;

		while ((fim != null) && (fim.getProfundidade() > 1))
			fim = fim.getPai();

		if ((fim != null) && (!pmc.estaPausado())) {

			switch (((Robo) fim.getEstado()).getCoordenada().getX()
					- ((Robo) ini).getCoordenada().getX()) {
			case -1:
				return Movimento.ESQUERDA;
			case 1:
				return Movimento.DIREITA;
			}

			switch (((Robo) fim.getEstado()).getCoordenada().getY()
					- ((Robo) ini).getCoordenada().getY()) {
			case -1:
				return Movimento.CIMA;
			case 1:
				return Movimento.BAIXO;
			}
		}

		return null;
	}

	/**
	 * Estimativa de Custo
	 * 
	 * @return Distancia de Manhatan
	 */
	public int h() {
		return getManhatan();
	}

	private static int fomeMaxima(int dp, int dc) {
		return Float.valueOf(450f - 5.311778f * Float.valueOf(dp) + 5.311778f * Float.valueOf(dc)).intValue();
		/*return Float.valueOf(
				((2 * dist * dist * dist) / 1305f)
						+ ((77 * dist * dist) / 145f) + ((-7369 * dist) / 261f)
						+ (54610 / 87f)).intValue();*/
	}

}
