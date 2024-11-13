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
    private void swap(int indice1 , int indice2){
        Proposition p1 = proposition.get(indice1);
        Proposition p2 = proposition.get(indice2);
        proposition.remove(indice1);
        proposition.remove(indice2);
        proposition.put(indice2, p1);
        proposition.put(indice1, p2);
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
        FistSecondCommun = CheckTwoPremise(proposition.get(taille-2),proposition.get(taille-1));
        IsoleLast = FistSecondCommun.get("SecondIsole");
        Terme1Conclusion = proposition.get(taille).getSubject();
        Terme2Conclusion = proposition.get(taille).getPredicat();
        result =(  (  IsoleFirst.equals(Terme1Conclusion ) || IsoleFirst.equals(Terme2Conclusion)   )
                &&
                (IsoleLast.equals(Terme1Conclusion) || IsoleLast.equals( Terme2Conclusion)));
        return result ;
    }
    public boolean Reordonne(){
        System.out.println("start :reodonne ");
        boolean result = false ;
        int i=1 ;
        int j=0;
        while(i<taille-2 && !result ){// l'avant dernier proposition
            j=i+1 ;
            while(j<taille-1){
                swap(i,j);
                result = HasValideForm() ;
                if(result){
                    break ;
                }else{
                    j++;
                }
            }
            i++;
        }
        return result ;
    }
}