package test;


import app.model.*;
import org.junit.Test;
import app.model.allSyllogism.Data;
import app.model.allSyllogism.SyllogismAndRules;
import java.util.List;

import static org.junit.Assert.assertEquals;
/*
    To test a rule, you need to create it (Rmt rmt = new Rmt())
    then compare the result of the rule for each syllogism with the corresponding value in the Excel table.
    So the index of getRules is the index of our rule in our Excel file.
*/

public class SyllogismTest {
    Data data = new Data("src/main/resources/app/Tableur.xlsx");
    List<SyllogismAndRules> AllSyllogismAndRules = data.getAllSyllogismAndRules() ;
    @Test
    public void AllTest(){
        data.load();
        for(SyllogismAndRules syl : AllSyllogismAndRules) {
            if(syl.getSyllogism()!= null){
                Rmt rmt = new Rmt();
                Rlh rlh = new Rlh();
                Raa raa = new Raa();
                Rpp rpp = new Rpp();
                Rp rp = new Rp();
                Rnn rnn = new Rnn();
                Rn rn = new Rn();
                Ruu ruu = new Ruu();
                Rii rii = new Rii();
                assertEquals(rmt.Launch(syl.getSyllogism()) , syl.getRules(0));
                assertEquals(rlh.Launch(syl.getSyllogism()) , syl.getRules(1));
                assertEquals(rnn.Launch(syl.getSyllogism()) , syl.getRules(2));
                assertEquals(rn.Launch(syl.getSyllogism())  , syl.getRules(3));
                assertEquals(raa.Launch(syl.getSyllogism()) , syl.getRules(4));
                assertEquals(rpp.Launch(syl.getSyllogism()) , syl.getRules(5));
                assertEquals(rp.Launch(syl.getSyllogism())  , syl.getRules(6));
                assertEquals(ruu.Launch(syl.getSyllogism()) , syl.getRules(8));
                assertEquals(rii.Launch(syl.getSyllogism()) , syl.getRules(10));

            }

        }

    }
}