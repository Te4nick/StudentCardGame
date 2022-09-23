package ru.scg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class Localizator {

    private static final I18NBundle locale =
            I18NBundle.createBundle(Gdx.files.internal("i18n/locale_en"),
                    Locale.ENGLISH);
    private static final I18NBundle localeRU =
            I18NBundle.createBundle(Gdx.files.internal("i18n/locale_ru"),
                    new Locale("ru"));
    private static String currentLocale = "en";

    private Localizator() {}

    /**
     * Changes game locale. Supported values are: "en", "ru".
     * Default value is "en" if locale is not found.
     */
    public static void setLocale(String l) {
        if (l.equals("en") | l.equals("ru")) currentLocale = l;
    }

    public static String getString(String key) {
        switch (currentLocale) {
            case "ru":
                return localeRU.get(key);
            default:
                return locale.get(key);
        }
//        return locale.get(key);
    }

}
