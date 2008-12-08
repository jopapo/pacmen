package br.pacmen.ghost.ctl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.pacmen.ghost.bo.PacManVO;
import br.pacmen.world.bo.Actor;
import br.pacmen.world.bo.Coordinate;
import br.pacmen.world.bo.World;
import br.pacmen.world.bo.World.Movement;
import br.pacmen.world.bo.err.EPacMenException;
import br.pacmen.world.bo.utl.ClassIterator;
import busca.AEstrela;
import busca.Estado;
import busca.Heuristica;
import busca.Nodo;

public class Robo implements Estado, Heuristica {

	/* Coordenada do Ghost e da Meta*/
	private Coordinate coo, meta;
	
	private World world;

	/**
	 * Construtor da classe Atribui uma coordenada ao nosso "Robo" Fantasma
	 * 
	 * @param coo
	 */
	public Robo(World world, Coordinate coo, Coordinate meta) {
		this.world = world;
		this.coo = coo;
		this.meta = meta;
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
	public Coordinate getCoordenada() {
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
		ArrayList<Movement> li = new ArrayList<Movement>();
		li.add(Movement.LEFT);
		li.add(Movement.RIGHT);
		li.add(Movement.UP);
		li.add(Movement.DOWN);
		Robo m = null;
		while (li.size() > 0) {
			int i = rd.nextInt(li.size());
			try {
				m = new Robo(world, World.createMovement(coo, li.get(i)), meta);
			} catch (EPacMenException e) {
			}
			if (ehValido(m))
				suc.add(m);
			li.remove(i);
		}
		return suc;
	}

	/**
	 * Verifica se o movimento do robo é valido
	 * 
	 * @param r
	 * @return <code> true </code> se o robo pode se mover caso contrário
	 *         <code> false </code>
	 */
	private static boolean ehValido(Robo r) {
		try {
			return (r != null) && (r.getCoordenada().getX() >= 0)
					&& (r.getCoordenada().getY() >= 0)
					&& (r.getCoordenada().getX() < r.world.getWidth())
					&& (r.getCoordenada().getY() < r.world.getHeight())
					&& (r.world.canActorMoveTo(r.getCoordenada()));
		} catch (EPacMenException e) {
			e.printStackTrace();
			return false;
		}
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
	public static Movement ia(Actor actor) {
		// Para cada PacMen (usuário)
		ClassIterator ci = actor.getWorld().getIterator(PacManVO.class);
		while (ci.hasNext()) {
			PacManVO pac = (PacManVO) ci.next();
			
			//System.out.print(actor.toString() + " procurando por " + pac.toString() + "... ");
		
			// Estado inicial
			Estado ini = new Robo(actor.getWorld(), actor.getPos(), pac.getPos());
			Nodo fim = new AEstrela().busca(ini);
			
			//meta = pmc.getPosCozinha();
			//Nodo cozinha = new AEstrela().busca(ini);
	
			//if (pmc.getFome() > fomeMaxima(fim.getProfundidade(), cozinha.getProfundidade()))
				//fim = cozinha;
	
			while ((fim != null) && (fim.getProfundidade() > 1))
				fim = fim.getPai();
			
			if (fim != null) {
	
				//System.out.println("Achou em: " + ((Robo) fim.getEstado()).getCoordenada().toString());

				switch (((Robo) fim.getEstado()).getCoordenada().getX()
						- ((Robo) ini).getCoordenada().getX()) {
				case -1:
					return Movement.LEFT;
				case 1:
					return Movement.RIGHT;
				}
	
				switch (((Robo) fim.getEstado()).getCoordenada().getY()
						- ((Robo) ini).getCoordenada().getY()) {
				case -1:
					return Movement.UP;
				case 1:
					return Movement.DOWN;
				}
			}

			//System.out.println("Não achou!");
			
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

	/*private static int fomeMaxima(int dp, int dc) {
		return Float.valueOf(450f - 5.311778f * Float.valueOf(dp) + 5.311778f * Float.valueOf(dc)).intValue();
	}*/

}
