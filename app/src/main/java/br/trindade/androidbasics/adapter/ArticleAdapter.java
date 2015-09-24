package br.trindade.androidbasics.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.trindade.androidbasics.model.Article;
import br.trindade.androidbasics.ui.R;

/**
 * @author maiko.trindade
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> mArticles;

    public ArticleAdapter(List<Article> articles) {
        mArticles = articles;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        final Article article = mArticles.get(position);
        holder.mTitle.setText(article.getTitle() + " (" + ++position + "/" + getItemCount() + ")");
        holder.mPublicationDate.setText(article.getPublicationDate());
        holder.mDescription.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView mTitle;
        protected TextView mPublicationDate;
        protected TextView mDescription;

        public ViewHolder(View view) {
            super(view);
            this.mTitle = (TextView) view.findViewById(R.id.article_title);
            this.mPublicationDate = (TextView) view.findViewById(R.id.article_date);
            this.mDescription = (TextView) view.findViewById(R.id.article_description);
        }
    }
}
