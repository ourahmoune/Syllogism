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
        if((modeP1 == Type.A ||modeP1 == Type.E ) && ( modeP2 == Type.A || modeP2 == Type.E) && (modec == Type.I ||modec == Type.O)&&(Rules.Launch(syllogism))){
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
            boolean valideA = Rules.Launch(syllogismeA);
            boolean valideE = Rules.Launch(syllogismeE);
            return valideA || valideE;


        }
        return false;
    }
}
