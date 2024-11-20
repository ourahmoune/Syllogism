package app.model;

import app.model.polysyllogismes.Polysyllogisme;

import java.util.Map;
import java.util.Objects;

/**
 * The Rlh class represents the "latius hos" Rule.
 * Rlh: the quantity of a term in the conclusion can only be universal if it is so in the premise containing that term.
 * <p>
 * The main method of this class, `Launch`, checks if a given syllogism complies with syllogistic logic rules
 * based on its figure type and the types of propositions (A, E, I, O) that make up the syllogism.
 * </p>
 */
public class Rlh extends Rule {

    private String Result;

    /**
     * Main method to evaluate the validity of a polysyllogisme.
     * <p>
     * This method determines whether the polysyllogisme is valid based on its premises (modeP1 and modeP2),
     * its conclusion (modeC), and its figure.
     * </p>
     *
     * @param polysyllogisme The `Syllogism` object to evaluate, containing the propositions (premises and conclusion) as well as the figure.
     * @return A boolean `true` if the polysyllogisme is valid according to the defined rules, `false` otherwise.
     * @throws UnsupportedOperationException if the proposition type or the figure of the polysyllogisme is not supported.
     */
    public Boolean Launch(Polysyllogisme polysyllogisme) {
        boolean result1 = true;
        boolean result2 = true;
        String SujetConc , PredicatConc = null ;
        Proposition p1 = polysyllogisme.getProposition().get(1);
        Proposition p2 = polysyllogisme.getProposition().get(2);
        Proposition C = polysyllogisme.getProposition().get(polysyllogisme.getTaille());
        Map<String,String> FistSecondCommun = polysyllogisme.CheckTwoPremise(p1, p2);
        if(FistSecondCommun !=null){
            if(C.getQuality() == Quality.Negative){
                if(p1.getSubject().equals( FistSecondCommun.get("FirstIsole"))){
                    result1 = p1.getQuantity() ==  Quantity.Universal ;
                }else{
                    result1 = p1.getQuality() == Quality.Negative ;
                }
            }
        }
        p1 = polysyllogisme.getProposition().get(polysyllogisme.getTaille()-2);
        p2 = polysyllogisme.getProposition().get(polysyllogisme.getTaille()-1);
        FistSecondCommun = polysyllogisme.CheckTwoPremise(p1, p2);
        if(FistSecondCommun !=null){

            if(C.getQuantity() == Quantity.Universal ){
                if(Objects.equals(p2.getSubject(), FistSecondCommun.get("SecondIsole"))){
                    result2 = p2.getQuantity() ==  Quantity.Universal ;
                }else{
                    result2 = p2.getQuality() == Quality.Negative ;
                }
            }
        }
        return result1&result2;
    }
}
