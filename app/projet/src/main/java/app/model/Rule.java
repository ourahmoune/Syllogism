package app.model;
public abstract class Rule {

	private String result;

	public abstract Boolean Launch(Syllogism syllogism) ;
	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj != null && getClass() == obj.getClass());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}