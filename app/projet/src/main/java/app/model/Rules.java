package app.model;

import java.util.HashMap;
import java.util.Map;

public class Rules {

	private static Map<Rule, Boolean> listRules =new HashMap<>();
	/**
	 * 
	 * @param syllogism
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