package app.model;
public class Proposition {

	private Quantificator quantificator;
	private String subject;
	private String predicat;
	private Quality quality;
	private Type type;

	public Quality getQuality() {
		return quality;
	}

public Quantificator getQuantificator() {
		return quantificator;
}
	public Proposition(Quantificator quantificator, String subject, String predicat, Quality quality, Type type) {
		this.quantificator = quantificator;
		this.subject = subject;
		this.predicat = predicat;
		this.quality = quality;
		this.type = type;
	}
	public Proposition(Type type) {
		this.type = type;
		if (type == Type.E || type == Type.O){
			quality = Quality.Negative;
		}else{
			quality = Quality.Affirmative;
		}
	}
	public Type getType() {
		return type;
	}

}