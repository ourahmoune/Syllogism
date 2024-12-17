package test;
import app.controller.MenuController;
import app.model.*;
import app.model.polysyllogismes.Polysyllogisme;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PolyTest {
    @Test
    public void test() {
        Rules.initialize();
        Map<Integer, Proposition> propositions = new HashMap<Integer, Proposition>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "poils durs", "fox" , Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "fox", "chien" , Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "chien", "mammifere" , Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(5, new Proposition(new Quantificator(Quantity.Universal ,"aucun"), "velo", "animal" , Quality.Negative));
        propositions.put(6, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "velo" , Quality.Affirmative));
        propositions.put(7, new Proposition(new Quantificator(Quantity.Universal ,"aucun"), "minivelo", "poils durs" , Quality.Negative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions,7) ;
        polysyllogisme.Reordonne();
        assert  polysyllogisme.HasValideForm();

        assert polysyllogisme.solve();

        //assert  polysyllogisme.solve();
    }
    @Test
    public void test2() {
        Rules.initialize();
        Map<Integer, Proposition> propositions = new HashMap<Integer, Proposition>();

        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "poils durs", "fox" , Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "fox", "chien" , Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "chien", "mammifere" , Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(5, new Proposition(new Quantificator(Quantity.Universal ,"aucun"), "velo", "animal" , Quality.Affirmative));
        propositions.put(6, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "velo" , Quality.Affirmative));
        propositions.put(7, new Proposition(new Quantificator(Quantity.Universal ,"aucun"), "minivelo", "poils durs" , Quality.Negative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions,7) ;
        polysyllogisme.Reordonne();
        assert  polysyllogisme.HasValideForm();

        assert !polysyllogisme.solve(); //car Raa n'est pas valide
    }

    @Test
    public void test3() {
        Rules.initialize();
        Map<Integer, Proposition> propositions = new HashMap<Integer, Proposition>();
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions,0);
        polysyllogisme.Reordonne();


    }
}
