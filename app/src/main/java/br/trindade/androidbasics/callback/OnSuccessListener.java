package br.trindade.androidbasics.callback;

import java.util.List;

import br.trindade.androidbasics.model.Article;

public interface OnSuccessListener {
    void onSuccess(List<Article> articles);
}
