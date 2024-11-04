package app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rii extends Rule{
    private String Result;

    /**
     *
     * @param syllogism
     */
    public Boolean Launch(Syllogism syllogism) {
        Type modeP1 =syllogism.getProposition().get(1).getType();
        Type modeP2 =syllogism.getProposition().get(2).getType();
        Type modec = syllogism.getProposition().get(3).getType();
        Rmt rmt = new Rmt();
        Rlh rlh = new Rlh();
        Raa raa = new Raa();
        Rpp rpp = new Rpp();
        Rp rp = new Rp();
        Rnn rnn = new Rnn();
        Rn rn = new Rn();
        List<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(rmt);
        ruleList.add(rlh);
        ruleList.add(raa);
        ruleList.add(rpp);
        ruleList.add(rp);
        ruleList.add(rn)
        ;ruleList.add(rnn);
        Rules rules = new Rules(ruleList) ;
        if((modeP1 == Type.A ||modeP1 == Type.E ) && ( modeP2 == Type.A || modeP2 == Type.E) && (modec == Type.I ||modec == Type.O)&&(rules.Launch(syllogism))){
            Map<Integer,Proposition> propositionA =new HashMap<>();
            propositionA.put(1, new Proposition(modeP1));
            propositionA.put(2, new Proposition(modeP2));
            propositionA.put(3, new Proposition(Type.A));
            Syllogism syllogismeA = new Syllogism(syllogism.getFigure(), propositionA);
            Map<Integer,Proposition> propositionE =new HashMap<>();
            propositionE.put(1, new Proposition(modeP1));
            propositionE.put(2, new Proposition(modeP2));
            propositionE.put(3, new Proposition(Type.E));
            Syllogism syllogismeE = new Syllogism(syllogism.getFigure(), propositionE);
            boolean valideA = rules.Launch(syllogismeA);
            boolean valideE = rules.Launch(syllogismeE);
            // si l un des syllogism et valide alors c inintirisant
            return valideA || valideE;


        }
        return false;
    }
}
