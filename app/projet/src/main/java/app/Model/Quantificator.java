package app.Model;
public class Quantificator {

	private Quantity quantity;
	private String name;
	/**
	 *
	 * @param name
	 * @param quantity
	 */

	public Quantificator(Quantity quantity, String name) {
		this.quantity = quantity;
		this.name = name;

	}
	public Quantity getQuantity() {
		return this.quantity;
	}



	public String getName() {
		return this.name;
	}





}