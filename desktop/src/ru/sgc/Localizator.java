package ru.sgc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class Localizator {

    private static HashMap<String, I18NBundle> locales;
    private static String currentLocale = "en";

    private Localizator() {
        FileHandle baseFileHandle = Gdx.files.internal("strings/locale");
        I18NBundle localeDefault = I18NBundle.createBundle(baseFileHandle); // Default English Bundle

        baseFileHandle = Gdx.files.internal("strings/locale_ru");
        Locale currentLocale = new Locale("ru");
        I18NBundle localeRU = I18NBundle.createBundle(baseFileHandle, currentLocale); // Russian Bundle

        locales.put("en", localeDefault);
        locales.put("ru", localeRU);
    }

    /**
     * Changes game locale. Supported values are: "en", "ru".
     * Default value is "en" if locale is not found.
     */
    public static void setLocale(String locale) {
        if (!locales.containsKey(locale)) locale = "en";
        currentLocale = locale;
    }

    public static String getString(String key) {
        return locales.get(currentLocale).get(key);
    }

}
