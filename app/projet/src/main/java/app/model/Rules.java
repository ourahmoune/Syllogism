package app.model;

import java.util.HashMap;
import java.util.Map;
import app.model.polysyllogismes.Polysyllogisme;

/**
 * Class representing a collection of rules for evaluating syllogisms.
 */
public  class  Rules {

	private static Map<Rule, Boolean> listRules = new HashMap<>();
	/**
	 * Evaluates all rules against the given syllogism.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return a string result of the evaluation (not yet implemented)
	 * @throws UnsupportedOperationException if not implemented
	 */
	public static HashMap<String, String> AllRulesInvalide(Polysyllogisme polysyllogisme) {
		HashMap<String, String> res = new HashMap<String, String>();
		for (Map.Entry<Rule, Boolean> entry : listRules.entrySet()) {
			if (entry.getValue()) {
				if (!entry.getKey().Launch(polysyllogisme)) {
					res.put(entry.getKey().getClass().getSimpleName(), entry.getKey().toString());
				}
			}
		}
		return res;
	}
	/**
	 * Launches the evaluation of all rules against the given syllogism.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return true if all rules are satisfied, false otherwise
	 */
	public static Boolean Launch(Polysyllogisme polysyllogisme) {
		for (Map.Entry<Rule, Boolean> entry : listRules.entrySet()) {

			if (entry.getValue()) {
				if (!entry.getKey().Launch(polysyllogisme)) {
					System.out.println(	entry.getKey());

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
public static  void initialize(){
	listRules.put(new Rmt(), true);
	listRules.put(new Raa(), true);
	listRules.put(new Rii(), false);
	listRules.put(new Rlh(), true);
	listRules.put(new Rnn(), true);
	listRules.put(new Rn(), true);
	listRules.put(new Rp(), true);
	listRules.put(new Rpp(), true);
	listRules.put(new Ruu(), false);
}

}