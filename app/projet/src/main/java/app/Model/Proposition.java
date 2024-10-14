package app.Model;
public class Proposition {

	private Quantificator quantificator;
	private String subject;
	private String predicat;
	private Quality quality;
	private Type type;

	public Quality getQuality() {
		return quality;
	}
	public void setQuality(Quality quality) {
		this.quality = quality;
	}
	public Quantificator getQuantificator() {
		return quantificator;
	}

	public Proposition() {
		// TODO - implement Proposition.Proposition
		throw new UnsupportedOperationException();
	}
	public Proposition(Quantificator quantificator, String subject, String predicat, Quality quality, Type type) {
		this.quantificator = quantificator;
		this.subject = subject;
		this.predicat = predicat;
		this.quality = quality;
		this.type = type;
	}

}