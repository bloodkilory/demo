package com.example.cor;

/**
 * @author bloodkilory
 *         generate on 15/5/26
 */
public abstract class PriceHandler {
	protected PriceHandler successor;

	public void setSuccessor(PriceHandler successor) {
		this.successor = successor;
	}

	public abstract void processDiscount(float discount);
}
