package br.trindade.androidbasics.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.trindade.androidbasics.model.Movie;
import br.trindade.androidbasics.network.VolleySingleton;
import br.trindade.androidbasics.ui.R;
import br.trindade.androidbasics.util.BitmapUtils;

/**
 * @author maiko.trindade
 */
public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolderBoxOffice> {

    private List<Movie> mListMovies = new ArrayList<>();
    private LayoutInflater mInflater;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    private DateFormat mFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public AdapterMovies(Context context) {
        mInflater = LayoutInflater.from(context);
        mVolleySingleton = VolleySingleton.getInstance();
        mImageLoader = mVolleySingleton.getImageLoader();
    }

    public void setMovies(ArrayList<Movie> listMovies) {
        this.mListMovies = listMovies;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderBoxOffice onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_movie_box_office, parent, false);
        ViewHolderBoxOffice viewHolder = new ViewHolderBoxOffice(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderBoxOffice holder, int position) {
        Movie movie = mListMovies.get(position);
        //one or more fields of the Movie object may be null since they are fetched from the web
        holder.movieTitle.setText(movie.getTitle());

        //retrieved date may be null


        Date movieReleaseDate = movie.getReleaseDateTheater();
        if (movieReleaseDate != null) {
            String formattedDate = mFormatter.format(movieReleaseDate);
            holder.movieReleaseDate.setText(formattedDate);
        } else {
            holder.movieReleaseDate.setText("NA");
        }

        int audienceScore = movie.getAudienceScore();
        if (audienceScore == -1) {
            holder.movieAudienceScore.setRating(0.0F);
            holder.movieAudienceScore.setAlpha(0.5F);
        } else {
            holder.movieAudienceScore.setRating(audienceScore / 20.0F);
            holder.movieAudienceScore.setAlpha(1.0F);
            LayerDrawable ratings = (LayerDrawable) holder.movieAudienceScore.getProgressDrawable();
            ratings.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        }

        String urlThumbnail = movie.getUrlThumbnail();
        loadImages(urlThumbnail, holder);

    }


    private void loadImages(String urlThumbnail, final ViewHolderBoxOffice holder) {
        if (!urlThumbnail.equals("NA")) {
            mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    Bitmap bitmap = response.getBitmap();
                    if (bitmap != null) {
                        holder.movieThumbnail.setImageBitmap(BitmapUtils.getRounded(bitmap, 15));
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListMovies.size();
    }

    static class ViewHolderBoxOffice extends RecyclerView.ViewHolder {

        ImageView movieThumbnail;
        TextView movieTitle;
        TextView movieDuration;
        TextView movieReleaseDate;
        RatingBar movieAudienceScore;

        public ViewHolderBoxOffice(View itemView) {
            super(itemView);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            movieTitle = (TextView) itemView.findViewById(R.id.txt_title);
            movieDuration = (TextView) itemView.findViewById(R.id.txt_duration);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.txt_release_date);
            movieAudienceScore = (RatingBar) itemView.findViewById(R.id.movie_ratings);
        }
    }
}
