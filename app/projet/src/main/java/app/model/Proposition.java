package app.model;
public class Proposition {

	private Quantificator quantificator;
	private String subject;
	private String predicat;
	private Quality quality;
	private Type type;

	public Proposition(Quantificator quantificator, String subject, String predicat, Type type) {
		// TODO - implement Proposition.Proposition
		this.quantificator = quantificator;
		this.subject = subject;
		this.predicat = predicat;
		this.type = type;
	}

	public Quality getQuality() {
		return quality;
	}
	public Type getType() {
		return type;
	}
}