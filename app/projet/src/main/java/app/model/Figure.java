package app.model;

import app.controller.SettingController;

/**
 * Enum representing different figures.
 */
public enum Figure {
    UN,
    DEUX,
    TROIS,
    QUATRE;

    @Override
    public String toString() {
        if (SettingController.getLanguage().equals("english")){
            return switch (this) {
                case UN -> "ONE";
                case DEUX -> "TWO";
                case TROIS -> "THREE";
                case QUATRE -> "FOUR";
            };
        }else{
            return super.toString();
        }
    }
}
