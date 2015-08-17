package br.trindade.androidbasics.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public final class AndroidUtil {

    private AndroidUtil() {
        super();
    }

    public static <T> T get(Object element) {
        return (T) element;
    }

    public static AlertDialog getSimpleDialog(final Context context, final String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(context.getClass().getSimpleName());
        alertDialog.setMessage(message);
        return alertDialog;
    }
}
