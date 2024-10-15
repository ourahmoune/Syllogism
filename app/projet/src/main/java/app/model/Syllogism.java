package app.model;

import java.util.Map;

public class Syllogism  extends Rule{

	private Figure figure;
	private Map<Integer,Proposition> proposition;
	private Rules rules;
	private boolean valid;

	public boolean isValid() {
		return this.valid;
	}

	public String result() {
		// TODO - implement Syllogism.result
		throw new UnsupportedOperationException();
	}

	public void solve() {
		// TODO - implement Syllogism.solve
		throw new UnsupportedOperationException();
	}

	public Syllogism() {
		// TODO - implement Syllogism.Syllogism
		throw new UnsupportedOperationException();
	}

}