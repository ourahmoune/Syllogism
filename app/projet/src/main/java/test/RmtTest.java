package test;
import app.Model.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
public class RmtTest {
    @Test
    public void RmtTestVrai1(){
        Quantificator qauntous = new Quantificator(Quantity.Universal , "tous ") ;
        Quantificator Exict = new Quantificator(Quantity.Exisential , "exicte" ) ;
        Proposition P1  = new Proposition(qauntous , "chat" , "rouge" , Type.A) ;
        Proposition P2 = new Proposition(Exict , "chat" , "rouge" , Type.I) ;
        Map<Integer , Proposition>  mapproo = new HashMap<>();
        mapproo.put(1, P1);
        mapproo.put(2, P2);
        Syllogism syl = new Syllogism(Figure.UN, mapproo);
        Rmt rmt = new Rmt();
        assertTrue(rmt.Launch(syl)) ;

    }
    @Test
    public void RmtTestFaux1(){
        Quantificator Exict = new Quantificator(Quantity.Exisential , "exict ") ;
        Proposition P1  = new Proposition(Exict , "chat" , "rouge" , Type.O) ;
        Proposition P2 = new Proposition(Exict , "chat" , "rouge" , Type.I) ;
        Map<Integer , Proposition>  mapproo = new HashMap<>();
        mapproo.put(1, P1);
        mapproo.put(2, P2);
        Syllogism syl = new Syllogism(Figure.UN, mapproo);
        Rmt rmt = new Rmt();
        assertFalse(rmt.Launch(syl));

    }
}
