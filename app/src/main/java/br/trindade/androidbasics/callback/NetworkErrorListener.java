package br.trindade.androidbasics.callback;

/**
 * @author maiko.trindade
 */
public interface NetworkErrorListener<T> {
    public void onTaskComplete(T result);
}