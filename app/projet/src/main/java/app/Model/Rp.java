package app.Model;
public class Rp  extends Rule{

	private String Result;

	/**
	 *
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
        return (syllogism.getProposition().get(1).getQuantificator().getQuantity() != Quantity.Exisential && syllogism.getProposition().get(2).getQuantificator().getQuantity() != Quantity.Exisential) || syllogism.getProposition().get(3).getQuantificator().getQuantity() == Quantity.Exisential;
    }


}