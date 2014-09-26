package br.com.H2Helper.exception;

public class SalaNaoAlteradaException  extends H2Exception {

	
	private static final long serialVersionUID = 8772837803300706780L;

	public SalaNaoAlteradaException() {
		super("Sala nao alterada");
	}

}
