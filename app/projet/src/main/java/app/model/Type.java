package app.model;

/**
 * Enum representing the different types of propositions in a syllogism.
 */
public enum Type {
    A, // Universal affirmative
    E, // Universal negative
    I, // Particular affirmative
    O ;  // Particular negative



    public Quantity getQauntity(){
       if (this ==  Type.A || this == Type.E ){
           return Quantity.Universal;
       }else{
           return Quantity.Exisential;
       }
    }
    public Quality getQuality(){
        if (this ==  Type.A || this == Type.I ){
            return Quality.Affirmative ;
        }else{
            return Quality.Negative;
        }
    }
}
