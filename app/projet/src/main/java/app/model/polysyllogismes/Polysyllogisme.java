package app.model.polysyllogismes;

import app.model.Proposition;
import app.model.Syllogism;

import java.util.Map;
import java.util.Objects;

public class Polysyllogisme {
    private Map<Integer, Proposition> proposition; ;
    private int taille ;;
    public Polysyllogisme(Map<Integer,Proposition> propositions, int taille) {
        this.proposition = propositions;
        this.taille = taille ;
    }

    public boolean IsValidForm(){
        boolean result = true ;
        String S1,S2 ,P1,P2 ,FirstIsole , LastIsole = null ;
        Proposition Premisse1 = null;
        Proposition Premisse2 = null;
        // tester si chaque couple de premisses ont un terme commun
        for(int i =1 ; i<=taille ; i++){
            Premisse1 = proposition.get(i);
            Premisse2 = proposition.get(i+1);
            S1 = Premisse1.getSubject() ;
            S2 = Premisse2.getSubject() ;
            P1 = Premisse1.getPredicat();
            P2 = Premisse2.getPredicat() ;
            if(!Objects.equals(S1, S2) || !Objects.equals(S1, P2) || !Objects.equals(P1, S2) || !Objects.equals(P1, P2)){
                return false ;
            }
        }
        Premisse1= proposition.get(1); // premier premise
        Premisse2 = proposition.get(taille+1); // conclusion
        if(Objects.equals(Premisse1.getSubject(), Premisse2.getPredicat())) {
            FirstIsole = Premisse1.getPredicat();
            LastIsole = Premisse2.getSubject();
        }
        if(Objects.equals(Premisse1.getPredicat(), Premisse2.getPredicat())) {
            FirstIsole = Premisse1.getSubject();
            LastIsole = Premisse2.getSubject();
        }
        if(Objects.equals(Premisse1.getSubject(), Premisse2.getSubject())) {
            FirstIsole = Premisse1.getPredicat();
            LastIsole = Premisse2.getPredicat();
        }
        if(Objects.equals(Premisse1.getPredicat(), Premisse2.getSubject())) {
            FirstIsole = Premisse1.getSubject();
            LastIsole = Premisse2.getPredicat();
        }
        // a continuer
        return result ;
    }
}
