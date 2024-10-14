package app.Model;
public class Quantificator {

	private Quantity quantity;
	private String name;

	public Quantity getQuantity() {
		return this.quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param name
	 * @param quantity
	 */
	public Quantificator(int name, Quantity quantity) {
		// TODO - implement Quantificator.Quantificator
		throw new UnsupportedOperationException();
	}
	public Quantificator(Quantity quantity, String name) {
		this.quantity = quantity;
		this.name = name;
	}

}