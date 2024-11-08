package app.model.polysyllogismes;

import app.model.Proposition;
import app.model.Syllogism;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Polysyllogisme {
    private Map<Integer, Proposition> proposition; ;
    private int taille ;;
    public Polysyllogisme( ) {}
    public Polysyllogisme(Map<Integer,Proposition> propositions, int taille) {
        this.proposition = propositions;
        this.taille = taille ;
    }
    public Map<Integer, Proposition> getProposition() {return proposition;}
    public int getTaille() {return taille;}
    public Map<String, String> CheckTwoPremise(Proposition P1 , Proposition P2){
        Map<String, String> result = null ;
        if(P1.getSubject().equals(P2.getSubject()) ){
            if(!P1.getPredicat().equals(P2.getPredicat()) ){
                result = new HashMap<>();
                result.put("FirstIsole",P1.getPredicat());
                result.put("SecondIsole",P2.getPredicat());
                result.put("Commun",P1.getSubject());
            }
        }
        if(P1.getSubject().equals(P2.getPredicat()) ){
            if(!P1.getPredicat().equals(P2.getSubject()) ){
                result = new HashMap<>();
                result.put("FirstIsole",P1.getPredicat());
                result.put("SecondIsole",P2.getSubject());
                result.put("Commun",P1.getSubject());
            }
        }
        if(P1.getPredicat().equals(P2.getPredicat()) ){
            if(!P1.getSubject().equals(P2.getSubject()) ){
                result = new HashMap<>();
                result.put("FirstIsole",P1.getSubject());
                result.put("SecondIsole",P2.getSubject());
                result.put("Commun",P1.getPredicat());
            }
        }
        if(P1.getPredicat().equals(P2.getSubject()) ){
            if(!P1.getSubject().equals(P2.getPredicat()) ){
                result = new HashMap<>();
                result.put("FirstIsole",P1.getSubject());
                result.put("SecondIsole",P2.getPredicat());
                result.put("Commun",P1.getPredicat());
            }
        }
        return result ;
    }
    public boolean HasValideForm(){
        boolean result = true ;
        Map<String , String> FistSecondCommun = null ;
        for(int i=1;i<taille ;i++){
            FistSecondCommun = CheckTwoPremise(proposition.get(i),proposition.get(i+1));
            if(FistSecondCommun == null){
                result = false ;
                break ;
            }
        }
        String IsoleFirst , IsoleLast , Terme1Conclusion , Terme2Conclusion = null ;
        FistSecondCommun = CheckTwoPremise(proposition.get(1),proposition.get(2));
        IsoleFirst = FistSecondCommun.get("FirstIsole");
        FistSecondCommun = CheckTwoPremise(proposition.get(taille-1),proposition.get(taille));
        IsoleLast = FistSecondCommun.get("FirstIsole");
        Terme1Conclusion = proposition.get(taille).getSubject();
        Terme2Conclusion = proposition.get(taille).getPredicat();
        result =(  (  IsoleFirst.equals(Terme1Conclusion ) || IsoleFirst.equals(Terme2Conclusion)   )
                &&
                (IsoleLast.equals(Terme1Conclusion) || IsoleLast.equals( Terme2Conclusion)));
        return result ;
    }
}