package app.model.allSyllogism;

import app.model.Syllogism;
import app.model.polysyllogismes.Polysyllogisme;

import java.util.ArrayList;
import java.util.List;

/**
 * The SyllogismAndRules class encapsulates a syllogism and its associated logical rules.
 * It holds a reference to a Syllogism object and a list of boolean values representing
 * the results of the rules applied to that syllogism.
 */
public class PolySyllogismAndRules {
    private Syllogism syllogism;
    private List<Boolean> rules = new ArrayList<>();

    /**
     * Default constructor initializes the syllogism to null.
     */
    public PolySyllogismAndRules() {
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
        rules.add(Rule);
    }

    /**
     * Retrieves the rule evaluation result at the specified index.
     *
     * @param index The index of the rule result to retrieve.
     * @return The boolean result of the rule evaluation.
     */
    public boolean getRules(int index) {
        return rules.get(index);
    }
}
