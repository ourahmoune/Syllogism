package app.model.polysyllogismes;
import app.model.Figure;
import app.model.Proposition;
import app.model.Rules;

import java.util.HashMap;
import java.util.Map;
public class Polysyllogisme{
    protected int taille;
    protected Figure figure = null;
    protected Map<Integer, Proposition> proposition;
    protected boolean valid;

    public Polysyllogisme() {}
    public Polysyllogisme(Map<Integer,Proposition> propositions, int taille) {
        this.proposition = propositions;
        this.taille = taille ;
        assert taille == propositions.size();
    }
    public Proposition SearchProposition(String terme){
        Proposition propositionresult = null;
        for( int i = 1 ; i<taille ; i++){
            Proposition p = proposition.get(i);
            if(p != null){
                propositionresult = new Proposition(p.getQuantificator() ,p.getSubject() , p.getPredicat() , p.getQuality()) ;
                if(propositionresult.getPredicat().equals(terme) || propositionresult.getSubject().equals(terme)  ){
                    proposition.remove(i);
                    return propositionresult;
                }
            }


        }
        return propositionresult ;


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
        if(taille<2){
            return true ;
        }
        boolean result = true ;
        Map<String , String> FistSecondCommun = null ;
        for(int i=1;i<taille ;i++){
            FistSecondCommun = CheckTwoPremise(proposition.get(i),proposition.get(i+1));
            if(FistSecondCommun == null  ){
                return false;
            }
        }

        String IsoleFirst , IsoleLast , Terme1Conclusion , Terme2Conclusion = null ;
        FistSecondCommun = CheckTwoPremise(proposition.get(1),proposition.get(2));
        if(FistSecondCommun != null){
            IsoleFirst = FistSecondCommun.get("FirstIsole");
            FistSecondCommun = CheckTwoPremise(proposition.get(taille-2),proposition.get(taille-1));
            if(FistSecondCommun != null){
                IsoleLast = FistSecondCommun.get("SecondIsole");
                Terme1Conclusion = proposition.get(taille).getSubject();
                Terme2Conclusion = proposition.get(taille).getPredicat();
                result =(  (  IsoleFirst.equals(Terme2Conclusion)   )
                        &&
                        (IsoleLast.equals(Terme1Conclusion) ));
            }else{
                result = false ;
            }
        }else{
            result = false ;
        }
        return result ;
    }
    public void  Reordonne(){
        if(taille>1){
            String terme = proposition.get(taille).getPredicat();
            Map<Integer, Proposition> newproposition = new HashMap<>();
            for(int i =1 ; i<taille ; i++){
                Proposition p = SearchProposition(terme) ;
                if(p != null){
                    newproposition.put(i, p);
                    if(terme.equals(p.getPredicat()) ){
                        terme = p.getSubject();
                    }else{
                        terme = p.getPredicat();
                    }
                }

            }

            Proposition conclusion = new Proposition(proposition.get(taille).getQuantificator() , proposition.get(taille).getSubject() , proposition.get(taille).getPredicat() , proposition.get(taille).getQuality() );
            proposition = newproposition ;
            proposition.put(taille,conclusion);
        }

    }

    /**
     * Solves the syllogism (not yet implemented).
     *
     *
     * @throws UnsupportedOperationException if not implemented
     */

    public boolean solve() {
        System.out.println("Solving syllogism...");
        this.valid=Rules.Launch(this);
        if(this.valid) {
            System.out.println("le syllogism est valid");
            return true;
        }else{
            System.out.println("le syllogism est invalid");
            return false;
        }
    }
    public Figure getFigure() {
        return this.figure;
    }

    public HashMap<String, String> getNotValidRule(){
        return Rules.AllRules(this);
    }
}