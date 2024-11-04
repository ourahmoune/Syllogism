package test;

import app.model.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RnnTest {
    @Test
    public void testTrue(){
        //Création des quantificateurs
        Quantificator q1 = new Quantificator(Quantity.Exisential, "Tous");
        Quantificator q2 = new Quantificator(Quantity.Exisential, "Certains");

        // Création des propositions
        Proposition prop1 = new Proposition(q1, "hommes", "mortels", Quality.Negative);
        Proposition prop2 = new Proposition(q2, "philosophes", "hommes", Quality.Negative);
        Proposition prop3 = new Proposition(q1, "philosophes", "mortels", Quality.Affirmative);

        // Ajout des propositions dans une map
        Map<Integer, Proposition> propositions = new HashMap<>();
        propositions.put(1, prop1);
        propositions.put(2, prop2);
        propositions.put(3, prop3);

        Syllogism syllogism = new Syllogism(Figure.UN, propositions);
        Rnn rule= new Rnn();
        assertFalse(rule.Launch(syllogism));
    }
    @Test
    public void testFalse(){
        //Création des quantificateurs
        Quantificator q1 = new Quantificator(Quantity.Exisential, "Tous");
        Quantificator q2 = new Quantificator(Quantity.Exisential, "Certains");

        // Création des propositions
        Proposition prop1 = new Proposition(q1, "hommes", "mortels", Quality.Affirmative);
        Proposition prop2 = new Proposition(q2, "philosophes", "hommes", Quality.Affirmative);
        Proposition prop3 = new Proposition(q1, "philosophes", "mortels", Quality.Negative);

        // Ajout des propositions dans une map
        Map<Integer, Proposition> propositions = new HashMap<>();
        propositions.put(1, prop1);
        propositions.put(2, prop2);
        propositions.put(3, prop3);

        Syllogism syllogism = new Syllogism(Figure.UN, propositions);
        Rnn rule= new Rnn();
        assertTrue(rule.Launch(syllogism));
    }

}