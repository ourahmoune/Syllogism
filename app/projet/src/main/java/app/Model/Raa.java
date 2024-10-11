package app.Model;
public class Raa  extends Rule{

	private String Result;

	/**
	 * 
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
		if(syllogism.getProposition().get(1).getQuality()==Quality.Affirmative &&syllogism.getProposition().get(2).getQuality()==Quality.Affirmative &&syllogism.getProposition().get(3).getQuality()==Quality.Affirmative){
			return true;
		}
		return false;
	}

	public void operation() {
		// TODO - implement Raa.operation
		throw new UnsupportedOperationException();
	}

}