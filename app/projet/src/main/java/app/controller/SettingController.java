package app.controller;

import java.util.ResourceBundle;

public class SettingController {
    public static ResourceBundle language = ResourceBundle.getBundle("language/texts_en");

    public static void changeLanguage() {
        if (language.getObject("Language").equals("English  ")){
            language = ResourceBundle.getBundle("language/texts_fr");
        }else{
            language = ResourceBundle.getBundle("language/texts_en");
        }
    }

}
