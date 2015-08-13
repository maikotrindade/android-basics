package br.trindade.androidbasics.util.json;

import org.json.JSONObject;

/**
 * @author maiko.trindade
 */
public class JsonUtil {
    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }
}
