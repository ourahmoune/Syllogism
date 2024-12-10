package test;

import app.model.Proposition;
import app.model.Quality;
import app.model.Quantificator;
import app.model.Quantity;
import app.model.polysyllogismes.Polysyllogisme;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SearchPropostion {

    @Test
    public void testSearchePropostion() {
        Map<Integer, Proposition> propositions = new HashMap<Integer, Proposition>();
        Map<Integer, Proposition> propositionsvide = new HashMap<Integer, Proposition>();
        Proposition p1 = new Proposition(new Quantificator(Quantity.Universal, "tous"), "mammifere", "animal", Quality.Affirmative);
        Proposition p2 = new Proposition(new Quantificator(Quantity.Universal, "tous"), "poils durs", "fox", Quality.Affirmative);
        Proposition p3 = new Proposition(new Quantificator(Quantity.Universal, "tous"), "velo", "animal", Quality.Affirmative);
        Proposition p4 = new Proposition(new Quantificator(Quantity.Universal, "tous"), "fox", "chien", Quality.Affirmative);
        Proposition p5 = new Proposition(new Quantificator(Quantity.Universal, "tous"), "minivelo", "velo", Quality.Affirmative);
        Proposition p6 = new Proposition(new Quantificator(Quantity.Universal, "tous"), "chien", "mammifere", Quality.Affirmative);
        Proposition p7 = new Proposition(new Quantificator(Quantity.Universal, "tous"), "minivelo", "poils durs", Quality.Affirmative);

        propositions.put(1, p1);
        propositions.put(2, p2);
        propositions.put(3, p3);
        propositions.put(4, p4);
        propositions.put(5, p5);
        propositions.put(6, p6);
        propositions.put(7, p7);


        Polysyllogisme polysyllogisme = new Polysyllogisme(propositions,7);
        Polysyllogisme polysyllogismevide = new Polysyllogisme(propositionsvide,0);

        Proposition result1 =polysyllogisme.SearchProposition("mammifere");
        Proposition result3= polysyllogisme.SearchProposition("poils durs");
        Proposition result4 = polysyllogismevide.SearchProposition("velo") ;
        assertEquals(result1.getPredicat(),p1.getPredicat());
        assertEquals(result1.getSubject(),p1.getSubject());
        assertEquals(result1.getQuantity(),p1.getQuantity());
        assertEquals(result1.getQuality(),p1.getQuality());
        assertEquals(result3.getPredicat(),p2.getPredicat());
        assertEquals(result3.getSubject(),p2.getSubject());
        assertEquals(result3.getQuantity(),p2.getQuantity());
        assertEquals(result3.getQuality(),p2.getQuality());
        assertNull(result4);
    }
}
