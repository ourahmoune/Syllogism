package app.model;

import java.util.Map;

public class Syllogism   {

	private Figure figure;
	private Map<Integer,Proposition> proposition;
	private Rules rules;
	private boolean valid;

	public boolean isValid() {
		return this.valid;
	}
	public Map<Integer,Proposition> getProposition() {
		return this.proposition;
	}
	public Figure getFigure() {
		return this.figure;
	}
	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public String result() {
		// TODO - implement Syllogism.result
		throw new UnsupportedOperationException();
	}

	public void solve() {
		// TODO - implement Syllogism.solve
		throw new UnsupportedOperationException();
	}

	public Syllogism(Figure figure, Map<Integer,Proposition> proposition) {
		this.figure = figure;
		this.proposition = proposition;
	}
	public void setProposition(Map<Integer,Proposition> proposition) {
		this.proposition = proposition;
	}

}