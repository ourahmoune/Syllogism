package app.model;

import app.controller.SettingController;
import app.model.polysyllogismes.Polysyllogisme;

/**
 * Rnn is a subclass of Rule that evaluates a syllogism based on the qualities of its propositions.
 */
public class Rnn extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given polysyllogisme.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return true if at least one proposition is not negative, false otherwise
	 */
	public Boolean Launch(Polysyllogisme polysyllogisme) {
		boolean result = true ;
		int compter = 0;
		int taille = polysyllogisme.getTaille();
		Proposition p= null ;
		for (int i = 1; i < taille; i++) {
			p = polysyllogisme.getProposition().get(i);
			if(p.getQuality() == Quality.Negative){
				compter++;
				if(compter == 2){
					 return  false;
				}
			}
		}
		return result ;
	}

	@Override
	public String toString() {
		if (SettingController.getLanguage().equals("english")){
			return "Two negative premises do not give a conclusion.";
		}else{
			return "Deux prémisses négatives ne donnent pas de conclusion.";
		}
	}
}
