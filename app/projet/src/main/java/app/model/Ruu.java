package app.model;

import app.model.polysyllogismes.Polysyllogisme;

/**
 * Ruu is a subclass of Rule that evaluates a syllogism based on the types of its propositions.
 */
public class Ruu extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given syllogism.
	 *
	 * @param polysyllogism the syllogism to evaluate
	 * @return true if the rule is satisfied, false otherwise
	 */
	public Boolean Launch(Polysyllogisme polysyllogism) {boolean result = true ;
		int compter = 0;
		int taille = polysyllogism.getTaille();
		Proposition p , c= null ;
		c = polysyllogism.getProposition().get(taille);
		for (int i = 1; i < taille; i++) {
			if(c.getQuantity() ==  Quantity.Exisential){
				p = polysyllogism.getProposition().get(i);
				if(p.getQuantity() ==  Quantity.Universal){
					compter++;
					if(compter == 2){
						return  false;
					}
				}
			}

		}
		return result ;
	}
}
