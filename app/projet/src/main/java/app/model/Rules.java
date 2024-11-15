package app.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a collection of rules for evaluating syllogisms.
 */
public class Rules {

	private static Map<Rule, Boolean> listRules =new HashMap<>();
	/**
	 * Evaluates all rules against the given syllogism.
	 *
	 * @param syllogism the syllogism to evaluate
	 * @return a string result of the evaluation (not yet implemented)
	 * @throws UnsupportedOperationException if not implemented
	 */
	public String AllRules(Syllogism syllogism) {
		// TODO - implement Rules.AllRules
		throw new UnsupportedOperationException();
	}
	public static Boolean Launch(Syllogism syllogism) {
		for (Map.Entry<Rule, Boolean> entry : listRules.entrySet()) {
			if (entry.getValue()) {
				if (!entry.getKey().Launch(syllogism)) {
					return false;
				}
			}
		}
		return true;
	}

	public static Map<Rule, Boolean> getListRules() {
		return listRules;
	}

	public static void updateRule(Rule rule, Boolean value) {
		listRules.put(rule, value);
	}


}