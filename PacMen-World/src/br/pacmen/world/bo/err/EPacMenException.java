package br.pacmen.world.bo.err;

@SuppressWarnings("serial")
public class EPacMenException extends Exception {

	public EPacMenException(String msg) {
		super(msg);
	}

	public EPacMenException(String msg, Exception e) {
		super(msg, e);
	}

}
