package test;

import app.model.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RppTest {
    @Test
    public void testTrue(){
        //Création des quantificateurs
        Quantificator q1 = new Quantificator(Quantity.Exisential, "Il y a");
        Quantificator q2 = new Quantificator(Quantity.Exisential, "Certains");

        // Création des propositions
        Proposition prop1 = new Proposition(q1, "homme", "mortel", Quality.Negative);
        Proposition prop2 = new Proposition(q2, "philosophes", "homme", Quality.Negative);
        Proposition prop3 = new Proposition(q1, "philosophes", "mortels", Quality.Affirmative);

        // Ajout des propositions dans une map
        Map<Integer, Proposition> propositions = new HashMap<>();
        propositions.put(1, prop1);
        propositions.put(2, prop2);
        propositions.put(3, prop3);

        Syllogism syllogism = new Syllogism(Figure.UN, propositions);
        Rpp rule= new Rpp();
        assertFalse(rule.Launch(syllogism));
    }
    @Test
    public void testFalse(){
        //Création des quantificateurs
        Quantificator q1 = new Quantificator(Quantity.Universal, "Tous");
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
        Rpp rule= new Rpp();
        assertTrue(rule.Launch(syllogism));
    }

}