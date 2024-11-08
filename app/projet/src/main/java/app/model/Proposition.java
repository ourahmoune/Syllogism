package app.model;

/**
 * The `Proposition` class represents a logical proposition used in syllogistic reasoning.
 * <p>
 * Each proposition consists of a quantifier (e.g., "all" or "some"), a subject, a predicate,
 * a quality (affirmative or negative), and a type (A, E, I, or O).
 * The type is determined based on the quantifier and quality.
 * </p>
 */
public class Proposition {

	private Quantificator quantificator;
	private String subject;
	private String predicat;
	private Quality quality;
	private Type type;

	/**
	 * Constructs a `Proposition` with the specified quantificator, subject, predicate, and quality.
	 * The type is determined based on the quantificator and quality.
	 *
	 * @param quantificator The quantifier associated with the proposition.
	 * @param subject The subject of the proposition.
	 * @param predicat The predicate of the proposition.
	 * @param quality The quality (affirmative or negative) of the proposition.
	 */
	public Proposition(Quantificator quantificator, String subject, String predicat, Quality quality) {
		this.quantificator = quantificator;
		this.subject = subject;
		this.predicat = predicat;
		this.quality = quality;
		this.type = calculateType(quantificator, quality);
	}

	/**
	 * Constructs a `Proposition` with the specified type.
	 * Initializes the quality based on the type (negative for `Type.E` or `Type.O`,
	 * affirmative for `Type.A` or `Type.I`).
	 *
	 * @param type The type of the proposition (A, E, I, or O).
	 */
	public Proposition(Type type) {
		this.type = type;
		this.quality = (type == Type.E || type == Type.O) ? Quality.Negative : Quality.Affirmative;
	}
	/**
	 * Retrieves the quality of the proposition.
	 *
	 * @return The quality (affirmative or negative) of the proposition.
	 */
	public Quality getQuality() {
		return quality;
	}
	public String getSubject() {return subject;}
	public String getPredicat() {return predicat;}
	/**
	 * Retrieves the quantificator of the proposition.
	 *
	 * @return The `Quantificator` associated with the proposition.
	 */
	public Quantificator getQuantificator() {
		return quantificator;
	}

	/**
	 * Retrieves the type of the proposition.
	 *
	 * @return The type of the proposition (A, E, I, or O).
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Determines the type of the proposition based on its quantificator and quality.
	 * Universal affirmatives are Type A, universal negatives are Type E, existential affirmatives
	 * are Type I, and existential negatives are Type O.
	 *
	 * @param quantificator The quantifier associated with the proposition.
	 * @param quality The quality (affirmative or negative) of the proposition.
	 * @return The calculated type of the proposition.
	 */
	private Type calculateType(Quantificator quantificator, Quality quality) {
		Type result = null;
		switch (quantificator.getQuantity()) {
			case Universal -> result = (quality == Quality.Affirmative) ? Type.A : Type.E;
			case Exisential -> result = (quality == Quality.Affirmative) ? Type.I : Type.O;
		}
		return result;
	}
}
