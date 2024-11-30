package app.model;

import app.model.polysyllogismes.Polysyllogisme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Rii class represents a rule for "uninteresting" syllogisms.
 * Rii: Some syllogisms, while valid, are considered uninteresting because their existential conclusion
 * could have been replaced with a stronger universal conclusion.
 * This class evaluates a syllogism based on its types of propositions and its figure.
 */
public class Rii extends Rule {

    private String Result;

    /**
     * Evaluates the validity of a polysyllogisme based on its types of propositions and its figure.
     * Utilizes a list of rules (`ruleList`) and modified syllogisms to check validity
     * according to specific conditions.
     *
     * @param polysyllogisme The `Syllogism` object to evaluate, containing the types of propositions and the figure.
     * @return A boolean indicating whether the polysyllogisme is valid according to the defined rules.
     */

    public Boolean Launch(Polysyllogisme polysyllogisme) {
        Type modeP1 = polysyllogisme.getProposition().get(1).getType();
        Type modeP2 = polysyllogisme.getProposition().get(2).getType();
        Type modeC = polysyllogisme.getProposition().get(3).getType();

        Rules rules = new Rules();
        /*
        if ((modeP1 == Type.A || modeP1 == Type.E) &&
                (modeP2 == Type.A || modeP2 == Type.E) &&
                (modeC == Type.I || modeC == Type.O) &&
                rules.Launch(polysyllogisme)) {

            Map<Integer, Proposition> propositionA = new HashMap<>();
            propositionA.put(1, new Proposition(modeP1));
            propositionA.put(2, new Proposition(modeP2));
            propositionA.put(3, new Proposition(Type.A));
            Syllogism syllogismA = new Syllogism(polysyllogisme.getFigure(), propositionA);

            Map<Integer, Proposition> propositionE = new HashMap<>();
            propositionE.put(1, new Proposition(modeP1));
            propositionE.put(2, new Proposition(modeP2));
            propositionE.put(3, new Proposition(Type.E));
            Syllogism syllogismE = new Syllogism(polysyllogisme.getFigure(), propositionE);

            boolean validA = rules.Launch(syllogismA);
            boolean validE = rules.Launch(syllogismE);

            return validA || validE;
        }

         */
        return false;
    }
}
