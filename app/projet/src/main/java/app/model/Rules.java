package app.model;

import java.util.List;

public class Rules {

	private List<Rule> listRules;

	/**
	 * 
	 * @param syllogism
	 */
	public String AllRules(Syllogism syllogism) {
		// TODO - implement Rules.AllRules
		throw new UnsupportedOperationException();
	}
	public Rules(List<Rule> listRules) {
		this.listRules = listRules;
	}
	public Boolean Launch(Syllogism syllogism) {
		for (Rule rule : listRules) {
			if(!rule.Launch(syllogism)) {
				return false;
			}
		}
		return true;
	}

}