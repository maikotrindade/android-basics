package br.trindade.androidbasics.api;

import br.trindade.androidbasics.model.GitUser;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * @author maiko.trindade
 */
public interface GitApi {
    @GET("/users/{user}")
    void getFeed(@Path("user") String user, Callback<GitUser> response);
}