package test;

import app.model.Proposition;
import app.model.Quality;
import app.model.Quantificator;
import app.model.Quantity;
import app.model.polysyllogismes.Polysyllogisme;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ReordonneTest {
    @Test
    public void test() {
        Map<Integer, Proposition> propositions = new HashMap<Integer, Proposition>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "poils durs", "fox" , Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "velo", "animal" , Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "fox", "chien" , Quality.Affirmative));
        propositions.put(5, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "velo" , Quality.Affirmative));
        propositions.put(6, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "chien", "mammifere" , Quality.Affirmative));
        propositions.put(7, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "poils durs" , Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 7);
        if (!polysyllogisme.HasValideForm()){
            System.out.println("Polysyllogisme has no valid form start Reordonne ");
            polysyllogisme.Reordonne() ;
        }
        for(int i = 1 ; i <= polysyllogisme.getTaille() ; i++) {
            System.out.println(" " +polysyllogisme.getProposition().get(i).getSubject() +"  "+polysyllogisme.getProposition().get(i).getPredicat());
        }
        assert polysyllogisme.HasValideForm();
    }
}
