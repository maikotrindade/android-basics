package br.trindade.androidbasics.util;

public final class AppUtil {

    private AppUtil() {
        super();
    }

    public static <T> T get(Object element) {
        return (T) element;
    }
}
