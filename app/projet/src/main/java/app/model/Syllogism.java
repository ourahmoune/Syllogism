package app.model;

import app.model.polysyllogismes.Polysyllogisme;

import java.util.Map;

/**
 * Class representing a syllogism consisting of a figure, propositions, and associated rules.
 */
public class Syllogism extends Polysyllogisme {

	/**
	 * Constructs a Syllogism with the specified figure and propositions.
	 *
	 * @param figure     the figure of the syllogism
	 * @param proposition the propositions of the syllogism
	 */
	public Syllogism(Figure figure, Map<Integer, Proposition> proposition) {
		this.taille =3;
		this.figure = figure;
		this.proposition = proposition;
	}

	public boolean isValid() {
		return this.valid;
	}


	public Figure getFigure() {
		return this.figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public void setProposition(Map<Integer, Proposition> proposition) {
		this.proposition = proposition;
	}
	public void setRules(Rules rules) {
		this.rules = rules;
	}
	/**
	 * Returns the result of the syllogism evaluation (not yet implemented).
	 *
	 * @return the result of the evaluation
	 * @throws UnsupportedOperationException if not implemented
	 */
	public String result() {
		throw new UnsupportedOperationException();
	}
	public void setprposition(Figure figure) {
		switch (figure) {
			case UN -> {
				proposition.get(1).setSubject("M"); proposition.get(1).setPredicat("P");
				proposition.get(2).setSubject("S"); proposition.get(2).setPredicat("M");
			}
			case DEUX -> {
				proposition.get(1).setSubject("P"); proposition.get(1).setPredicat("M");
				proposition.get(2).setSubject("S"); proposition.get(2).setPredicat("M");
			}
			case TROIS -> {
				proposition.get(1).setSubject("M"); proposition.get(1).setPredicat("P");
				proposition.get(2).setSubject("M"); proposition.get(2).setPredicat("S");
			}
			case QUATRE -> {
				proposition.get(1).setSubject("P"); proposition.get(1).setPredicat("M");
				proposition.get(2).setSubject("M"); proposition.get(2).setPredicat("S");
			}
		}
	}


}
