package app.Model.AllSyllogism;
import app.Model.Syllogism;
import java.util.ArrayList;
import java.util.List;


public class SyllogismAndRules {
    private Syllogism syllogism;
    List<Boolean> Rules = new ArrayList<>();

    public SyllogismAndRules() {
        syllogism= null ;
    }
    public void setSyllogism(Syllogism syllogism) {
        this.syllogism = syllogism;
    }
    public  Syllogism getSyllogism() {
        return syllogism;
    }
    public void setRules(boolean Rule) {
        Rules.add(Rule);
    }
    public boolean getRules(int index) {
        return Rules.get(index);
    }

}
