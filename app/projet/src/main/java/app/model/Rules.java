package app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a collection of rules for evaluating syllogisms.
 */
public class Rules {

	private List<Rule> listRules;
	/**
	 * Constructs a Rules object with the specified list of rules.
	 *
	 * @param listRules the list of rules to be evaluated
	 */
	public Rules(List<Rule> listRules) {
		this.listRules = listRules;
	}

	/**
	 * Evaluates all rules against the given syllogism.
	 *
	 * @param syllogism the syllogism to evaluate
	 * @return a string result of the evaluation (not yet implemented)
	 * @throws UnsupportedOperationException if not implemented
	 */
	public String AllRules(Syllogism syllogism) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Launches the evaluation of all rules against the given syllogism.
	 *
	 * @param syllogism the syllogism to evaluate
	 * @return true if all rules are satisfied, false otherwise
	 */
	public Boolean Launch(Syllogism syllogism) {
		for (Rule rule : listRules) {
			if (!rule.Launch(syllogism)) {
				return false;
			}
		}
		return true;
	}
}
