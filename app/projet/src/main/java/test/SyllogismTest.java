package test;


import app.Model.Rlh;
import app.Model.Rmt;
import org.junit.Test;
import app.Model.AllSyllogism.Data;
import app.Model.AllSyllogism.SyllogismAndRules;
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
                assertEquals(rmt.Launch(syl.getSyllogism()) , syl.getRules(0));
                assertEquals(rlh.Launch(syl.getSyllogism()) , syl.getRules(1));
            }

        }

    }
}