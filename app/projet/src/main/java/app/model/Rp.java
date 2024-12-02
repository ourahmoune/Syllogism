package app.model;

import app.model.polysyllogismes.Polysyllogisme;

/**
 * Rp is a subclass of Rule that evaluates a syllogism based on the types of its propositions.
 */
public class Rp extends Rule {

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
			if(p.getQuantity()== Quantity.Exisential && c.getQuantity() != Quantity.Exisential){
				return false ;
			}
		}
		return result ;

	}
}
