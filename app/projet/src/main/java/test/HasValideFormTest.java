package test;

import app.model.Proposition;
import app.model.Quality;
import app.model.Quantificator;
import app.model.Quantity;
import app.model.polysyllogismes.Polysyllogisme;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HasValideFormTest {

    @Test
    public void test() {
        Map<Integer, Proposition>propositions = new HashMap<Integer, Proposition>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "poils durs", "fox" , Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "fox", "chien" , Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "chien", "mammifere" , Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(5, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "velo", "animal" , Quality.Affirmative));
        propositions.put(6, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "velo" , Quality.Affirmative));
        propositions.put(7, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "poils durs" , Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 7);
        assert (polysyllogisme.HasValideForm());
    }
    @Test
    public void test1() {
        Map<Integer, Proposition>propositions = new HashMap<Integer, Proposition>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "poils durs", "fox" , Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "velo", "animal" , Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "fox", "chien" , Quality.Affirmative));
        propositions.put(5, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "velo" , Quality.Affirmative));
        propositions.put(6, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(7, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "poils durs" , Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 7);
        assert (! polysyllogisme.HasValideForm());
    }
    @Test
    public void test2() {
        Map<Integer, Proposition>propositions = new HashMap<Integer, Proposition>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "poils durs", "fox" , Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "fox", "chien" , Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "chien", "mammifere" , Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(5, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "velo", "animal" , Quality.Affirmative));
        propositions.put(6, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "velo" , Quality.Affirmative));
        propositions.put(7, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "xx" , Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 7);
        assert (!polysyllogisme.HasValideForm());
    }
    @Test
    public void test3() {
        Map<Integer, Proposition>propositions = new HashMap<Integer, Proposition>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "poils durs", "fox" , Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "fox", "chien" , Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "chien", "mammifere" , Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "mammifere", "animal" , Quality.Affirmative));
        propositions.put(5, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "velo", "animal" , Quality.Affirmative));
        propositions.put(6, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "minivelo", "velo" , Quality.Affirmative));
        propositions.put(7, new Proposition(new Quantificator(Quantity.Universal ,"tous"), "XX", "poils durs" , Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 7);
        assert (!polysyllogisme.HasValideForm());
    }
    @Test
    public void testSingleProposition() {
        Map<Integer, Proposition> propositions = new HashMap<>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal, "tous"), "poils durs", "fox", Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 1);
        assert(!polysyllogisme.HasValideForm());
    }
    @Test
    public void testDisconnectedPremises() {
        Map<Integer, Proposition> propositions = new HashMap<>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal, "tous"), "poils durs", "fox", Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal, "tous"), "velo", "animal", Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal, "tous"), "minivelo", "velo", Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 3);
        assert(!polysyllogisme.HasValideForm());
    }
    @Test
    public void testCyclicPremises() {
        Map<Integer, Proposition> propositions = new HashMap<>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal, "tous"), "poils durs", "fox", Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal, "tous"), "fox", "poils durs", Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal, "tous"), "chien", "poils durs", Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal, "tous"), "chien", "poils durs", Quality.Affirmative));
        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 4);
        assert(!polysyllogisme.HasValideForm());
    }

    @Test
    public void testIdenticalTerms() {
        Map<Integer, Proposition> propositions = new HashMap<>();
        propositions.put(1, new Proposition(new Quantificator(Quantity.Universal, "tous"), "animal", "animal", Quality.Affirmative));
        propositions.put(2, new Proposition(new Quantificator(Quantity.Universal, "tous"), "animal", "animal", Quality.Affirmative));
        propositions.put(3, new Proposition(new Quantificator(Quantity.Universal, "tous"), "animal", "animal", Quality.Affirmative));
        propositions.put(4, new Proposition(new Quantificator(Quantity.Universal, "tous"), "animal", "animal", Quality.Affirmative));

        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions, 4);

        assert(!polysyllogisme.HasValideForm());
    }




}
