package test;

import app.model.*;
import org.junit.Test;
import app.model.allSyllogism.Data;
import app.model.allSyllogism.PolySyllogismAndRules;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * SyllogismTest is a unit test class for validating the correctness of syllogism rules.
 * It compares the results of various syllogism rules against expected values stored in an Excel file.
 */
public class SyllogismTest {

    /**
     * The data object for loading syllogisms and their associated rules from an Excel file.
     */
    Data data = new Data("src/main/resources/app/Tableur.xlsx");

    /**
     * A list of all syllogisms and their corresponding rules loaded from the Excel file.
     */
    List<PolySyllogismAndRules> AllSyllogismAndRules = data.getAllSyllogismAndRules();

    /**
     * Tests all syllogism rules against the expected results from the Excel table.
     * It initializes rule instances and checks the output of each rule against
     * the corresponding value in the Excel sheet.
     */
    @Test
    public void AllTest() {
        data.load();
        int i=0;
        for(PolySyllogismAndRules syl : AllSyllogismAndRules) {
            i++;
            if(syl.getSyllogism() != null) {
                Rmt rmt = new Rmt();
                Rlh rlh = new Rlh();
                Raa raa = new Raa();
                Rpp rpp = new Rpp();
                Rp rp = new Rp();
                Rnn rnn = new Rnn();
                Rn rn = new Rn();
                Ruu ruu = new Ruu();
                Rii rii = new Rii();

                // Assert that the output of each rule matches the expected value
                Syllogism syllogism = syl.getSyllogism();
                assertEquals(rmt.Launch(syllogism), syl.getRules(0));

                /*
                assertEquals(rlh.Launch(syl.getPolysyllogisme()), syl.getRules(1));
                assertEquals(rnn.Launch(syl.getPolysyllogisme()), syl.getRules(2));
                assertEquals(rn.Launch(syl.getPolysyllogisme()), syl.getRules(3));
                assertEquals(raa.Launch(syl.getPolysyllogisme()), syl.getRules(4));
                assertEquals(rpp.Launch(syl.getPolysyllogisme()), syl.getRules(5));
                assertEquals(rp.Launch(syl.getPolysyllogisme()), syl.getRules(6));
                assertEquals(ruu.Launch(syl.getPolysyllogisme()), syl.getRules(8));
                assertEquals(rii.Launch(syl.getPolysyllogisme()), syl.getRules(10));
                 */
            }
        }
    }
}
