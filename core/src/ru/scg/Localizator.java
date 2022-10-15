package ru.scg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public final class Localizator {
    public static final int LOCALE_EN = 0;
    public static final int LOCALE_RU = 1;

    private static final I18NBundle localeDefault =
            I18NBundle.createBundle(Gdx.files.internal("i18n/locale_en"),
                    Locale.ENGLISH);
    private static final I18NBundle localeRU =
            I18NBundle.createBundle(Gdx.files.internal("i18n/locale_ru"),
                    new Locale("ru"));
    private static int currentLocale = 0;

    private Localizator() {}

    /**
     * Changes game locale. Supported values are: "en", "ru".
     * Default value is "en" if locale is not found.
     */
    public static void setLocaleDefault(int l) {
        if (l == LOCALE_EN | l == LOCALE_RU) currentLocale = l;
    }

    public static String getString(String key) {
        switch (currentLocale) {
            case 1:
                return localeRU.get(key);
            default:
                return localeDefault.get(key);
        }
//        return locale.get(key);
    }

}
