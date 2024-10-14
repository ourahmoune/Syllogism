package app.Model;
public class Rn  extends Rule{

	private String Result;

	/**
	 *
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
		return (syllogism.getProposition().get(1).getQuality()==Quality.Negative && syllogism.getProposition().get(3).getQuality()==Quality.Negative)
				|| (syllogism.getProposition().get(2).getQuality()==Quality.Negative && syllogism.getProposition().get(3).getQuality()==Quality.Negative);
	}
}