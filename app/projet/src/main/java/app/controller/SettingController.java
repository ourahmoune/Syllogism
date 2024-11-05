package app.controller;

import java.util.ResourceBundle;

/**
 * SettingController manages the language settings for the application.
 * It allows switching between different language resources based on user preference.
 */
public class SettingController {
    /**
     * The current language resource bundle.
     * Initially set to the English language resource bundle.
     */
    public static ResourceBundle language = ResourceBundle.getBundle("language/texts_en");
    public static ResourceBundle subMenu = ResourceBundle.getBundle("language/texts_submenu_en");

    /**
     * Changes the application language based on the current language setting.
     * If the current language is English, it switches to French;
     * otherwise, it switches back to English.
     */
    public static void changeLanguage() {
        if (language.getObject("Language").equals("English  ")) {
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
