package test;
import app.Model.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RlhTest {
    @Test
    public void RlhTestVrai1(){
        Quantificator qauntous = new Quantificator(Quantity.Universal , "tous ") ;
        Quantificator Exict = new Quantificator(Quantity.Exisential , "exicte" ) ;
        Proposition P1  = new Proposition(qauntous , "chat" , "rouge" , Type.A) ;
        Proposition P2 = new Proposition(Exict , "chat" , "rouge" , Type.O) ;
        Proposition C = new Proposition(qauntous ,  "sujet" ,"predicat", Type.A) ;
        Map<Integer , Proposition>  mapproo = new HashMap<>();
        mapproo.put(1, P1);
        mapproo.put(2, P2);
        mapproo.put(3, C);
        Syllogism syl = new Syllogism(Figure.UN, mapproo);
        Rlh rmt = new Rlh();
        assertFalse(rmt.Launch(syl));

    }
    @Test
    public void RlhTestFaux1(){
        Quantificator qauntous = new Quantificator(Quantity.Universal , "tous ") ;
        Quantificator Exict = new Quantificator(Quantity.Exisential , "exicte" ) ;
        Proposition P1  = new Proposition(qauntous , "chat" , "rouge" , Type.A) ;
        Proposition P2 = new Proposition(Exict , "chat" , "rouge" , Type.A) ;
        Proposition C = new Proposition(qauntous ,  "sujet" ,"predicat", Type.A) ;
        Map<Integer , Proposition>  mapproo = new HashMap<>();
        mapproo.put(1, P1);
        mapproo.put(2, P2);
        mapproo.put(3, C);
        Syllogism syl = new Syllogism(Figure.UN, mapproo);
        Rlh rmt = new Rlh();
        assertTrue(rmt.Launch(syl)) ;

    }

}
