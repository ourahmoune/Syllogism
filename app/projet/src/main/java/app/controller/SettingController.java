package app.controller;

import java.util.ResourceBundle;

public class SettingController {
    public static ResourceBundle language = ResourceBundle.getBundle("language/texts_en");
    public static ResourceBundle subMenu = ResourceBundle.getBundle("language/texts_submenu_en");

    public static void changeLanguage() {
        if (language.getObject("Language").equals("English  ")){
            language = ResourceBundle.getBundle("language/texts_fr");
            subMenu = ResourceBundle.getBundle("language/texts_submenu_fr");
        }else{
            language = ResourceBundle.getBundle("language/texts_en");
            subMenu = ResourceBundle.getBundle("language/texts_submenu_en");
        }
    }

    public static String getLanguage() {
        if (language.getObject("Language").equals("English  ")){
            return "english";
        }else{
            return "french";
        }
    }

}
