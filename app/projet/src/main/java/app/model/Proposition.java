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
	public Proposition(Quantificator quantificator, String subject, String predicat, Quality quality) {
		this.quantificator = quantificator;
		this.subject = subject;
		this.predicat = predicat;
		this.quality = quality;
		this.type = calculateType(quantificator, quality);
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

	private Type calculateType(Quantificator quantificator,  Quality quality) {
		Type result = null ;
		switch (quantificator.getQuantity()){
			case Universal -> {
				if(quality == Quality.Affirmative) {
					result = Type.A ;
				}else{
					result = Type.E ;
				}
			}
			case Exisential -> {
				if(quality == Quality.Affirmative) {
					result = Type.I ;
				}else{
					result = Type.O ;
				}
			}

		};
		return result ;

	}
}