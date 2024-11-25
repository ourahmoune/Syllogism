package app.model.allSyllogism;

import app.model.Syllogism;

import java.util.ArrayList;
import java.util.List;

/**
 * The SyllogismAndRules class encapsulates a syllogism and its associated logical rules.
 * It holds a reference to a Syllogism object and a list of boolean values representing
 * the results of the rules applied to that syllogism.
 */
public class SyllogismAndRules {
    private Syllogism syllogism;
    private List<Boolean> Rules = new ArrayList<>();

    /**
     * Default constructor initializes the syllogism to null.
     */
    public SyllogismAndRules() {
        syllogism = null;
    }

    /**
     * Sets the syllogism for this instance.
     *
     * @param syllogism The Syllogism object to be associated with this instance.
     */
    public void setSyllogism(Syllogism syllogism) {
        this.syllogism = syllogism;
    }

    /**
     * Retrieves the syllogism associated with this instance.
     *
     * @return The Syllogism object.
     */
    public Syllogism getSyllogism() {
        return syllogism;
    }

    /**
     * Adds a rule evaluation result to the list of rules.
     *
     * @param Rule The boolean result of a rule evaluation.
     */
    public void setRules(boolean Rule) {
        Rules.add(Rule);
    }

    /**
     * Retrieves the rule evaluation result at the specified index.
     *
     * @param index The index of the rule result to retrieve.
     * @return The boolean result of the rule evaluation.
     */
    public boolean getRules(int index) {
        return Rules.get(index);
    }

    public List<Boolean> getAllRules() {
        return Rules;
    }
}
