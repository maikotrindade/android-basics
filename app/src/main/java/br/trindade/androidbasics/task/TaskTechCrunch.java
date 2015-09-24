package br.trindade.androidbasics.task;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import br.trindade.androidbasics.callback.OnSuccessListener;
import br.trindade.androidbasics.model.Article;

/**
 * @author maiko.trindade
 */
public class TaskTechCrunch extends AsyncTask<Void, Void, List<Article>> {

    private static final String TAG = TaskTechCrunch.class.getSimpleName();

    public static final String ARTICLE_ITEM = "item";
    public static final String ARTICLE_TITLE = "title";
    public static final String ARTICLE_DATE = "pubDate";
    public static final String ARTICLE_DESCRIPTION = "description";
    public static final String ARTICLE_THUMBNAIL = "media:thumbnail";

    private OnSuccessListener mOnSuccessListener;

    public TaskTechCrunch(OnSuccessListener onSuccessListener) {
        mOnSuccessListener = onSuccessListener;
    }

    @Override
    protected List<Article> doInBackground(Void... voids) {
        String downloadUrl = "http://feeds.feedburner.com/TechCrunch/android?format=xml";
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            final InputStream inputStream = connection.getInputStream();
            return processXML(inputStream);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return null;
    }

    public List<Article> processXML(final InputStream inputStream) {
        List<Article> articles = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element rootElement = document.getDocumentElement();
            NodeList items = rootElement.getElementsByTagName(ARTICLE_ITEM);
            NodeList itemChildren;
            Node currentItem;
            Node currentChild;

            Article article;

            for (int indexParent = 0; indexParent < items.getLength(); indexParent++) {
                currentItem = items.item(indexParent);
                itemChildren = currentItem.getChildNodes();
                article = new Article();
                for (int indexChild = 0; indexChild < itemChildren.getLength(); indexChild++) {
                    currentChild = itemChildren.item(indexChild);
                    if (currentChild.getNodeName().equalsIgnoreCase(ARTICLE_TITLE)) {
                        article.setTitle(currentChild.getTextContent());
                    }
                    if (currentChild.getNodeName().equalsIgnoreCase(ARTICLE_DESCRIPTION)) {
                        article.setDescription(currentChild.getTextContent());
                    }
                    if (currentChild.getNodeName().equalsIgnoreCase(ARTICLE_DATE)) {
                        article.setPublicationDate(currentChild.getTextContent());
                    }
                    if (currentChild.getNodeName().equalsIgnoreCase(ARTICLE_THUMBNAIL)) {
                        article.setMediaThumbnail(currentChild.getAttributes().item(0).getTextContent());
                    }
                }
                if (article != null) {
                    articles.add(article);
                }
            }
        } catch (ParserConfigurationException e) {
            Log.e(TAG, e.toString());
        } catch (SAXException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }

        return articles;
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        mOnSuccessListener.onSuccess(articles);
        super.onPostExecute(articles);
    }
}