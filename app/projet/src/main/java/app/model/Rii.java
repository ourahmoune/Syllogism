package app.model;

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
     * Evaluates the validity of a syllogism based on its types of propositions and its figure.
     * Utilizes a list of rules (`ruleList`) and modified syllogisms to check validity
     * according to specific conditions.
     *
     * @param syllogism The `Syllogism` object to evaluate, containing the types of propositions and the figure.
     * @return A boolean indicating whether the syllogism is valid according to the defined rules.
     */
    public Boolean Launch(Syllogism syllogism) {
        Type modeP1 = syllogism.getProposition().get(1).getType();
        Type modeP2 = syllogism.getProposition().get(2).getType();
        Type modeC = syllogism.getProposition().get(3).getType();

        Rmt rmt = new Rmt();
        Rlh rlh = new Rlh();
        Raa raa = new Raa();
        Rpp rpp = new Rpp();
        Rp rp = new Rp();
        Rnn rnn = new Rnn();
        Rn rn = new Rn();

        List<Rule> ruleList = new ArrayList<>();
        ruleList.add(rmt);
        ruleList.add(rlh);
        ruleList.add(raa);
        ruleList.add(rpp);
        ruleList.add(rp);
        ruleList.add(rn);
        ruleList.add(rnn);

        Rules rules = new Rules(ruleList);

        if ((modeP1 == Type.A || modeP1 == Type.E) &&
                (modeP2 == Type.A || modeP2 == Type.E) &&
                (modeC == Type.I || modeC == Type.O) &&
                rules.Launch(syllogism)) {

            Map<Integer, Proposition> propositionA = new HashMap<>();
            propositionA.put(1, new Proposition(modeP1));
            propositionA.put(2, new Proposition(modeP2));
            propositionA.put(3, new Proposition(Type.A));
            Syllogism syllogismA = new Syllogism(syllogism.getFigure(), propositionA);

            Map<Integer, Proposition> propositionE = new HashMap<>();
            propositionE.put(1, new Proposition(modeP1));
            propositionE.put(2, new Proposition(modeP2));
            propositionE.put(3, new Proposition(Type.E));
            Syllogism syllogismE = new Syllogism(syllogism.getFigure(), propositionE);

            boolean validA = rules.Launch(syllogismA);
            boolean validE = rules.Launch(syllogismE);

            return validA || validE;
        }

        return false;
    }
}
