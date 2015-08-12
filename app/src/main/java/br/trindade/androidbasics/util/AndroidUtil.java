package br.trindade.androidbasics.util;

public final class AndroidUtil {

    private AndroidUtil() {
        super();
    }

    public static <T> T get(Object element) {
        return (T) element;
    }
}
