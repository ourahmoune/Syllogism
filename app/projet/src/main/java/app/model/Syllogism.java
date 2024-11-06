package app.model;

import java.util.Map;

/**
 * Class representing a syllogism consisting of a figure, propositions, and associated rules.
 */
public class Syllogism {
	private Figure figure;
	private Map<Integer, Proposition> proposition;
	private Rules rules;
	private boolean valid;
	/**
	 * Constructs a Syllogism with the specified figure and propositions.
	 *
	 * @param figure     the figure of the syllogism
	 * @param proposition the propositions of the syllogism
	 */
	public Syllogism(Figure figure, Map<Integer, Proposition> proposition) {
		this.figure = figure;
		this.proposition = proposition;
	}

	public boolean isValid() {
		return this.valid;
	}

	public Map<Integer, Proposition> getProposition() {
		return this.proposition;
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

	/**
	 * Solves the syllogism (not yet implemented).
	 *
	 *
	 * @throws UnsupportedOperationException if not implemented
	 */

	public void solve() {
		this.valid=rules.Launch(this);
		if(this.valid) {
			System.out.println("le syllogism est valid");
		}else{
			System.out.println("le syllogism est invalid");
		}
	}
}
