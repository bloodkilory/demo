package com.example.cor;

/**
 * @author bloodkilory
 *         generate on 15/5/26
 */
public class Sales extends PriceHandler {
	@Override
	public void processDiscount(float discount) {
		if(discount <= 0.05) {
			System.out.format("%s批准了折扣: %.2f\n", this.getClass().getSimpleName(), discount);
		} else {
			successor.processDiscount(discount);
		}
	}
}
