package app.model;

import app.controller.SettingController;
import app.model.polysyllogismes.Polysyllogisme;

/**
 * Rn is a subclass of Rule that evaluates a syllogism based on specific types of propositions.
 */
public class Rn extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given polysyllogisme.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return true if the rule is satisfied, false otherwise
	 */
	public Boolean Launch(Polysyllogisme polysyllogisme) {
		boolean result = true ;
		int taille = polysyllogisme.getTaille();
		Proposition p,c= null ;
		c = polysyllogisme.getProposition().get(taille);
		for(int i = 1 ; i < taille ; i++){
			p = polysyllogisme.getProposition().get(i);
			if(p.getQuality() == Quality.Negative && c.getQuality() != Quality.Negative){
				return false ;
			}
		}
		return result ;
	}

	@Override
	public String toString() {
		if (SettingController.getLanguage().equals("english")){
			return "If a premise is negative, the conclusion is negative.";
		}else{
			return "Si une prémisse est négative, la conclusion est négative.";
		}
	}
}
