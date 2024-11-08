package test;

import app.model.Proposition;
import app.model.Quality;
import app.model.Quantificator;
import app.model.Quantity;
import app.model.polysyllogismes.Polysyllogisme;
import org.junit.Test;
import java.util.Map;

public class CheckTwoPropositionTest {
    @Test
    public void test() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "P1" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S2" , "P2" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result == null);
    }
    @Test
    public void test2() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "S1" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "S1" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result == null);
    }
    @Test
    public void test3() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "P1" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "P1" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result == null);
    }
    @Test
    public void test4() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "TT" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"TT" , "S1" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result == null);
    }
    @Test
    public void test5() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "P1" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"P2" , "S1" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result != null);
        System.out.println("test5"+result);
    }
    @Test
    public void test6() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "TT" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "P2" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result != null);
        System.out.println("test6"+result);
    }
    @Test
    public void test7() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "TT" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"TT" , "S2" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result != null);
        System.out.println("test7"+result);
    }
    @Test
    public void test8() {
        Proposition P1 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S1" , "TT" , Quality.Affirmative);
        Proposition P2 = new Proposition(new Quantificator(Quantity.Universal,"tous") ,"S2" , "TT" , Quality.Affirmative);
        Polysyllogisme polysyllogisme = new Polysyllogisme();;
        Map<String,String> result = polysyllogisme.CheckTwoPremise(P1,P2);
        assert (result != null);
        System.out.println("test8"+result);
    }
}
