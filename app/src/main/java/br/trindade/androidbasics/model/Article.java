package br.trindade.androidbasics.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author maiko.trindade
 */
public class Article implements Parcelable {
    private String mTitle;
    private String mPublicationDate;
    private String mDescription;
    private String mMediaThumbnail;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getMediaThumbnail() {
        return mMediaThumbnail;
    }

    public void setMediaThumbnail(String mediaThumbnail) {
        this.mMediaThumbnail = mediaThumbnail;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.mPublicationDate = publicationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mPublicationDate);
        dest.writeString(this.mDescription);
        dest.writeString(this.mMediaThumbnail);
    }

    public Article() {
    }

    protected Article(Parcel in) {
        this.mTitle = in.readString();
        this.mPublicationDate = in.readString();
        this.mDescription = in.readString();
        this.mMediaThumbnail = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
