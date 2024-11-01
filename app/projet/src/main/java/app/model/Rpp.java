package app.model;
public class Rpp  extends Rule{

	private String Result;

	/**
	 *
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
        return syllogism.getProposition().get(1).getQuantificator().getQuantity() != Quantity.Exisential || syllogism.getProposition().get(2).getQuantificator().getQuantity() != Quantity.Exisential;
    }


}