package app.model;
public abstract class Rule {

	private String result;

	public abstract Boolean Launch(Syllogism syllogism) ;

}