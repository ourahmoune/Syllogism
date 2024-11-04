package app.model;

/**
 * The Rlh class represents the "latius hos" Rule.
 * Rlh: the quantity of a term in the conclusion can only be universal if it is so in the premise containing that term.
 * <p>
 * The main method of this class, `Launch`, checks if a given syllogism complies with syllogistic logic rules
 * based on its figure type and the types of propositions (A, E, I, O) that make up the syllogism.
 * </p>
 */
public class Rlh extends Rule {

    private String Result;

    /**
     * Main method to evaluate the validity of a syllogism.
     * <p>
     * This method determines whether the syllogism is valid based on its premises (modeP1 and modeP2),
     * its conclusion (modeC), and its figure.
     * </p>
     *
     * @param syllogism The `Syllogism` object to evaluate, containing the propositions (premises and conclusion) as well as the figure.
     * @return A boolean `true` if the syllogism is valid according to the defined rules, `false` otherwise.
     * @throws UnsupportedOperationException if the proposition type or the figure of the syllogism is not supported.
     */
    public Boolean Launch(Syllogism syllogism) {
        Boolean result = false;

        Type modeP1 = syllogism.getProposition().get(1).getType();  // Type of the first premise
        Type modeP2 = syllogism.getProposition().get(2).getType();  // Type of the second premise
        Type modeC = syllogism.getProposition().get(3).getType();   // Type of the conclusion

        switch (syllogism.getFigure()) {
            case UN:
                result = switch (modeC) {
                    case A -> modeP2 == Type.A || modeP2 == Type.E;
                    case E -> (modeP2 == Type.A || modeP2 == Type.E) && (modeP1 == Type.E || modeP1 == Type.O);
                    case O -> modeP1 == Type.E || modeP1 == Type.O;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modeC + " : Type not supported");
                };
                break;

            case DEUX:
                result = switch (modeC) {
                    case A -> modeP2 == Type.A || modeP2 == Type.E;
                    case E -> (modeP2 == Type.A || modeP2 == Type.E) && (modeP1 == Type.A || modeP1 == Type.E);
                    case O -> modeP1 == Type.E || modeP1 == Type.A;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modeC + " : Type not supported");
                };
                break;

            case TROIS:
                result = switch (modeC) {
                    case A -> modeP2 == Type.O || modeP2 == Type.E;
                    case E -> (modeP2 == Type.O || modeP2 == Type.E) && (modeP1 == Type.O || modeP1 == Type.E);
                    case O -> modeP1 == Type.O || modeP1 == Type.E;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modeC + " : Type not supported");
                };
                break;

            case QUATRE:
                result = switch (modeC) {
                    case A -> modeP2 == Type.O || modeP2 == Type.E;
                    case E -> (modeP2 == Type.O || modeP2 == Type.E) && (modeP1 == Type.A || modeP1 == Type.E);
                    case O -> modeP1 == Type.A || modeP1 == Type.E;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modeC + " : Type not supported");
                };
                break;

            default:
                throw new UnsupportedOperationException(syllogism.getFigure() + " : Figure not supported");
        }
        return result;
    }
}
