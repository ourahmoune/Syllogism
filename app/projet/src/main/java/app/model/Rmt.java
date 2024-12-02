package app.model;

import app.model.polysyllogismes.Polysyllogisme;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.Objects;

/**
 * The Rmt class represents the Middle Term Rule.
 * Rmt: the quantity of M must be universal in at least one of the premises.
 * The main method `Launch` checks if the types of the premises (A, E, I, O) of a given syllogism
 * comply with the specific rules associated with each figure.
 */
public class Rmt extends Rule {

	private String Result;

	/**
	 * Evaluates the validity of a polysyllogisme based on its premise types and figure.
	 *
	 * @param polysyllogisme The `Syllogism` object to evaluate, containing the types of propositions and the figure.
	 * @return A boolean indicating whether the polysyllogisme is valid according to the defined rules.
	 * @throws UnsupportedOperationException if the figure of the polysyllogisme is not supported.
	 */
	public Boolean Launch(Polysyllogisme polysyllogisme) {
		boolean result = true;
		int taille  = polysyllogisme.getTaille();
		Map<String , String> FistSecondCommun = null ;
		Proposition p1 = null ;
		Proposition p2 = null ;
		for (int i = 1 ; i<taille -1  ; i++){
			p1=polysyllogisme.getProposition().get(i);
			p2=polysyllogisme.getProposition().get(i+1);
			FistSecondCommun = polysyllogisme.CheckTwoPremise(p1,p2);
			String MoyenTerme = FistSecondCommun.get("Commun");
			if(MoyenTerme.equals( p1.getSubject())){
				if(MoyenTerme.equals( p2.getSubject())){
					result =  p1.getType()== Type.A || p1.getType() == Type.E || p2.getType() == Type.A || p2.getType() == Type.E;
				}else{
					result = p1.getType() == Type.A || p1.getType() == Type.E || p2.getType() == Type.E || p2.getType() == Type.O;
				}
			}else{
				if(MoyenTerme.equals( p2.getSubject())){
					result =  p1.getType()== Type.O || p1.getType() == Type.E || p2.getType() == Type.A || p2.getType() == Type.E;
				}else{
					result = p1.getType() == Type.O || p1.getType() == Type.E || p2.getType() == Type.E || p2.getType() == Type.O;
				}
			}
			if(!result){
				return   false;
			}
		}
		return result;
	}
}
