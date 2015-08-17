package br.trindade.androidbasics.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @author maiko.trindade
 */
public class Movie implements Parcelable {

    private long id;
    private String title;
    private Date releaseDateTheater;
    private int audienceScore;
    private String synopsis;
    private String urlThumbnail;
    private String urlSelf;
    private String urlCast;
    private String urlReviews;
    private String urlSimilar;
    private int runtime;

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDateTheater() {
        return releaseDateTheater;
    }

    public void setReleaseDateTheater(Date releaseDateTheater) {
        this.releaseDateTheater = releaseDateTheater;
    }

    public int getAudienceScore() {
        return audienceScore;
    }

    public void setAudienceScore(int audienceScore) {
        this.audienceScore = audienceScore;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getUrlSelf() {
        return urlSelf;
    }

    public void setUrlSelf(String urlSelf) {
        this.urlSelf = urlSelf;
    }

    public String getUrlCast() {
        return urlCast;
    }

    public void setUrlCast(String urlCast) {
        this.urlCast = urlCast;
    }

    public String getUrlReviews() {
        return urlReviews;
    }

    public void setUrlReviews(String urlReviews) {
        this.urlReviews = urlReviews;
    }

    public String getUrlSimilar() {
        return urlSimilar;
    }

    public void setUrlSimilar(String urlSimilar) {
        this.urlSimilar = urlSimilar;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeLong(releaseDateTheater != null ? releaseDateTheater.getTime() : -1);
        dest.writeInt(this.audienceScore);
        dest.writeString(this.synopsis);
        dest.writeString(this.urlThumbnail);
        dest.writeString(this.urlSelf);
        dest.writeString(this.urlCast);
        dest.writeString(this.urlReviews);
        dest.writeString(this.urlSimilar);
        dest.writeInt(this.runtime);
    }

    protected Movie(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        long tmpReleaseDateTheater = in.readLong();
        this.releaseDateTheater = tmpReleaseDateTheater == -1 ? null : new Date(tmpReleaseDateTheater);
        this.audienceScore = in.readInt();
        this.synopsis = in.readString();
        this.urlThumbnail = in.readString();
        this.urlSelf = in.readString();
        this.urlCast = in.readString();
        this.urlReviews = in.readString();
        this.urlSimilar = in.readString();
        this.runtime = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}