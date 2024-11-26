package app.model;

import app.model.polysyllogismes.Polysyllogisme;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class representing a collection of rules for evaluating syllogisms.
 */
public class Rules {

    private static Map<Rule, Boolean> listRules =new HashMap<>();
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
	public String AllRules(Polysyllogisme polysyllogisme) {
		throw new UnsupportedOperationException();
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
