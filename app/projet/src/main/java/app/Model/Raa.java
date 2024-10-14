package app.Model;
public class Raa  extends Rule{

	private String Result;
	public Raa(){
		this.Result = "Deux prémisses affirmatives donnent une conclusion affirmative.";
	}
	/**
	 * 
	 * @param syllogism
	 */

	public Boolean Launch(Syllogism syllogism) {
		if(syllogism.getProposition().get(1).getQuality()==Quality.Affirmative &&syllogism.getProposition().get(2).getQuality()==Quality.Affirmative &&syllogism.getProposition().get(3).getQuality()==Quality.Negative){
			return false;
		}
		return true;
	}



}