package app.model;

import app.controller.SettingController;
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
     * @param syllogism The `Syllogism` object to evaluate, containing the types of propositions and the figure.
     * @return A boolean indicating whether the polysyllogisme is valid according to the defined rules.
     */

    public Boolean Launch(Polysyllogisme syllogism) {

        Type modeP1 = syllogism.getProposition().get(1).getType();
        Type modeP2 = syllogism.getProposition().get(2).getType();
        Type modeC = syllogism.getProposition().get(3).getType();
        List<Rule> ruleList = new ArrayList<>();

        Rmt rmt = new Rmt();ruleList.add(rmt);
        Rlh rlh = new Rlh();ruleList.add(rlh);
        Raa raa = new Raa();ruleList.add(raa);
        Rpp rpp = new Rpp();ruleList.add(rpp);
        Rp rp = new Rp();ruleList.add(rp);
        Rnn rnn = new Rnn();ruleList.add(rnn);
        Rn rn = new Rn();ruleList.add(rn);

        boolean conclusionresul=true ;
        for(Rule  rule : ruleList){
            if(!rule.Launch(syllogism)){
                conclusionresul=false;
                break;
            }
        }

        if (
                (modeP1 == Type.A || modeP1 == Type.E) &&
                (modeP2 == Type.A || modeP2 == Type.E) &&
                (modeC == Type.I || modeC == Type.O) &&
                (conclusionresul)
        )
        {
            Map<Integer, Proposition> propositionA = new HashMap<>();
            propositionA.put(1, new Proposition(modeP1));
            propositionA.put(2, new Proposition(modeP2));
            propositionA.put(3, new Proposition(Type.A));
            Figure figure=syllogism.getFigure();
            Syllogism syllogismA = new Syllogism(figure, propositionA);
            syllogismA.setprposition(figure);

            Map<Integer, Proposition> propositionE = new HashMap<>();
            propositionE.put(1, new Proposition(modeP1));
            propositionE.put(2, new Proposition(modeP2));
            propositionE.put(3, new Proposition(Type.E));
            Syllogism syllogismE = new Syllogism(figure, propositionE);
            syllogismE.setprposition(figure);



            boolean validE=true ;
            for(Rule  rule : ruleList){
                if(!rule.Launch(syllogismE)){
                    validE=false;
                    break;
                }
            }

            boolean validA=true ;
            for(Rule  rule : ruleList){
                if(!rule.Launch(syllogismA)){
                    validA=false;
                    break;
                }
            }

            if(!validA && !validE){
                return true ;
            }else{
                return false;
            }
        }
        return conclusionresul & true;
    }

    @Override
    public String toString() {
        if (SettingController.getLanguage().equals("english")){
            return "A syllogism is considered uninteresting if its conclusion is existential and can be replaced by a stronger universal conclusion.";
        }else{
            return "Un syllogisme est considéré comme inintéressant si sa conclusion est existentielle et peut être remplacée par une conclusion universelle plus forte.";
        }
    }
}
